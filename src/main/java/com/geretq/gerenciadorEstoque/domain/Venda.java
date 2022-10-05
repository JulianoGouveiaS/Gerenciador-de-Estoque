package com.geretq.gerenciadorEstoque.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tvenda")
public class Venda implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "data")
	private Date data;

	@ManyToOne
	@JoinColumn(name = "cliente", referencedColumnName = "id", insertable = false, updatable = false)
	private Cliente cliente;

	@OneToMany(mappedBy = "venda", cascade=CascadeType.ALL)
	private List<Movimentacao> movimentacoes;

	public Venda() {
	}

	public Venda(Long id, Date data, Cliente cliente, List<Movimentacao> movimentacoes) {
		this.id = id;
		this.data = data;
		this.cliente = cliente;
		this.movimentacoes = movimentacoes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public List<Movimentacao> getMovimentacoes() {
		return movimentacoes;
	}

	public void setMovimentacoes(List<Movimentacao> movimentacoes) {
		this.movimentacoes = movimentacoes;
	}
}
