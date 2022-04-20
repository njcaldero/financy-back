package com.financy.service;
import com.financy.entity.Cuenta;
import com.financy.entity.Movimiento;
import com.financy.entity.TipoTransaccion;
import com.financy.repository.ICuentaRepository;
import com.financy.repository.IMovimientoRepository;
import com.financy.type.TipoMovimiento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CuentaService implements ICuentaService {

    private final ICuentaRepository productRepository;
    private final IMovimientoRepository movimientoRepository;

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
        if (cuentaDB != null) throw new Exception("El cliente ya existe.");

        cuenta.setSaldo(cuenta.getValor_apertura());
        Cuenta cuentaNew = productRepository.save(cuenta);
        movimientoRepository.save(Movimiento.builder()
                .cuenta(cuentaNew)
                .valor_transaccion(cuentaNew.getValor_apertura())
                .tipo_Transaccion(TipoTransaccion.builder().id(TipoMovimiento.CONSIGNAR.getId()).build())
                .fecha_transaccion(new Date())
                .build());
        return cuentaNew;

    }
}
