package br.com.caelum.fj91.design.domain;

import java.time.LocalTime;

/**
 * Created by nando on 27/06/17.
 */
public class Sessao implements Comparable<Sessao>{

    private Filme filme;
    private LocalTime horario;

    public Sessao(Filme filme, LocalTime horario) {
        this.filme = filme;
        this.horario = horario;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Sessao sessao = (Sessao) o;

        return horario.equals(sessao.horario);
    }

    @Override
    public int hashCode() {
        return horario.hashCode();
    }

    @Override
    public int compareTo(Sessao outraSessao) {
        return horario.compareTo(outraSessao.horario);
    }

    @Override
    public String toString() {
        return "Sessao{" +
                "filme=" + filme +
                ", horario=" + horario +
                '}';
    }

    public boolean colide(Sessao novaSessao) {

        if (this.equals(novaSessao)) return true;

        LocalTime inicioSessaoAtual = this.horario;
        LocalTime inicioNovaSessao = novaSessao.horario;

        boolean sessaoAtualEhAntes = inicioSessaoAtual.isBefore(inicioNovaSessao);

        if (sessaoAtualEhAntes){
            LocalTime terminoSessaoAtual = this.getTermino();
            return inicioNovaSessao.isBefore(terminoSessaoAtual);
        }else {
            LocalTime terminoNovaSessao = novaSessao.getTermino();
            return terminoNovaSessao.isAfter(inicioSessaoAtual);
        }

    }

    public LocalTime getTermino() {
        return horario.plus(filme.getDuracao());
    }
}
