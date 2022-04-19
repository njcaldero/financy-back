package com.financy.dto;

import com.financy.entity.Cuenta;
import com.financy.entity.TipoTransaccion;

import javax.persistence.*;
import javax.validation.constraints.Positive;
import java.util.Date;

public class movimientoDto {

    private long id;


    private Cuenta cuenta;

    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha_transaccion;

    @Positive(message = "El valor de la transacción debe ser mayor que cero")
    private double valor_transaccion;

    @Positive()
    private double saldo;

    @ManyToOne
    @JoinColumn(name = "tipo_transaccion_id")
    private TipoTransaccion tipo_Transaccion;
}
