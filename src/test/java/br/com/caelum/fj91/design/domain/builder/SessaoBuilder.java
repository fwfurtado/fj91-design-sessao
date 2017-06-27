package br.com.caelum.fj91.design.domain.builder;

import br.com.caelum.fj91.design.domain.Filme;
import br.com.caelum.fj91.design.domain.Sessao;
import sun.jvm.hotspot.HelloWorld;

import java.time.LocalTime;

/**
 * Created by nando on 27/06/17.
 */
public class SessaoBuilder {
    public SessaoPartFilme paraOfilme(Filme filme) {

        return new SessaoPartFilme(filme);
    }

    public class SessaoPartFilme {
        private final Filme filme;

        SessaoPartFilme(Filme filme) {
            this.filme = filme;
        }

        public SessaoPartHoras as(int horas) {
            return new SessaoPartHoras(filme, horas);
        }

        public class SessaoPartHoras {
            private final Filme filme;
            private final int horas;

            SessaoPartHoras(Filme filme, int horas) {
                this.filme = filme;
                this.horas = horas;
            }

            public SessaoPartMinutos horas() {

                return new SessaoPartMinutos(filme, horas);
            }

            public class SessaoPartMinutos {
                private final Filme filme;
                private final int horas;

                SessaoPartMinutos(Filme filme, int horas) {
                    this.filme = filme;
                    this.horas = horas;
                }

                public SessaoPartBuild e(int minutos) {
                    return new SessaoPartBuild(filme, horas, minutos);
                }

                public class SessaoPartBuild {
                    private final Filme filme;
                    private final int horas;
                    private final int minutos;

                    SessaoPartBuild(Filme filme, int horas, int minutos) {
                        this.filme = filme;
                        this.horas = horas;
                        this.minutos = minutos;
                    }

                    public Builder minutos() {
                        return new Builder(filme, LocalTime.of(horas, minutos));
                    }

                    public class Builder {
                        private final Filme filme;
                        private final LocalTime horario;

                        Builder(Filme filme, LocalTime horario) {
                            this.filme = filme;
                            this.horario = horario;
                        }

                        public Sessao build(){
                            return new Sessao(filme, horario);
                        }
                    }
                }
            }
        }
    }
}
