package com.encuesta.dao.dto;

import java.util.Map;


public class EncuestaResultadoResponse {
    private Map<String, Long> resultados;


    public Map<String, Long> getResultados() {
        return resultados;
    }

    public void setResultados(Map<String, Long> resultados) {
        this.resultados = resultados;
    }

    @Override
    public String toString() {
        return "EncuestaResultadoResponse{" +
                "resultados=" + resultados +
                '}';
    }
}
