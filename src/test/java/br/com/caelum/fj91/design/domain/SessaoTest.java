package br.com.caelum.fj91.design.domain;

import br.com.caelum.fj91.design.domain.builder.FilmeBuilder;
import br.com.caelum.fj91.design.domain.builder.SessaoBuilder;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalTime;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Created by nando on 27/06/17.
 */
public class SessaoTest {

    private FilmeBuilder filmeBuilder = new FilmeBuilder();
    private SessaoBuilder sessaoBuilder = new SessaoBuilder();
    private Filme logan;
    private Filme rogueOne;

    @Before
    public void setup(){
        logan = filmeBuilder
                .comTitulo("Logan")
                    .eDuracaoDe(90)
                        .minutos()
                            .build();

        rogueOne = filmeBuilder
                    .comTitulo("Rogue One")
                        .eDuracaoDe(120)
                            .minutos()
                                .build();

    }


    @Test
    public void umaSessaoIniciadaAs15e30ParaUmFilmeComDuracaoDe90MinutosDeveTerminarAs17Horas(){
        Sessao sessao = sessaoBuilder.paraOfilme(logan).as(15).horas().e(30).minutos().build();

        assertThat(sessao.getTermino(), is(equalTo(LocalTime.of(17,0))));
    }


    @Test
    public void dadoUmaSessaoAs15e30ParaUmFilmeComDuracaoDe90MinutosDeveColidirComOutraSessaoIniciadaAs16e30ComDuracaoDe120Minutos(){
        Sessao sessaoDas15e30 = sessaoBuilder.paraOfilme(logan).as(15).horas().e(30).minutos().build();
        Sessao sessaoDas16e30 = sessaoBuilder.paraOfilme(rogueOne).as(16).horas().e(30).minutos().build();

        assertTrue(sessaoDas15e30.colide(sessaoDas16e30));
    }


    @Test
    public void dadoUmaSessaoAs16e30ParaUmFilmeComDuracaoDe90MinutosDeveColidirComOutraSessaoIniciadaAs15e30ComDuracaoDe120Minutos(){
        Sessao sessaoDas16e30 = sessaoBuilder.paraOfilme(logan).as(16).horas().e(30).minutos().build();
        Sessao sessaoDas15e30 = sessaoBuilder.paraOfilme(rogueOne).as(15).horas().e(30).minutos().build();

        assertTrue(sessaoDas15e30.colide(sessaoDas16e30));
    }

    @Test
    public void dadoUmaSessaoAs16e30ParaUmFilmeComDuracaoDe90MinutosNaoDeveColidirComOutraSessaoIniciadaAs13e30ComDuracaoDe120Minutos(){
        Sessao sessaoDas16e30 = sessaoBuilder.paraOfilme(logan).as(16).horas().e(30).minutos().build();
        Sessao sessaoDas13e30 = sessaoBuilder.paraOfilme(rogueOne).as(13).horas().e(30).minutos().build();

        assertFalse(sessaoDas13e30.colide(sessaoDas16e30));
    }


    @Test
    public void dadoUmaSessaoAs16e30ParaUmFilmeComDuracaoDe90MinutosNaoDeveColidirComOutraSessaoIniciadaAs19ComDuracaoDe120Minutos(){
        Sessao sessaoDas16e30 = sessaoBuilder.paraOfilme(logan).as(16).horas().e(30).minutos().build();
        Sessao sessaoDas19 = sessaoBuilder.paraOfilme(rogueOne).as(19).horas().e(0).minutos().build();

        assertFalse(sessaoDas16e30.colide(sessaoDas19));
    }
}
