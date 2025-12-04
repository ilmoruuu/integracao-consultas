package upe.br.consultas.business.producers;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import upe.br.consultas.controller.DTO.notificacoes.MsgCancelamentoDTO;
import upe.br.consultas.infra.config.RabbitMQConfig;

@Component
@RequiredArgsConstructor
public class MsgProducer {

  private final RabbitTemplate rabbitTemplate;

    public void enviarMensagemCancelamento(MsgCancelamentoDTO mensagem) {
        // Envia para o Exchange, com a Routing Key específica
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_CONSULTAS,
                RabbitMQConfig.ROUTING_KEY_CANCELAMENTO,
                mensagem
        );
        System.out.println("Mensagem enviada para o RabbitMQ: " + mensagem);
    }
  
    public void enviarMensagemFinanceiro(Object mensagem) {
        // Envia para o Exchange, com a Routing Key específica
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_FINANCEIRO,
                RabbitMQConfig.ROUTING_KEY_FINANCEIRO,
                mensagem
        );
        System.out.println("Mensagem enviada para o RabbitMQ: " + mensagem);
    }

    public void enviarMensagemEstoque(Object mensagem) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_ESTOQUE,
                RabbitMQConfig.ROUTING_KEY_ESTOQUE,
                mensagem
        );
        System.out.println("Mensagem enviada para o RabbitMQ: " + mensagem);
    }
}
