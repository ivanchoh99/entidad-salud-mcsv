package com.usuarios.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "citas-mcsv", url = "localhost:8002")
public interface CitasClientRest {
}
