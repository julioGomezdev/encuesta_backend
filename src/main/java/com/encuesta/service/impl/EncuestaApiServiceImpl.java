package com.encuesta.service.impl;

import com.encuesta.dao.EncuestaDao;
import com.encuesta.dao.dto.EncuestaDTO;
import com.encuesta.dao.dto.EncuestaResultadoResponse;
import com.encuesta.service.EncuestaApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class EncuestaApiServiceImpl implements EncuestaApiService {
    private final static Logger _logger = LoggerFactory.getLogger(EncuestaApiServiceImpl.class);

    @Autowired
    private EncuestaDao encuestaDao;

    @Override
    public String registrarEncuesta(EncuestaDTO encuesta) {
        return encuestaDao.registrarEncuesta(encuesta.getEmail(), encuesta.getEstiloMusical());
    }

    @Override
    public EncuestaResultadoResponse obtenerResultados() {
        return encuestaDao.obtenerResultados();
    }
}
