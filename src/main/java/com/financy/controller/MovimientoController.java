package com.financy.controller;

import com.financy.entity.Cuenta;
import com.financy.entity.Movimiento;
import com.financy.service.CuentaService;
import com.financy.service.MovimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/movimiento")
@RequiredArgsConstructor
public class MovimientoController {

    private final MovimientoService movimientoService;

    @PostMapping
    public ResponseEntity<Movimiento> Create(@Valid @RequestBody Movimiento movimiento, BindingResult result) throws Exception {
        try {
            if (result.hasErrors())
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

            Movimiento movimientoDB = movimientoService.Create(movimiento);
            return ResponseEntity.status(HttpStatus.CREATED).body(movimientoDB);

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, ex.getMessage());
        }

    }

    @GetMapping()
    public ResponseEntity<List<Movimiento>> GetAll() {

        List<Movimiento> listMovimiento = movimientoService.GetAll();

        return ResponseEntity.ok(listMovimiento);

    }

    @GetMapping("/latest")
    public ResponseEntity<List<Movimiento>> GetLatest() {

        List<Movimiento> listMovimiento = movimientoService.FindOrderedByFechaTransaccionLimitedTo();

        return ResponseEntity.ok(listMovimiento);

    }
}
