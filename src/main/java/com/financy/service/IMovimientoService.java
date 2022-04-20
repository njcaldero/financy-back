package com.financy.service;

import com.financy.entity.Cuenta;
import com.financy.entity.Movimiento;

import java.util.List;

public interface IMovimientoService {
    public List<Movimiento> GetAll();
    public Movimiento Create(Movimiento movimiento) throws Exception;

    List<Movimiento> FindOrderedByFechaTransaccionLimitedTo(Long idCuenta);
}
