package com.usuarios.controller;

import com.usuarios.model.entity.Usuario;
import com.usuarios.service.IUsuarioService;
import com.usuarios.service.Implement.UsuarioServiceImpl;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UsuarioController {

    private final IUsuarioService usuarioService;

    private UsuarioController() {
        this.usuarioService = new UsuarioServiceImpl();
    }

    @GetMapping("/")
    public List<Usuario> usuarios() {
        return usuarioService.listar();
    }

    @GetMapping("/{identifiacion}")
    public ResponseEntity<?> detalle(@Valid @PathVariable String identifiacion, BindingResult result) {

        if (result.hasErrors()) {
            return validarErrores(result);
        }

        Optional<Usuario> usuario = usuarioService.buscar(identifiacion);
        if (usuario.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.guardar(usuario));
    }

    @PutMapping("/{identifiacion}")
    public ResponseEntity<?> actualizar(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable String identifiacion) {


        if (result.hasErrors()) {
            return validarErrores(result);
        }

        Optional<Usuario> u = usuarioService.buscar(identifiacion);
        if (u.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(201).body(usuarioService.guardar(usuario));
    }


    @DeleteMapping("/{identifiacion}")
    public ResponseEntity<?> delete(@PathVariable String identifiacion) {
        Optional<Usuario> u = usuarioService.buscar(identifiacion);
        if (u.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        usuarioService.eliminar(identifiacion);
        return ResponseEntity.noContent().build();
    }

        private ResponseEntity<Map<String, String>> validarErrores(BindingResult result) {
            Map<String, String> errores = new HashMap<>();
            result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));

            return ResponseEntity.badRequest().body(errores);
        }
}