package com.geretq.gerenciadorEstoque.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tmovimentacao")
public class Movimentacao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6660107810088488207L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@OneToOne
	@JoinColumn(name = "usuario", referencedColumnName = "id")
	private Usuario usuario;

	@Column(name = "tipo")
	private String tipo;

	@OneToOne
	@JoinColumn(name = "produto", referencedColumnName = "id")
	private Produto produto;

	@Column(name = "quantidade")
	private Long quantidade;

	@Column(name = "data")
	private Date data;

	public Movimentacao() {}

	public Movimentacao(Long id, Usuario usuario, String tipo, Produto produto, Long quantidade, Date data) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.tipo = tipo;
		this.produto = produto;
		this.quantidade = quantidade;
		this.data = data;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Long quantidade) {
		this.quantidade = quantidade;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
