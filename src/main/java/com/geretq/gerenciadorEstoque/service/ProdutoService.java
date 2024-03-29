package com.geretq.gerenciadorEstoque.service;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.geretq.gerenciadorEstoque.domain.Produto;
import com.geretq.gerenciadorEstoque.repository.ProdutoRepository;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public ProdutoService() throws NoSuchAlgorithmException {
        super();
    }

    @Transactional
    public Produto salvar(Produto produto) throws Exception {
    	antesSalvar(produto);
        return produtoRepository.save(produto);
    }
    
    private void antesSalvar(Produto produto) throws Exception {
    	if (produto == null) {
    		throw new Exception("Produto não informado");
    	}
    	if (!StringUtils.hasText(produto.getDescricao())) {
    		throw new Exception("Descrição do produto é obrigatória");
    	}
    	if (produto.getDataCadastro() == null) {
    		produto.setDataCadastro(new Date());
    	}
    	produto.setDataUltimaAlteracao(new Date());
    	if (produto.getQuantidadeEstoque() == null) {
        	produto.setQuantidadeEstoque(0L);
    	}    	
    }

	public List<Produto> buscarTodos() {
		return produtoRepository.findAll();
	}

	public Boolean excluir(Long id) throws Exception {
		try {
			produtoRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível excluir este produto.");
		}
		return Boolean.TRUE;
	}

	public Produto buscarPorId(Long idProduto) {
		return produtoRepository.findById(idProduto).orElse(null);
	}

}