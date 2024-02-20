package com.citas.controller;

import com.citas.model.entity.Cita;
import com.citas.service.ICitaService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class CitaController {

    private final ICitaService service;

//?     Se puede remplazar cuando se usa lombok por la anotacion que genera el constructor con todos los argumentos
//    public CitaController(ICitaService service) {
//        this.service = service;
//    }


    @GetMapping("/")
    public List<Cita> citas() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalle(@PathVariable Long id) {
        Optional<Cita> c = service.detalle(id);
        if (c.isEmpty()) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(c.get());
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@RequestBody Cita cita, BindingResult result) {
        if (result.hasErrors()) return validarErrores(result);
        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(cita));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@RequestBody Cita cita, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) return validarErrores(result);

        Optional<Cita> oC = service.detalle(id);
        if (oC.isEmpty()) return ResponseEntity.notFound().build();
        cita.setId(oC.get().getId());

        return ResponseEntity.ok(service.guardar(cita));
    }

    @PutMapping("asigMedico/{citaId}")
    public ResponseEntity<?> asignarMedico(@PathVariable Long citaId, @RequestParam Long medicoId) {
        if (citaId <= 0 || medicoId == null || medicoId <= 0) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.asignarMedico(citaId, medicoId));
    }

    @PutMapping("asigPaciente/{citaId}")
    public ResponseEntity<?> asignarPaciente(@PathVariable Long citaId, @RequestParam Long pacienteId) {
        if (citaId <= 0 || pacienteId == null || pacienteId <= 0) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(service.asignarPaciente(citaId, pacienteId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        Optional<Cita> c = service.detalle(id);
        if (c.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Map<String, String>> validarErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errores);
    }
}
