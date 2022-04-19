package com.financy.repository;

import com.financy.entity.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICuentaRepository extends JpaRepository<Cuenta, Long> {
    public Cuenta findByCedula(String cedula);
}
