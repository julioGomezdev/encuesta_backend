package com.encuesta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@SpringBootApplication
public class EncuestaBackendApplication implements CommandLineRunner {
    private static final Logger _logger = LoggerFactory.getLogger(EncuestaBackendApplication.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static void main(String[] args) {
        SpringApplication.run(EncuestaBackendApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS ENCUESTA (" +
                "ID INT AUTO_INCREMENT PRIMARY KEY, " +
                "EMAIL VARCHAR(255) NOT NULL UNIQUE, " +
                "ESTILO_MUSICAL VARCHAR(100) NOT NULL, " +
                "FECHA_REGISTRO TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ")");
        _logger.info("Tabla ENCUESTA creada o ya existía");
    }

    @Bean
    public CommandLineRunner testConnection(JdbcTemplate jdbcTemplate) {
        return args -> {
            try {
                Integer result = jdbcTemplate.queryForObject("SELECT 1", Integer.class);
                _logger.info("Conexión exitosa, resultado de la consulta: " + result);
            } catch (Exception e) {
                _logger.error("Error al conectar con la base de datos", e);
            }
        };
    }
}

