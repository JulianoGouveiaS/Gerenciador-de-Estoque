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
@Table(name="tproduto")
public class Produto implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dataValidade")
    private Date dataValidade;

    @Column(name = "dataCadastro")
    private Date dataCadastro;

    @Column(name = "dataUltimaAlteracao")
    private Date dataUltimaAlteracao;

    @Column(name = "observacao")
    private String observacao;

    @OneToOne
    @JoinColumn(name = "usuarioAlteracao", referencedColumnName = "id")
    private Usuario usuarioAlteracao;

    @Column(name = "quantidadeEstoque")
    private Boolean quantidadeEstoque;

    public Produto() {
    }

    public Produto(Long id, String descricao, Date dtValidade, Date dtCadastro, Date dtUltimaAlteracao, String observacao, Usuario usuarioAlteracao, Boolean qtdeEstoque) {
        this.id = id;
        this.descricao = descricao;
        this.dataValidade = dtValidade;
        this.dataCadastro = dtCadastro;
        this.dataUltimaAlteracao = dtUltimaAlteracao;
        this.observacao = observacao;
        this.usuarioAlteracao = usuarioAlteracao;
        this.quantidadeEstoque = qtdeEstoque;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getDataValidade() {
        return dataValidade;
    }

    public void setDataValidade(Date dtValidade) {
        this.dataValidade = dtValidade;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dtCadastro) {
        this.dataCadastro = dtCadastro;
    }

    public Date getDataUltimaAlteracao() {
        return dataUltimaAlteracao;
    }

    public void setDataUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dataUltimaAlteracao = dtUltimaAlteracao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public Usuario getUsuarioAlteracao() {
        return usuarioAlteracao;
    }

    public void setUsuarioAlteracao(Usuario usuarioAlteracao) {
        this.usuarioAlteracao = usuarioAlteracao;
    }

    public Boolean getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(Boolean qtdeEstoque) {
        this.quantidadeEstoque = qtdeEstoque;
    }
}
