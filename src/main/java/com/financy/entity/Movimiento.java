package com.financy.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Movimiento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "cuenta_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    private Cuenta cuenta;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_transaccion;

    @Positive(message = "El valor de la transacción debe ser mayor que cero")
    private double valor_transaccion;

    private double saldo;

    @ManyToOne
    @JoinColumn(name = "tipo_transaccion_id")
    @JsonIgnoreProperties(value={"hibernateLazyInitializer","handler"})
    private TipoTransaccion tipo_Transaccion;
}
