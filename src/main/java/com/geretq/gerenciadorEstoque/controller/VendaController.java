package com.geretq.gerenciadorEstoque.controller;


import com.geretq.gerenciadorEstoque.domain.Venda;
import com.geretq.gerenciadorEstoque.service.VendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vendas")
public class VendaController {

    @Autowired
    private VendaService vendaService;

    @PostMapping
    public ResponseEntity<Venda> criarVenda(@RequestBody Venda venda) throws Exception{
        return ResponseEntity.ok().body(vendaService.salvar(venda));
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Venda>> buscarTodos() {
    	return ResponseEntity.ok(vendaService.buscarTodos());
    }

    @GetMapping("{id}")
    public ResponseEntity<Venda> buscarPorId(@PathVariable Long id) {
    	return ResponseEntity.ok(vendaService.buscarPorId(id));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Boolean> excluir(@PathVariable Long id) throws Exception {
    	return ResponseEntity.ok(vendaService.excluir(id));
    }
}
