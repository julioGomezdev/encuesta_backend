package com.encuesta;

import com.encuesta.dao.EncuestaDao;
import com.encuesta.dao.dto.EncuestaResultadoResponse;
import com.encuesta.service.EncuestaApiService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EncuestaBackendApplicationTests {

    @Autowired
    private EncuestaApiService encuestaApiService;

    @Autowired
    private EncuestaDao encuestaDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    void contextLoads() {
        assertNotNull(encuestaApiService, "El servicio de encuesta debe estar cargado");
        assertNotNull(encuestaDao, "El DAO de encuesta debe estar cargado");
        assertNotNull(jdbcTemplate, "JdbcTemplate debe estar cargado");
    }

    @Test
    void testCrearTabla() {
        jdbcTemplate.execute("DROP TABLE IF EXISTS ENCUESTA");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS ENCUESTA (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "EMAIL VARCHAR(255) NOT NULL UNIQUE, " +
                "ESTILO_MUSICAL VARCHAR(100) NOT NULL, " +
                "FECHA_REGISTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")");
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME = 'ENCUESTA'", Integer.class);
        assertEquals(1, count, "La tabla ENCUESTA debe existir");
    }

    @Test
    void testAgregarEncuesta() {
        jdbcTemplate.update("INSERT INTO ENCUESTA (EMAIL, ESTILO_MUSICAL) VALUES (?, ?)", "usuario@ejemplo.com", "Rock");
        Integer count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM ENCUESTA WHERE EMAIL = ?", Integer.class, "usuario@ejemplo.com");
        assertEquals(1, count, "Debe haberse insertado una fila para el usuario 'usuario@ejemplo.com'");
    }

    @Test
    void testObtenerResultados() {
        jdbcTemplate.update("INSERT INTO ENCUESTA (EMAIL, ESTILO_MUSICAL) VALUES (?, ?)", "usuario1@ejemplo.com", "Rock");
        jdbcTemplate.update("INSERT INTO ENCUESTA (EMAIL, ESTILO_MUSICAL) VALUES (?, ?)", "usuario2@ejemplo.com", "Pop");
        EncuestaResultadoResponse response = encuestaApiService.obtenerResultados();

        assertTrue(response.getResultados().containsKey("Rock"), "Debe contener el estilo 'Rock'");
        assertTrue(response.getResultados().containsKey("Pop"), "Debe contener el estilo 'Pop'");
    }

}
