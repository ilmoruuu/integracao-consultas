package upe.br.consultas.business.services.implementations;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import upe.br.consultas.business.producers.MsgProducer;
import upe.br.consultas.business.services.interfaces.ConsultaService;
import upe.br.consultas.controller.DTO.consulta.ConsultaCriadaDTO;
import upe.br.consultas.controller.DTO.consulta.ConsultaDTO;
import upe.br.consultas.controller.DTO.notificacoes.MsgCancelamentoPacienteDTO;
import upe.br.consultas.controller.DTO.notificacoes.MsgCancelamentoMedicoDTO;
import upe.br.consultas.controller.DTO.notificacoes.MsgEstoqueDTO;
import upe.br.consultas.controller.DTO.notificacoes.MsgFinanceiroDTO;
import upe.br.consultas.infra.entities.Consulta;
import upe.br.consultas.infra.entities.Medico;
import upe.br.consultas.infra.entities.Paciente;
import upe.br.consultas.infra.entities.Recepcionista;
import upe.br.consultas.infra.exceptions.PacienteNaoEncontradoException;
import upe.br.consultas.infra.repositories.ConsultaRepository;
import upe.br.consultas.infra.repositories.MedicoRepository;
import upe.br.consultas.infra.repositories.PacienteRepository;
import upe.br.consultas.infra.repositories.RecepcionistaRepository;

import java.util.List;

@Service
@RequiredArgsConstructor 
public class ConsultaServiceImpl implements ConsultaService {


    private final ConsultaRepository consultaRepository;
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;
    private final RecepcionistaRepository recepcionistaRepository;
    private final MsgProducer msgProducer;
    

    @Override
    public ConsultaDTO agendarConsulta(ConsultaCriadaDTO dto) {
        
        Medico medico = medicoRepository.findById(dto.medicoId())
                .orElseThrow(() -> new RuntimeException("Médico não encontrado com ID: " + dto.medicoId()));

        Paciente paciente = pacienteRepository.findById(dto.pacienteId())
                .orElseThrow(() -> new PacienteNaoEncontradoException());   

        Recepcionista recepcionista = recepcionistaRepository.findById(dto.recepcionistaId())
                .orElseThrow(() -> new RuntimeException("Recepcionista não encontrado com ID: " + dto.recepcionistaId()));

        
        Consulta consulta = new Consulta();
        consulta.setData(dto.data());
        consulta.setDescricao(dto.descricao());
        consulta.setMedico(medico);
        consulta.setPaciente(paciente);
        consulta.setRecepcionista(recepcionista);
        if (dto.materiaisRequisitados() != null) {
            consulta.setMateriaisRequisitados(dto.materiaisRequisitados());
        }
        consulta.setValor(dto.valor());
        consulta.setCategoria(dto.categoria());
        consulta.setQuantidadeMaterial(dto.quantidadeMaterial());


        Consulta consultaSalva = consultaRepository.save(consulta);

        MsgFinanceiroDTO msgFinanceira = new MsgFinanceiroDTO(
                consultaSalva.getId(),
                consultaSalva.getMedico().getId(),
                consultaSalva.getValor(),
                consultaSalva.getData()
        );

        // Envia (Assíncrono - não trava o agendamento se o financeiro estiver fora do ar)
        msgProducer.enviarMensagemFinanceiro(msgFinanceira);

        MsgEstoqueDTO msgEstoque = new MsgEstoqueDTO(
                consultaSalva.getId(),
                consultaSalva.getMateriaisRequisitados(),
                consultaSalva.getCategoria(),
                consultaSalva.getQuantidadeMaterial()
        );

        msgProducer.enviarMensagemEstoque(msgEstoque);
       
        return ConsultaDTO.consultaToDTO(consultaSalva);
    }

    @Override
    public ConsultaDTO atualizarConsulta(ConsultaDTO dto) {
        
        Consulta consulta = consultaRepository.findById(dto.id())
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada para atualização."));

        
        consulta.setData(dto.data());
        consulta.setDescricao(dto.descricao());
        consulta.setValor(dto.valor());
        consulta.setCategoria(dto.categoria());
        consulta.setQuantidadeMaterial(dto.quantidadeMaterial());
        if (dto.materiaisRequisitados() != null) {
            consulta.setMateriaisRequisitados(dto.materiaisRequisitados());
        }

        Consulta atualizada = consultaRepository.save(consulta);

        return ConsultaDTO.consultaToDTO(atualizada);
    }

    @Override
    public void desmarcarConsulta(Integer id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada para exclusão."));

        String especialidadeStr = consulta.getMedico().getEspecializacao().name().replace("_", " ");

        MsgCancelamentoPacienteDTO notificacaoPaciente = new MsgCancelamentoPacienteDTO(
                consulta.getPaciente().getEmail(),
                consulta.getPaciente().getNome(),
                consulta.getMedico().getNome(),
                especialidadeStr,
                consulta.getData(),
                "Cancelamento solicitado pelo sistema."
        );

        MsgCancelamentoMedicoDTO notificacaoMedico = new MsgCancelamentoMedicoDTO(
                consulta.getMedico().getEmail(),
                consulta.getMedico().getNome(),
                consulta.getPaciente().getNome(),
                consulta.getData(),
                "Cancelamento solicitado pelo sistema."
        );

        //Envia para o RabbitMQ (Assíncrono - não trava o sistema)
        msgProducer.enviarMensagemCancelamento(notificacaoPaciente);
        msgProducer.enviarMensagemCancelamento(notificacaoMedico);

        consultaRepository.deleteById(id);
    }

    @Override
    public List<ConsultaDTO> listarConsultas() {
        return consultaRepository.findAll().stream()
                .map(ConsultaDTO::consultaToDTO)
                .toList();
    }

    @Override
    public ConsultaDTO buscarConsultaPorId(Integer id) {
        Consulta consulta = consultaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Consulta não encontrada."));
        return ConsultaDTO.consultaToDTO(consulta);
    }

    // Métodos de Busca Específicos

    @Override
    public List<ConsultaDTO> buscarConsultasPorPaciente(Integer pacienteId) {
        return consultaRepository.findByPacienteId(pacienteId).stream()
                .map(ConsultaDTO::consultaToDTO)
                .toList();
    }

    @Override
    public List<ConsultaDTO> buscarConsultasPorMedico(Integer medicoId) {
        return consultaRepository.findByMedicoId(medicoId).stream()
                .map(ConsultaDTO::consultaToDTO)
                .toList();
    }

    @Override
    public List<ConsultaDTO> buscarConsultasPorNomeMedico(String nome) {
        return consultaRepository.findByMedicoNomeContainingIgnoreCase(nome).stream()
                .map(ConsultaDTO::consultaToDTO)
                .toList();
    }

    @Override
    public List<ConsultaDTO> buscarConsultasPorNomePaciente(String nome) {
        return consultaRepository.findByPacienteNomeContainingIgnoreCase(nome).stream()
                .map(ConsultaDTO::consultaToDTO)
                .toList();
    }
}