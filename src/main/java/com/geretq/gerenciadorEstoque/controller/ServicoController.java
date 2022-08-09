package com.geretq.gerenciadorEstoque.controller;


import com.geretq.gerenciadorEstoque.domain.Servico;
import com.geretq.gerenciadorEstoque.service.ServicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("servicos")
public class ServicoController {

    @Autowired
    private ServicoService servicoService;


    @PostMapping(path = "/novo")
    public ResponseEntity<Servico> criarServico(@RequestBody Servico servico) throws Exception{

        return ResponseEntity.ok().body(servicoService.criarServico(servico));
    }



}
