package com.financy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Cuenta  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotEmpty(message = "El campo cédula es requerido")
    private String cedula;

    @NotEmpty(message = "El campo nombre es requerido")
    private String nombres;

    private String apellidos;

    @Positive(message = "El valor de apertura debe ser mayor que cero")
    private double valor_apertura;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_creacion;

    private double saldo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipo_cuenta_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    private TipoCuenta tipo_cuenta;

}
