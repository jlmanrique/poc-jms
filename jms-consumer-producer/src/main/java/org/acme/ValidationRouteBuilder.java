package org.acme;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ValidationRouteBuilder extends RouteBuilder {

    @ConfigProperty(name = "app.jms.queue-start")
    private String queue_in;

    @ConfigProperty(name = "app.jms.queue-validated")
    private String queue_out;

    @Override
    public void configure() throws Exception {
        from(String.format("jms:queue:%s",queue_in))
            .log("Received a message - ${body} - sending to validated")
        .to(String.format("jms:queue:%s",queue_out));
    }

}
