package br.com.caelum.fj91.design.domain;

import br.com.caelum.fj91.design.domain.exception.SessaoInvalidaException;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by nando on 27/06/17.
 */
@Entity
public class Sala {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    @OneToMany
    private Set<Sessao> sessoes = new HashSet<>();

    public Sala(String nome) {
        this.nome = nome;
    }

    public void add(Sessao sessao) {

        if (sessaoEhValida(sessao)) {
            sessoes.add(sessao);
        }else {
            String message = String.format("A Sessao %s sobrepõe uma sessao existente.", sessao);
            throw new SessaoInvalidaException(message);
        }
    }

    private boolean sessaoEhValida(Sessao novaSessao) {
        return sessoes.stream().noneMatch(novaSessao::colide);
    }

    public Set<Sessao> getSessoes() {
        return Collections.unmodifiableSet(sessoes);
    }

    @Override
    public String toString() {
        return "Sala{" +
                "nome='" + nome + '\'' +
                ", sessoes=" + sessoes +
                '}';
    }
}