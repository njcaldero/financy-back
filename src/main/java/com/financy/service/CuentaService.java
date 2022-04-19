package com.financy.service;

import com.financy.entity.Cuenta;
import com.financy.repository.ICuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaService implements ICuentaService {

    private final ICuentaRepository productRepository;

    @Override
    public List<Cuenta> GetAll() {
        return productRepository.findAll();
    }

    @Override
    public Cuenta Get(long id) {
        if (productRepository.existsById(id))
            return productRepository.getById(id);
        return null;
    }

    @Override
    public Cuenta Create(Cuenta cuenta) throws Exception {
        Cuenta cuentaDB = productRepository.findByCedula(cuenta.getCedula());
        if (cuentaDB == null)
            return productRepository.save(cuenta);
        else
            throw new Exception("El cliente ya existe.");

    }
}
