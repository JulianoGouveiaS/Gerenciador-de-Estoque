package com.geretq.gerenciadorEstoque.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="tproduto")
public class Produto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "dtValidade")
    private Date dtValidade;

    @Column(name = "dtCadastro")
    private Date dtCadastro;

    @Column(name = "dtUltimaAlteracao")
    private Date dtUltimaAlteracao;

    @Column(name = "observacao")
    private String observacao;

    @ManyToOne
    @JoinColumn(name = "id")
    private Usuario usuarioAlteracao;

    @Column(name = "qtdeEstoque")
    private Boolean qtdeEstoque;

    public Produto() {
    }

    public Produto(Long id, String descricao, Date dtValidade, Date dtCadastro, Date dtUltimaAlteracao, String observacao, Usuario usuarioAlteracao, Boolean qtdeEstoque) {
        this.id = id;
        this.descricao = descricao;
        this.dtValidade = dtValidade;
        this.dtCadastro = dtCadastro;
        this.dtUltimaAlteracao = dtUltimaAlteracao;
        this.observacao = observacao;
        this.usuarioAlteracao = usuarioAlteracao;
        this.qtdeEstoque = qtdeEstoque;
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

    public Date getDtValidade() {
        return dtValidade;
    }

    public void setDtValidade(Date dtValidade) {
        this.dtValidade = dtValidade;
    }

    public Date getDtCadastro() {
        return dtCadastro;
    }

    public void setDtCadastro(Date dtCadastro) {
        this.dtCadastro = dtCadastro;
    }

    public Date getDtUltimaAlteracao() {
        return dtUltimaAlteracao;
    }

    public void setDtUltimaAlteracao(Date dtUltimaAlteracao) {
        this.dtUltimaAlteracao = dtUltimaAlteracao;
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

    public Boolean getQtdeEstoque() {
        return qtdeEstoque;
    }

    public void setQtdeEstoque(Boolean qtdeEstoque) {
        this.qtdeEstoque = qtdeEstoque;
    }
}
