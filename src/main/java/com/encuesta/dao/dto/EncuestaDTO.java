package com.encuesta.dao.dto;

public class EncuestaDTO {
    private String email;
    private String estiloMusical;

    public EncuestaDTO() {
    }

    public EncuestaDTO(String email, String estiloMusical) {
        this.email = email;
        this.estiloMusical = estiloMusical;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEstiloMusical() {
        return estiloMusical;
    }

    public void setEstiloMusical(String estiloMusical) {
        this.estiloMusical = estiloMusical;
    }

    @Override
    public String toString() {
        return "EncuestaDTO{" +
                "email='" + email + '\'' +
                ", estiloMusical='" + estiloMusical + '\'' +
                '}';
    }
}
