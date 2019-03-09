package br.com.appcontazul.rest;

import org.springframework.http.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

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
}
