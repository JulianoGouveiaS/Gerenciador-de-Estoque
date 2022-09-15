package com.geretq.gerenciadorEstoque.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.geretq.gerenciadorEstoque.domain.Usuario;
import com.geretq.gerenciadorEstoque.service.UsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController{

    @Autowired
    private UsuarioService usuarioService;


    @PostMapping(path = "/novo")
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) throws Exception{
        return ResponseEntity.ok().body(usuarioService.criarUsuario(usuario));
    }

    @GetMapping("{id}")
    public ResponseEntity<Usuario> findById(@PathVariable Long id) throws Exception{
        return ResponseEntity.ok().body(usuarioService.buscarPorId(id));
    }

    @GetMapping(path = "/todos")
    public ResponseEntity<List<Usuario>> buscarUsuarios() throws Exception{
        return ResponseEntity.ok().body(usuarioService.buscarTodos());
    }



}
