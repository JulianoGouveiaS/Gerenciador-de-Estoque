package com.geretq.gerenciadorEstoque.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geretq.gerenciadorEstoque.domain.Produto;
import com.geretq.gerenciadorEstoque.service.ProdutoService;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) throws Exception{
        return ResponseEntity.ok().body(produtoService.salvar(produto));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Produto>> buscarTodos() {
    	return ResponseEntity.ok(produtoService.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable Long id) {
    	return ResponseEntity.ok(produtoService.buscarPorId(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> excluir(@PathVariable Long id) throws Exception {
    	return ResponseEntity.ok(produtoService.excluir(id));
    }
}
