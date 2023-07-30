package org.example;

public class Pedido {
    Cliente cliente;
    boolean encerrado;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.encerrado = false;
    }

    public void encerrarPedido() {
        this.encerrado = true;
    }
}

