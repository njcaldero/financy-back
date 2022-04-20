package com.financy.service;

import com.financy.entity.Cuenta;
import com.financy.entity.Movimiento;
import com.financy.repository.ICuentaRepository;
import com.financy.repository.IMovimientoRepository;
import com.financy.type.TipoMovimiento;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovimientoService implements IMovimientoService {

    private final IMovimientoRepository movimientoRepository;
    private final ICuentaRepository cuentaRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movimiento> GetAll() {
        List<Movimiento> listMovimientos = movimientoRepository.findAll();
        return listMovimientos;
    }

    @Override
    public Movimiento Create(Movimiento movimiento) throws Exception {

        Cuenta cuentaDB = cuentaRepository.findByCedula(movimiento.getCuenta().getCedula());
        if (cuentaDB == null) throw new Exception("El cliente no tiene una cuenta existente.");

        cuentaDB = cuentaRepository.getById(movimiento.getCuenta().getId());

        if (movimiento.getTipo_Transaccion().getId() == TipoMovimiento.CONSIGNAR.getId())
            cuentaDB.setSaldo(cuentaDB.getSaldo() + movimiento.getValor_transaccion());
        else {
            if (cuentaDB.getSaldo() < movimiento.getValor_transaccion())
                throw new Exception("El valor a retirar no debe ser mayor al valor del saldo en la cuenta.");

            cuentaDB.setSaldo(cuentaDB.getSaldo() - movimiento.getValor_transaccion());
        }

        cuentaRepository.save(cuentaDB);
        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<Movimiento> FindOrderedByFechaTransaccionLimitedTo(Long idCuenta) {
        List<Movimiento> listMovimientos = movimientoRepository.findTop3ByCuentaOrderByIdDesc(Cuenta.builder().id(idCuenta).build());
        return listMovimientos;
    }

}
