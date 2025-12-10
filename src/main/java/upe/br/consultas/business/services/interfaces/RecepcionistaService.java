package upe.br.consultas.business.services.interfaces;

import upe.br.consultas.controller.DTO.recepcionista.LoginDTO;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaCriadaDTO;
import upe.br.consultas.controller.DTO.recepcionista.RecepcionistaDTO;

import java.util.List;

public interface RecepcionistaService {
    RecepcionistaDTO cadastrarRecepcionista(RecepcionistaCriadaDTO dto);
    RecepcionistaDTO atualizarRecepcionista(RecepcionistaDTO dto);
    void excluirRecepcionista(Integer id);
    List<RecepcionistaDTO> listarRecepcionistas();
    RecepcionistaDTO buscarPorId(Integer id);
    List<RecepcionistaDTO> buscarPorNome(String nome);
    RecepcionistaDTO logar(LoginDTO login);
}