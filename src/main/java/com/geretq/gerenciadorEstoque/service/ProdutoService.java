package com.geretq.gerenciadorEstoque.service;

import com.geretq.gerenciadorEstoque.domain.Produto;
import com.geretq.gerenciadorEstoque.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    private final Random random = SecureRandom.getInstanceStrong();

    public ProdutoService() throws NoSuchAlgorithmException {
        super();
    }

    public Produto criarProduto(Produto produto) throws Exception {

        return produtoRepository.save(produto);
    }

}