package com.geretq.gerenciadorEstoque.controller;


import com.geretq.gerenciadorEstoque.domain.Cliente;
import com.geretq.gerenciadorEstoque.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;


    @PostMapping(path = "/novo")
    public ResponseEntity<Cliente> criarCliente(@RequestBody Cliente cliente) throws Exception{

        return ResponseEntity.ok().body(clienteService.criarCliente(cliente));
    }



}
