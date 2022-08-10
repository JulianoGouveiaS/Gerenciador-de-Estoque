package com.geretq.gerenciadorEstoque.controller;


import com.geretq.gerenciadorEstoque.domain.Produto;
import com.geretq.gerenciadorEstoque.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;


    @PostMapping(path = "/novo")
    public ResponseEntity<Produto> criarProduto(@RequestBody Produto produto) throws Exception{

        return ResponseEntity.ok().body(produtoService.criarProduto(produto));
    }



}
