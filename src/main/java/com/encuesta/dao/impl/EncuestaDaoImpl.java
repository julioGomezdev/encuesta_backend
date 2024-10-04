package com.encuesta.dao.impl;

import com.encuesta.dao.EncuestaDao;
import com.encuesta.dao.dto.EncuestaResultadoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository("encuestaDao")
public class EncuestaDaoImpl implements EncuestaDao {
    private static final Logger _logger = LoggerFactory.getLogger(EncuestaDaoImpl.class);

    private final JdbcTemplate jdbcTemplate;

    public EncuestaDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public String registrarEncuesta(String email, String estiloMusical) {
        jdbcTemplate.update(SQL_INSERT_ENCUESTA, email, estiloMusical);
        return "Encuesta registrada exitosamente";
    }

    @Override
    public EncuestaResultadoResponse obtenerResultados() {
        Map<String, Long> resultados = new HashMap<>();
        jdbcTemplate.query(SQL_OBTENER_RESULTADOS, rs -> {
            resultados.put(rs.getString("estilo_musical"), rs.getLong("votos"));
        });

        EncuestaResultadoResponse response = new EncuestaResultadoResponse();
        response.setResultados(resultados);
        return response;
    }

}
