package com.cursodevsuperior.desafio02.entities;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_atividade")
public class Atividade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;
    private Double preco;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "atividade", cascade = CascadeType.ALL)
    private Set<Bloco> blocos = new HashSet<>();

    @ManyToMany(mappedBy = "atividades")
    private Set<Participante> participantes = new HashSet<>();

    public Atividade() {
    }

    public Atividade(Integer id, Double preco, String descricao, String nome, Categoria categoria) {
        this.id = id;
        this.preco = preco;
        this.descricao = descricao;
        this.nome = nome;
        this.categoria = categoria;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Categoria getCategoria() {
        return categoria;
    }


    public Set<Bloco> getBlocos() {
        return blocos;
    }

    public Set<Participante> getParticipantes() {
        return participantes;
    }

}
