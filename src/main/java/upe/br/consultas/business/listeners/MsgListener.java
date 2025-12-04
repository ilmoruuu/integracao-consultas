package upe.br.consultas.business.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import upe.br.consultas.controller.DTO.notificacoes.MsgCancelamentoDTO;
import upe.br.consultas.infra.config.RabbitMQConfig;

public class MsgListener {
  @RabbitListener(queues = RabbitMQConfig.QUEUE_CANCELAMENTO)
    public void receberMensagem(MsgCancelamentoDTO mensagem) {
        // Aqui entra a lógica de enviar email de verdade (JavaMailSender)
        System.out.println("------------------------------------------------");
        System.out.println("NOVA MENSAGEM RECEBIDA DO RABBITMQ:");
        System.out.println("Enviando e-mail para: " + mensagem.emailPaciente());
        System.out.println("Olá " + mensagem.nomePaciente() + ", sua consulta com Dr. " + 
                           mensagem.nomeMedico() + "("+ mensagem.especialidadeMedico() + "), no dia " + mensagem.dataConsulta() + 
                           " foi cancelada. Descrição da consulta: " + mensagem.descricao());
        System.out.println("------------------------------------------------");
    }
}
