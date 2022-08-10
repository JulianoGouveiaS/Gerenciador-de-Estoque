package com.geretq.gerenciadorEstoque.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.geretq.gerenciadorEstoque.domain.Movimentacao;
import com.geretq.gerenciadorEstoque.service.MovimentacaoService;

@RestController
@RequestMapping("movimentacoes")
public class MovimentacaoController {

    @Autowired
    private MovimentacaoService movimentacaoService;


    @PostMapping(path = "/novo")
    public ResponseEntity<Movimentacao> criarMovimentacao(@RequestBody Movimentacao movimentacao) throws Exception{

        return ResponseEntity.ok().body(movimentacaoService.criarMovimentacao(movimentacao));
    }



}
