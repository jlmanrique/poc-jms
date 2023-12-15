package org.acme;

import org.apache.camel.builder.RouteBuilder;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.statement.Query;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class GetInfoRouteBuilder extends RouteBuilder {

    @Inject
    private Jdbi jdbi;
    
    @ConfigProperty(name = "app.jms.queue-validated")
    private String queue_in;

    @ConfigProperty(name = "app.jms.queue-processed")
    private String queue_out;

    @Override
    public void configure() throws Exception {

        from(String.format("jms:queue:%s?concurrentConsumers=50",queue_in))
            .process(exchange -> {
                String[] body = exchange.getIn().getBody(String.class).split("-");
                String tipoDocumento=body[0];
                String numeroDocumento=body[1];
                String nombreCompleto = jdbi.withHandle( handle -> {
                    Query query = handle.createQuery("select concat(nombres,', ',apellido_paterno,' ',apellido_materno) from ciudadano where tipo_documento=? and numero_documento=?");
                    query.bind(1, tipoDocumento);
                    query.bind(2, numeroDocumento);
                    log.info("Datos para procesar TipoDocumento {} y Numero de Documento {}",tipoDocumento,numeroDocumento);
                    return query.mapTo(String.class).findOne().orElse("");
                });
                exchange.getIn().setBody(nombreCompleto);  
            })
        .to(String.format("jms:queue:%s",queue_out))
        .log("Received a message - ${body} - sending to processed");
    }

}
