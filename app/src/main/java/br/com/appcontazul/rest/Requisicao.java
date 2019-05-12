package br.com.appcontazul.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import br.com.appcontazul.contentstatic.ReferenciaUsuario;
import br.com.appcontazul.contentstatic.Simulador;
import br.com.appcontazul.rest.model.Central;
import br.com.appcontazul.rest.model.ListaContasAPagar;
import br.com.appcontazul.rest.model.ListaContazul;
import br.com.appcontazul.rest.model.ListaDividaFixa;
import br.com.appcontazul.rest.model.ListaLucroMensal;
import br.com.appcontazul.rest.model.ListaMeta;
import br.com.appcontazul.rest.model.ListaSomaSaldo;
import br.com.appcontazul.rest.model.ListaSubtracaoSaldo;
import br.com.appcontazul.rest.model.PerfilContazul;
import br.com.appcontazul.rest.model.Saldo;
import br.com.appcontazul.rest.model.SimuladorEntrada;
import br.com.appcontazul.rest.model.SimuladorSaida;
import br.com.appcontazul.util.model.ListaSimuladorAdesaoDivida;

public class Requisicao {

    public String requestEx01(String nomeUsuario) {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String url = credenciaisWsContazul.getPathEpEx01();
        return restTemplate.getForObject(url, String.class, nomeUsuario);
    }

    public void requestInserirUsuario(String nomeUsuario, String senha) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpInserirUsuario();
        url = url.replace("{nomeUsuario}", nomeUsuario);
        url = url.replace("{senha}", senha);
        restTemplate.postForObject(url, null, Void.class);
    }

    public String requestEx08(String nomeUsuario, String senha) {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String url = credenciaisWsContazul.getPathEpEx08();
        return restTemplate.getForObject(url, String.class, nomeUsuario, senha);
    }

    public void requestInserirContazul() {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpInserirContazul();
        url = url.replace("{nomeUsuario}", ReferenciaUsuario.nomeUsuarioLogado);
        restTemplate.postForObject(url, null, Void.class);
    }

    public List<ListaContazul> requestListaContazul() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpListaDeContazul();
        ResponseEntity<List<ListaContazul>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListaContazul>>() {},
                ReferenciaUsuario.nomeUsuarioLogado);

        List<ListaContazul> listaContazul = response.getBody();
        return listaContazul;
    }

    public PerfilContazul requestPerfilConta() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String url = credenciaisWsContazul.getPathPerfilContazul();
        return restTemplate.getForObject(url, PerfilContazul.class, ReferenciaUsuario.numeroContazul);
    }

    public void requestAtualizarPerfilContazul(String descricao, String valorIdeal) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpAtualizarPerfilContazul();
        url = url.replace("{numeroContazul}", "" + ReferenciaUsuario.numeroContazul);
        url = url.replace("{descricao}", descricao);
        url = url.replace("{valorIdeal}", valorIdeal);
        restTemplate.postForObject(url, null, Void.class);
    }

    public Saldo requestSaldo() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String url = credenciaisWsContazul.getPathEpSaldo();
        return restTemplate.getForObject(url, Saldo.class, ReferenciaUsuario.numeroContazul);
    }

    public void requestInserirSomaSaldo(String descricao, String valor) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpInserirSomaSaldo();
        url = url.replace("{numeroContazul}", "" + ReferenciaUsuario.numeroContazul);
        url = url.replace("{descricao}", descricao);
        url = url.replace("{valor}", valor);
        restTemplate.postForObject(url, null, Void.class);
    }

    public List<ListaSomaSaldo> requestListaSomaSaldo() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpListaSomaSaldo();
        ResponseEntity<List<ListaSomaSaldo>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListaSomaSaldo>>() {},
                ReferenciaUsuario.numeroContazul);

        List<ListaSomaSaldo> listaSomaSaldo = response.getBody();
        return listaSomaSaldo;
    }

    public void requestInserirSubtracaoSaldo(String valor, String descricao, String prioridade) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpInserirSubtracaoSaldo();
        url = url.replace("{numeroContazul}", "" + ReferenciaUsuario.numeroContazul);
        url = url.replace("{valor}", valor);
        url = url.replace("{descricao}", descricao);
        url = url.replace("{prioridade}", prioridade);
        restTemplate.postForObject(url, null, Void.class);
    }

    public List<ListaSubtracaoSaldo> requestListaSubtracaoSaldo() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpListaDeSubtracaoSaldo();
        ResponseEntity<List<ListaSubtracaoSaldo>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListaSubtracaoSaldo>>() {},
                ReferenciaUsuario.numeroContazul);

        List<ListaSubtracaoSaldo> listaSubtracaoSaldo = response.getBody();
        return listaSubtracaoSaldo;
    }

    public void requestInserirLucroMensal(String descricao, String valor) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpInserirLucroMensal();
        url = url.replace("{numeroContazul}", "" + ReferenciaUsuario.numeroContazul);
        url = url.replace("{descricao}", descricao);
        url = url.replace("{valor}", valor);
        restTemplate.postForObject(url, null, Void.class);
    }

    public List<ListaLucroMensal> requestListaLucroMensal() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpListaDeLucroMensal();
        ResponseEntity<List<ListaLucroMensal>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListaLucroMensal>>() {},
                ReferenciaUsuario.numeroContazul);

        List<ListaLucroMensal> listaLucroMensal = response.getBody();
        return listaLucroMensal;
    }

    public void requestReceber(String id) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpReceber();
        url = url.replace("{id}", id);
        url = url.replace("{numeroContazul}", "" + ReferenciaUsuario.numeroContazul);
        restTemplate.postForObject(url, null, Void.class);
    }

    public void requestExcluirLucroMensal(String id) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpExcluirLucroMensal();
        url = url.replace("{id}", id);
        restTemplate.postForObject(url, null, Void.class);
    }

    public Central requestInformacoesCentral() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
        String url = credenciaisWsContazul.getPathEpInformacoesCentral();
        return restTemplate.getForObject(url, Central.class, ReferenciaUsuario.numeroContazul);
    }

    public void requestIncluirConta(String descricao, double valor, String prioridade,
                                    int quantidadeParcela) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpIncluirConta();
        url = url.replace("{descricao}", descricao);
        url = url.replace("{valor}", "" + valor);
        url = url.replace("{prioridade}", prioridade);
        url = url.replace("{quantidadeParcela}", "" + quantidadeParcela);
        url = url.replace("{numeroContazul}", "" + ReferenciaUsuario.numeroContazul);
        restTemplate.postForObject(url, null, Void.class);
    }

    public List<ListaContasAPagar> requestListaDeDividaMensal() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpListaDeDividaMensal();
        ResponseEntity<List<ListaContasAPagar>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListaContasAPagar>>() {},
                ReferenciaUsuario.numeroContazul);

        List<ListaContasAPagar> listaContasAPagar = response.getBody();
        return listaContasAPagar;
    }

    public void requestPagarDivida(long id) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpPagarDivida();
        url = url.replace("{id_divida_mensal}", "" + id);
        restTemplate.postForObject(url, null, Void.class);
    }

    public void requestExcluirDivida(long id) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpExcluirDivida();
        url = url.replace("{id_divida_mensal}", "" + id);
        restTemplate.delete(url, null, Void.class);
    }

    public void requestQuitarDivida(long id) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpQuitarDivida();
        url = url.replace("{id_divida_mensal}", "" + id);
        restTemplate.postForObject(url, null, Void.class);
    }

    public void requestIncluirMeta(String descricao, double valor, int isAvista,
                                   double valorEconomizar, int quantidadeParcela) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpIncluirMeta();
        url = url.replace("{descricao}", descricao);
        url = url.replace("{valor}", "" + valor);
        url = url.replace("{isAvista}", "" + isAvista);
        url = url.replace("{valorEconomizar}", "" + valorEconomizar);
        url = url.replace("{quantidadeParcela}", "" + quantidadeParcela);
        url = url.replace("{numeroContazul}", "" + ReferenciaUsuario.numeroContazul);
        restTemplate.postForObject(url, null, Void.class);
    }

    public List<ListaMeta> requestListaMeta() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpListaMeta();
        ResponseEntity<List<ListaMeta>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListaMeta>>() {},
                ReferenciaUsuario.numeroContazul);

        List<ListaMeta> listaMeta = response.getBody();
        return listaMeta;
    }

    public void requestAplicarMeta(long id) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpAplicar();
        url = url.replace("{id}", "" + id);
        restTemplate.postForObject(url, null, Void.class);
    }

    public void requestExcluirMeta(long id) {

        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_FORM_URLENCODED));
        restTemplate.getMessageConverters().add(converter);
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpExcluir();
        url = url.replace("{id}", "" + id);
        restTemplate.postForObject(url, null, Void.class);
    }

    public SimuladorSaida requestSimular() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpSimular();
        ResponseEntity<SimuladorSaida> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<SimuladorSaida>() {},
                ReferenciaUsuario.numeroContazul,
                Simulador.simuladorEntrada.getTotalDivida(),
                Simulador.simuladorEntrada.getTotalDividaRemovida(),
                Simulador.simuladorEntrada.getTotalBeneficio(),
                Simulador.simuladorEntrada.getTotalBeneficioRemovido(),
                Simulador.simuladorEntrada.getIdMeta(),
                Simulador.simuladorEntrada.isSimulandoMeta()
        );

        SimuladorSaida saida = response.getBody();
        return saida;
    }

    public List<ListaDividaFixa> requestListaDividaFixa() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpListarDividaFixa();
        ResponseEntity<List<ListaDividaFixa>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListaDividaFixa>>() {},
                ReferenciaUsuario.numeroContazul);

        List<ListaDividaFixa> lista = response.getBody();
        return lista;
    }

    public List<ListaMeta> requestListaMetaStatus01() {

        RestTemplate restTemplate = new RestTemplate();
        CredenciaisWsContazul credenciaisWsContazul = new CredenciaisWsContazul();
        String url = credenciaisWsContazul.getPathEpListaMetaStatus01();
        ResponseEntity<List<ListaMeta>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<ListaMeta>>() {},
                ReferenciaUsuario.numeroContazul);

        List<ListaMeta> listaMeta = response.getBody();
        return listaMeta;
    }
}













