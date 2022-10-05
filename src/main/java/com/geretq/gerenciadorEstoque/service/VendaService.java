package com.geretq.gerenciadorEstoque.service;

import com.geretq.gerenciadorEstoque.domain.Venda;
import com.geretq.gerenciadorEstoque.repository.VendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@Service
public class VendaService {

    @Autowired
    private VendaRepository vendaRepository;

    public VendaService() throws NoSuchAlgorithmException {
        super();
    }

    @Transactional
    public Venda salvar(Venda venda) throws Exception {
    	antesSalvar(venda);
        return vendaRepository.save(venda);
    }

    private void antesSalvar(Venda venda) throws Exception {
    	if (venda == null) {
    		throw new Exception("Venda não informado");
    	}
		venda.setData(new Date());
    	if (venda.getCliente() == null) {
			throw new Exception("Cliente não informado");
    	}
    }

	public List<Venda> buscarTodos() {
		return vendaRepository.findAll();
	}

	public Boolean excluir(Long id) throws Exception {
		try {
			vendaRepository.deleteById(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Não foi possível excluir este venda.");
		}
		return Boolean.TRUE;
	}

	public Venda buscarPorId(Long idVenda) {
		return vendaRepository.findById(idVenda).orElse(null);
	}

}
