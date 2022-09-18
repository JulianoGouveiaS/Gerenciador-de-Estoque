package com.geretq.gerenciadorEstoque.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.geretq.gerenciadorEstoque.domain.Movimentacao;
import com.geretq.gerenciadorEstoque.domain.Produto;
import com.geretq.gerenciadorEstoque.domain.TipoMovimentacaoEnum;
import com.geretq.gerenciadorEstoque.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Transactional
    public Movimentacao salvar(Movimentacao movimentacao) throws Exception {
    	antesSalvar(movimentacao);
    	Produto produto = produtoService.buscarPorId(movimentacao.getIdProduto());
    	movimentarQuantidadeProduto(movimentacao, produto);
    	produtoService.salvar(produto);
        return movimentacaoRepository.save(movimentacao);
    }

	private void antesSalvar(Movimentacao movimentacao) throws Exception {
    	if (movimentacao == null) {
    		throw new Exception("Movimentação não informada");
    	}
    	if (movimentacao.getQuantidade() == null || movimentacao.getQuantidade() < 1) {
    		throw new Exception("Quantidade da movimentação deve ser maior que zero");
    	}
    	if (movimentacao.getTipo() == null) {
    		throw new Exception("Tipo da movimentação não informado");
    	}
    	if (movimentacao.getIdProduto() == null) {
    		throw new Exception("O produto não foi informado");
    	}
    	if (produtoService.buscarPorId(movimentacao.getIdProduto()) == null) {
    		throw new Exception("O produto informado não foi encontrado");
    	}
    	movimentacao.setData(new Date());
	}
	
	public void movimentarQuantidadeProduto(Movimentacao movimentacao, Produto produto) {
		if (TipoMovimentacaoEnum.ENTRADA.equals(movimentacao.getTipo())) {
	    	produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() + movimentacao.getQuantidade());
		} else if (TipoMovimentacaoEnum.SAIDA.equals(movimentacao.getTipo())) {
	    	produto.setQuantidadeEstoque(produto.getQuantidadeEstoque() - movimentacao.getQuantidade());
		}
	}

}