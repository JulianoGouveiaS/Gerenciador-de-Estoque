package com.geretq.gerenciadorEstoque.service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.geretq.gerenciadorEstoque.domain.Movimentacao;
import com.geretq.gerenciadorEstoque.repository.MovimentacaoRepository;

@Service
public class MovimentacaoService {

    @Autowired
    private MovimentacaoRepository movimentacaoRepository;

    private final Random random = SecureRandom.getInstanceStrong();

    public MovimentacaoService() throws NoSuchAlgorithmException {
        super();
    }

    public Movimentacao criarMovimentacao(Movimentacao movimentacao) throws Exception {

        return movimentacaoRepository.save(movimentacao);
    }

}