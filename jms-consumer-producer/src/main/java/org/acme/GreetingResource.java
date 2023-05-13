package org.acme;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello")
public class GreetingResource {

    @Inject
    private ConnectionFactory connectionFactory;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() throws JMSException {

        Connection conn = connectionFactory.createConnection();
        
        System.out.println("Esta es la conexion aciva "+conn);
        
        Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Queue destination = session.createQueue("DEV.QUEUE.INICIO.CLI1");
        MessageProducer producer = session.createProducer(destination);

        TextMessage message = session.createTextMessage("Hola Denis!!!");
        message.setStringProperty("_Destination",destination.toString());
        producer.send(message);
        
        

        return "Hello from RESTEasy Reactive";
    }
}
