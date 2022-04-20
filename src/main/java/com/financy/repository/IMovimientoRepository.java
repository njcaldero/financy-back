package com.financy.repository;

import com.financy.entity.Cuenta;
import com.financy.entity.Movimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface IMovimientoRepository extends JpaRepository<Movimiento, Long> {
    public List<Movimiento> findTop3ByCuentaOrderByIdDesc(Cuenta cuenta);
}

