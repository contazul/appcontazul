package br.com.appcontazul.contentstatic;

import java.util.ArrayList;
import java.util.List;

import br.com.appcontazul.rest.model.InclusaoDivida;
import br.com.appcontazul.rest.model.ListaSubtracaoSaldo;
import br.com.appcontazul.rest.model.SimuladorEntrada;
import br.com.appcontazul.util.model.ListaSimuladorNovoBeneficio;

public class Simulador {

    public static List<ListaSubtracaoSaldo> listaSimulacao = new ArrayList<>();

    public static SimuladorEntrada simuladorEntrada;

    public static List<InclusaoDivida> dividasSimuladas;

    public static long[] dividas;

    public static List<ListaSimuladorNovoBeneficio> beneficiosNovos;
}
