package com.geretq.gerenciadorEstoque.service;

import com.geretq.gerenciadorEstoque.domain.Cliente;
import com.geretq.gerenciadorEstoque.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Random;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private final Random random = SecureRandom.getInstanceStrong();

    public ClienteService() throws NoSuchAlgorithmException {
        super();
    }

    public Cliente criarCliente(Cliente cliente) throws Exception {

        return clienteRepository.save(cliente);
    }

}