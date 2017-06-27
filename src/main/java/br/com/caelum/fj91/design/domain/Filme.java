package br.com.caelum.fj91.design.domain;

import java.time.Duration;
import java.time.LocalTime;

/**
 * Created by nando on 27/06/17.
 */
public class Filme {

    private String titulo;
    private Duration duracao;

    public Filme(String titulo, Duration duracao) {
        this.titulo = titulo;
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "titulo='" + titulo + '\'' +
                ", duracao=" + duracao +
                '}';
    }

    public Duration getDuracao() {
        return duracao;
    }
}
