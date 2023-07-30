package org.example;

import java.util.ArrayList;

public class Pessoa {
    private String nome;
    private ArrayList<Endereco> enderecos;

    public Pessoa(String nome) {
        this.nome = nome;
        this.enderecos = new ArrayList<>();
    }

    public void adicionarEndereco(Endereco endereco) {
        enderecos.add(endereco);
    }

    public String getNome() {
        return nome;
    }

    public ArrayList<Endereco> getEnderecos() {
        return enderecos;
    }
}

