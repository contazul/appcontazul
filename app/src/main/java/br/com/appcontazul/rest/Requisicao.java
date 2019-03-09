package br.com.appcontazul.rest;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

import br.com.appcontazul.contentstatic.ReferenciaUsuario;
import br.com.appcontazul.rest.model.ListaContazul;
import br.com.appcontazul.rest.model.PerfilContazul;

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
}













