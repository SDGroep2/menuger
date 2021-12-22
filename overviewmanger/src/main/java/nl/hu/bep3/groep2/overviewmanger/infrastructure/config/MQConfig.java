package nl.hu.bep3.groep2.overviewmanger.infrastructure.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;


@Configuration
public class MQConfig {
    @Value("${overview.queue}")
    public String QUEUE;
    @Value("${menuger.exchange}")
    public String EXCHANGE;
    @Value("${overview.routing-key}")
    public String ROUTING_KEY;
    @Value("${spring.rabbitmq.host}")
    public String HOST;
    @Value("${spring.rabbitmq.port}")
    public int PORT;
    @Value("${overview.order.queue}")
    public String ORDER_QUEUE;
    @Value("${overview.order.routing-key}")
    public String ORDER_ROUTING_KEY;


    @Bean
    public Queue queue() {
        return QueueBuilder.durable(QUEUE).build();
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding() {
        return BindingBuilder.bind(queue())
                .to(exchange())
                .with(ROUTING_KEY);
    }

    @Bean
    public Queue orderQueue() {
        return QueueBuilder.durable(ORDER_QUEUE).build();
    }

    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue())
                .to(exchange())
                .with(ORDER_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(Jackson2JsonMessageConverter converter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory());
        rabbitTemplate.setMessageConverter(converter);

        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter converter(Jackson2ObjectMapperBuilder builder) {
        // We need to configure a message converter to be used by RabbitTemplate.
        // We could use any format, but we'll use JSON so it is easier to inspect.
        ObjectMapper objectMapper = builder
                .createXmlMapper(false)
                .build();

        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter(objectMapper);

        // Set this in order to prevent deserialization using the sender-specific
        // __TYPEID__ in the message header.
        converter.setAlwaysConvertToInferredType(true);

        return converter;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new CachingConnectionFactory(HOST, PORT);
    }


}

