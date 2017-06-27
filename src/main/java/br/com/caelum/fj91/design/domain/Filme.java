package br.com.caelum.fj91.design.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.Duration;

/**
 * Created by nando on 27/06/17.
 */
@Entity
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private Duration duracao;

    /**
     * @deprecated frameworks only
     */
    private Filme(){}

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
