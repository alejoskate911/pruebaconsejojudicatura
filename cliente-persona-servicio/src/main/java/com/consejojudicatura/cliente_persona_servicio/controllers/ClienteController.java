package com.consejojudicatura.cliente_persona_servicio.controllers;

import com.consejojudicatura.cliente_persona_servicio.entity.Cliente;
import com.consejojudicatura.cliente_persona_servicio.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> listaClientes(){
        return clienteService.listaClientes();
    }

    @PostMapping
    public ResponseEntity<Object> guardarCliente(@RequestBody Cliente cliente){
        return clienteService.guardarCliente(cliente);
    }

    @PutMapping
    public ResponseEntity<Object> actualizarCliente(@RequestBody Cliente cliente){
        return clienteService.actualizarCliente(cliente);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> eliminarCliente(@PathVariable UUID id){
        return clienteService.eliminarCliente(id);
    }

}
