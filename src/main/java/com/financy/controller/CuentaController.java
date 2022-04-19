package com.financy.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.financy.entity.Cuenta;
import com.financy.exception.CustomException;
import com.financy.exception.ErrorMessage;
import com.financy.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/cuenta")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;

    @PostMapping
    public ResponseEntity<Cuenta> Create(@Valid @RequestBody Cuenta cuenta, BindingResult result) throws Exception {
        try {
            if (result.hasErrors()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
            }
            Cuenta cuentaDB = cuentaService.Create(cuenta);
            return ResponseEntity.status(HttpStatus.CREATED).body(cuentaDB);
        } catch (Exception ex) {
          throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, ex.getMessage(),ex);
        }

    }

    @GetMapping()
    public ResponseEntity<List<Cuenta>> GetAll() {
        List<Cuenta> listCuentas = cuentaService.GetAll();


        return ResponseEntity.ok(listCuentas);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Cuenta> Get(@PathVariable("id") Long id) {
        Cuenta cuenta = cuentaService.Get(id);

        if (cuenta == null)
            return ResponseEntity.notFound().build();

        return ResponseEntity.ok().body(cuenta);
    }


    private String formatMessage(BindingResult result) {
        List<Map<String, String>> errors = result.getFieldErrors().stream()
                .map(err -> {
                    Map<String, String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());
                    return error;

                }).collect(Collectors.toList());
        ErrorMessage errorMessage = ErrorMessage.builder()
                .code("01")
                .messages(errors).build();
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = "";
        try {
            jsonString = mapper.writeValueAsString(errorMessage);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonString;
    }
}
