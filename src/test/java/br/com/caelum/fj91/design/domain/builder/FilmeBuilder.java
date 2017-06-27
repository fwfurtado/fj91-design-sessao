package br.com.caelum.fj91.design.domain.builder;

import br.com.caelum.fj91.design.domain.Filme;

import java.time.Duration;

/**
 * Created by nando on 27/06/17.
 */
public class FilmeBuilder {


    public FilmePartTitulo comTitulo(String titulo) {
        return new FilmePartTitulo(titulo);
    }


    public class FilmePartTitulo {

        private final String titulo;

        FilmePartTitulo(String titulo) {
            this.titulo = titulo;
        }


        public FilmePartDuracao eDuracaoDe(int duration) {
            return new FilmePartDuracao(titulo, duration);
        }

        public class FilmePartDuracao {
            private final String titulo;
            private final int duracao;

            FilmePartDuracao(String titulo, int duracao) {
                this.titulo = titulo;
                this.duracao = duracao;
            }


            public Builder minutos() {
                return new Builder(titulo, Duration.ofMinutes(duracao));
            }

            public class Builder {
                private final String titulo;
                private final Duration duracao;

                Builder(String titulo, Duration duracao) {

                    this.titulo = titulo;
                    this.duracao = duracao;
                }


                public Filme build() {
                    return new Filme(titulo, duracao);
                }
            }
        }
    }
}
