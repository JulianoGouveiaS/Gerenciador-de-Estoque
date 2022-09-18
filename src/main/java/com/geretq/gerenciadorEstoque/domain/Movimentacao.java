package com.geretq.gerenciadorEstoque.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@Enumerated(EnumType.STRING)
	private TipoMovimentacaoEnum tipo;

	@ManyToOne
	@JoinColumn(name = "produto", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonIgnore
	private Produto produto;
	
	@Column(name = "produto")
	private Long idProduto;

	@Column(name = "quantidade")
	private Long quantidade;

	@Column(name = "data")
	private Date data;

	public Movimentacao() {}

	public Movimentacao(Long id, Usuario usuario, TipoMovimentacaoEnum tipo, Produto produto, Long quantidade, Date data) {
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

	public TipoMovimentacaoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoMovimentacaoEnum tipo) {
		this.tipo = tipo;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
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
