package com.citas.client;


import com.citas.model.Usuario;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "usuarios-mcsv", url = "${mcsv.cursos.url}")
public interface UsuarioClientREST {

    @GetMapping("/{id}")
    Usuario detalle(@PathVariable Long id);
}
