package com.usuarios.controller;

import com.usuarios.model.entity.Usuario;
import com.usuarios.service.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UsuarioController {

    @Autowired
    private IUsuarioService service;

    @GetMapping("/")
    public List<Usuario> usuarios() {
        return service.listar();
    }

    @GetMapping("/{identifiacion}")
    public ResponseEntity<?> detalle(@Valid @PathVariable String identifiacion) {


        Optional<Usuario> usuario = service.buscar(identifiacion);
        if (usuario.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(usuario);
    }

    @PostMapping("/")
    public ResponseEntity<?> crear(@Valid @RequestBody Usuario usuario, BindingResult result) {

        if (result.hasErrors()) return validarErrores(result);

        if (service.existeIdentificacion(usuario.getIdentificacion())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mesaje", "Esta identificacion ya existe"));
        }

        if (service.existeCorreo(usuario.getEmail())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "Este correo ya esta registrado"));
        }


        return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(usuario));
    }

    @PutMapping("/{identifiacion}")
    public ResponseEntity<?> actualizar(@PathVariable String identifiacion, @Valid @RequestBody Usuario usuario, BindingResult result) {


        if (result.hasErrors()) {
            return validarErrores(result);
        }

        Optional<Usuario> optionalUsuario = service.buscar(identifiacion);

        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

//TODO  Vaida:  1. Hay un usuario que tiene este correo registrado en la DB
//TODO          2. Que el usuarioDB y el usuario ingresado sean DIFERENTES
        if (service.existeCorreo(usuario.getEmail()) && !usuario.getEmail().equalsIgnoreCase(optionalUsuario.get().getEmail())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mensaje", "Este correo ya esta registrado"));
        }

        if (service.existeIdentificacion(usuario.getIdentificacion()) && !usuario.getIdentificacion().equalsIgnoreCase(optionalUsuario.get().getIdentificacion())) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("mesaje", "Esta identificacion ya existe"));
        }

        usuario.setId(optionalUsuario.get().getId());

        return ResponseEntity.ok(service.guardar(usuario));
    }


    @DeleteMapping("/{identifiacion}")
    public ResponseEntity<?> delete(@PathVariable String identifiacion) {
        Optional<Usuario> u = service.buscar(identifiacion);
        if (u.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        service.eliminar(identifiacion);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Map<String, String>> validarErrores(BindingResult result) {
        Map<String, String> errores = new HashMap<>();
        result.getFieldErrors().forEach(err -> errores.put(err.getField(), err.getDefaultMessage()));

        return ResponseEntity.badRequest().body(errores);
    }

}