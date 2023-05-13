package org.acme;

import org.apache.camel.builder.RouteBuilder;

public class CamelRouteBuilder extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("mq:queue:DEV.QUEUE.INICIO.CLI1")
            .log("Pattern: ${exchange}")
        .to("mq:queue:DEV.QUEUE.VALIDADOS")
        .end();
    }
    
}
