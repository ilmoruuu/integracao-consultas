package upe.br.consultas.infra.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    
    public static final String QUEUE_CANCELAMENTO = "consulta.cancelada.fila";
    public static final String EXCHANGE_CONSULTAS = "consultas.exchange";
    public static final String ROUTING_KEY_CANCELAMENTO = "consulta.cancelada";

    public static final String EXCHANGE_FINANCEIRO = "appointments";
    public static final String ROUTING_KEY_FINANCEIRO = "appointments.confirmed.finance";
    public static final String ROUTING_KEY_ESTOQUE = "appointments.confirmed.estoque";

    public static final String EXCHANGE_ESTOQUE = "appointments.estoque";




    // Cria a Fila
    @Bean
    public Queue filaCancelamento() {
        return new Queue(QUEUE_CANCELAMENTO, true); // true = durável (não some se o rabbitmq reiniciar)
    }

    // Cria o Exchange 
    @Bean
    public DirectExchange exchangeConsultas() {
        return new DirectExchange(EXCHANGE_CONSULTAS);
    }

    //Liga a Fila ao Exchange
    @Bean
    public Binding bindingCancelamento(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY_CANCELAMENTO);
    }

    //Configura para enviar JSON
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}