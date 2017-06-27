package br.com.caelum.fj91.design.domain;

import br.com.caelum.fj91.design.domain.builder.FilmeBuilder;
import br.com.caelum.fj91.design.domain.exception.SessaoInvalidaException;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.Matchers.contains;
import static org.junit.Assert.assertThat;

/**
 * Created by nando on 27/06/17.
 */
public class SalaTest {


    private Sala eldorado7;
    private Filme logan;
    private Filme rogueOne;

    @Before
    public void setup(){
        eldorado7 = new Sala("Eldorado 7");

        FilmeBuilder filme = new FilmeBuilder();

        logan = filme
                .comTitulo("Logan")
                    .eDuracaoDe(90)
                        .minutos()
                            .build();
        rogueOne = filme
                        .comTitulo("Rogue One")
                            .eDuracaoDe(120)
                                .minutos()
                                    .build();


    }

    @Test
    public void deveSerPossivelAdicionarUmaSessaoNaSala(){


        LocalTime quinzeETrina = LocalTime.of(15, 30);

        Sessao sessaoDasQuinzeETrinta = new Sessao(logan, quinzeETrina);

        eldorado7.add(sessaoDasQuinzeETrinta);

        assertThat(eldorado7.getSessoes(), contains(sessaoDasQuinzeETrinta));

    }


    @Test(expected = SessaoInvalidaException.class)
    public void naoDeveSerPossivelAdicionarDuasSessoesNoMesmoHorario(){

        LocalTime quinzeETrina = LocalTime.of(15, 30);
        Sessao sessaoDasQuinzeETrinta = new Sessao(logan, quinzeETrina);
        Sessao outraSessaoDasQuinzeETrinta = new Sessao(rogueOne, quinzeETrina);

        eldorado7.add(sessaoDasQuinzeETrinta);
        eldorado7.add(outraSessaoDasQuinzeETrinta);
    }


    @Test(expected = SessaoInvalidaException.class)
    public void naoDeveSerPossivelAdicionarUmaSegundaSessaoQueInicieDuranteOHorarioDeUmaSessaoExistente(){
        LocalTime quinzeETrina = LocalTime.of(15, 30);
        Sessao sessaoDasQuinzeETrinta = new Sessao(logan, quinzeETrina);

        LocalTime dezeseisETrinta = LocalTime.of(16, 30);
        Sessao outraSessaoDasQuinzeETrinta = new Sessao(rogueOne, dezeseisETrinta);

        eldorado7.add(sessaoDasQuinzeETrinta);
        eldorado7.add(outraSessaoDasQuinzeETrinta);
    }

    @Test(expected = SessaoInvalidaException.class)
    public void naoDeveSerPossivelAdicionarUmaSegundaSessaoQueTermineDuranteUmaSessaoExistente(){

        LocalTime dezeseisETrinta = LocalTime.of(16, 30);
        Sessao sessaoDasQuinzeETrinta = new Sessao(logan, dezeseisETrinta);

        LocalTime quinzeETrina = LocalTime.of(15, 30);
        Sessao outraSessaoDasQuinzeETrinta = new Sessao(rogueOne, quinzeETrina);

        eldorado7.add(sessaoDasQuinzeETrinta);
        eldorado7.add(outraSessaoDasQuinzeETrinta);

    }
}
