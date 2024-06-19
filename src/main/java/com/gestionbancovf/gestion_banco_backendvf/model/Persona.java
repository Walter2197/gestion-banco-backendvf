package com.gestionbancovf.gestion_banco_backendvf.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.processing.Pattern;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "personas")
@Inheritance(strategy = InheritanceType.JOINED)

public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    @Column(name = "nombre")
    private String nombre;

    @NotBlank(message = "El género es obligatorio")
    @Column(name = "genero")
    private String genero;

    @Column(name = "edad")
    private Integer edad;

    @NotBlank(message = "La identificación es obligatoria")
    @Column(name = "identificacion", unique = true)
    private String identificacion;

    @NotBlank(message = "La dirección es obligatoria")
    @Column(name = "direccion")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Column(name = "telefono")
    private String telefono;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Integer getEdad() {
        return edad;
    }


    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
