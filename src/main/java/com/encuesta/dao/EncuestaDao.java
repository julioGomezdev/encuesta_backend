package com.encuesta.dao;

import com.encuesta.dao.dto.EncuestaResultadoResponse;

import java.util.List;
import java.util.Map;

public interface EncuestaDao {

    String SQL_INSERT_ENCUESTA = "INSERT INTO ENCUESTA (EMAIL, ESTILO_MUSICAL) VALUES (?, ?)";

    String SQL_OBTENER_RESULTADOS = "SELECT ESTILO_MUSICAL, COUNT(*) as VOTOS FROM ENCUESTA GROUP BY ESTILO_MUSICAL";



    String registrarEncuesta(String email, String estiloMusical);

    EncuestaResultadoResponse obtenerResultados();
    }
