package com.gestionbancovf.gestion_banco_backendvf.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clientes")

public class Cliente extends Persona {
    @NotBlank(message = "El clientId es obligatorio")
    @Column(name = "client_id")
    private String clientId;

    @NotBlank(message = "La contraseña es obligatoria")
    @Column(name = "contrasenia")
    private String contrasenia;

    @NotNull(message = "El estado es obligatorio")
    @Column(name = "estado")
    private Boolean estado;

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}