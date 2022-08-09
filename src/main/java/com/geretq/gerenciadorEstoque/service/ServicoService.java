package com.geretq.gerenciadorEstoque.service;

import com.geretq.gerenciadorEstoque.domain.Servico;
import com.geretq.gerenciadorEstoque.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class ServicoService {

    @Autowired
    private ServicoRepository servicoRepository;

    private final Random random = SecureRandom.getInstanceStrong();

    public ServicoService() throws NoSuchAlgorithmException {
        super();
    }

    public Servico criarServico(Servico servico) throws Exception {

        return servicoRepository.save(servico);
    }

}