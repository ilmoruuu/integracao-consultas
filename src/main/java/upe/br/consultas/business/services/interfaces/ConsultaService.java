package upe.br.consultas.business.services.interfaces;
import upe.br.consultas.controller.DTO.consulta.ConsultaCriadaDTO;
import upe.br.consultas.controller.DTO.consulta.ConsultaDTO;
import java.util.List;

public interface ConsultaService {
    
    ConsultaDTO agendarConsulta(ConsultaCriadaDTO dto);
    
    ConsultaDTO atualizarConsulta(ConsultaDTO dto);
    
    void desmarcarConsulta(Integer id);
    
    List<ConsultaDTO> listarConsultas();
    
    ConsultaDTO buscarConsultaPorId(Integer id);
    
    // Métodos extras úteis
    List<ConsultaDTO> buscarConsultasPorPaciente(Integer pacienteId);
    List<ConsultaDTO> buscarConsultasPorMedico(Integer medicoId);
    List<ConsultaDTO> buscarConsultasPorNomeMedico(String nome);
    List<ConsultaDTO> buscarConsultasPorNomePaciente(String nome);
}