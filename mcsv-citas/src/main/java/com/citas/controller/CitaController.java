package com.citas.controller;

import com.citas.model.entity.Cita;
import com.citas.service.ICitaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class CitaController {

    @Autowired
    private ICitaService citaService;


    @GetMapping("/")
    public List<Cita> citas() {
        return citaService.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Cita> c = citaService.detalle(id);
        if (c.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(c.get());
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Cita cita, BindingResult result) {

        if (result.hasErrors()) return validarErrores(result);

        Cita c = citaService.guardar(cita);
        return ResponseEntity.status(HttpStatus.CREATED).body(c);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Cita cita, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) return validarErrores(result);

        Optional<Cita> c = citaService.detalle(id);
        if (c.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        cita.setId(c.get().getId());

        return ResponseEntity.ok(citaService.guardar(cita));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Cita> c = citaService.detalle(id);
        if (c.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        citaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Map<String, String>> validarErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errores);
    }
}
