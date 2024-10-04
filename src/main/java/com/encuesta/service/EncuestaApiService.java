package com.encuesta.service;

import com.encuesta.dao.dto.EncuestaDTO;
import com.encuesta.dao.dto.EncuestaResultadoResponse;


public interface EncuestaApiService {

    String registrarEncuesta(EncuestaDTO encuesta);

    EncuestaResultadoResponse obtenerResultados();

}
