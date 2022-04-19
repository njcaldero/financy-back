package com.financy.service;

import com.financy.entity.Movimiento;
import com.financy.repository.IMovimientoRepository;
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
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Movimiento> GetAll() {
        List<Movimiento>  listMovimientos= movimientoRepository.findAll();
       // Collections.sort(listMovimientos);
       return listMovimientos;
    }

    @Override
    public Movimiento Create(Movimiento movimiento) throws Exception {
        return movimientoRepository.save(movimiento);
    }

    @Override
    public List<Movimiento> FindOrderedByFechaTransaccionLimitedTo() {
        List<Movimiento>  listMovimientos= movimientoRepository.findAll(Sort.by(Sort.Direction.DESC, "id")).stream().limit(3).collect(Collectors.toList());

        return listMovimientos;
    }

}
