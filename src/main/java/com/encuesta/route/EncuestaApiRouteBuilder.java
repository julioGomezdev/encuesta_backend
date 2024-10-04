package com.encuesta.route;

import com.encuesta.dao.dto.EncuestaDTO;
import com.encuesta.dao.dto.EncuestaResultadoResponse;
import com.encuesta.service.EncuestaApiService;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EncuestaApiRouteBuilder extends RouteBuilder {

    @Autowired
    private EncuestaApiService encuestaApiService;

    @Override
    public void configure() throws Exception {

        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .contextPath("/api")
                .enableCORS(true);

        rest("/encuestas")

                .get("/resultados")
                .produces("application/json")
                .route()
                .process(exchange -> {
                    EncuestaResultadoResponse response = encuestaApiService.obtenerResultados();
                    exchange.getIn().setBody(response);
                })
                .endRest()

                .post("/registrar").type(EncuestaDTO.class)
                .outType(String.class)
                .route()
                .process(exchange -> {
                    EncuestaDTO encuesta = exchange.getIn().getBody(EncuestaDTO.class);
                    String response = encuestaApiService.registrarEncuesta(encuesta);
                    exchange.getIn().setBody(response);
                })
                .endRest();
    }
}
