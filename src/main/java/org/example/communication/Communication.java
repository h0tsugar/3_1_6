package org.example.communication;

import org.example.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class Communication implements CommunicationInterface{

    private final RestTemplate restTemplate;
    private final String URL = "http://94.198.50.185:7081/api/users";
    private final HttpHeaders headers = new HttpHeaders();

    public Communication(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public String getHeader() {
        ResponseEntity<List<User>> response = restTemplate.exchange(URL, HttpMethod.GET,
                null, new ParameterizedTypeReference<List<User>>() {});
        return response.getHeaders().getFirst("Set-Cookie");
    }

    @Override
    public String getCodePartOne(String setCookieHeaders) {
        User userJames = new User(3L,"James", "Brown", (byte)20);
        headers.set("Cookie", setCookieHeaders);
        RequestEntity<User> request = RequestEntity
                .post(URI.create(URL))
                .headers(headers)
                .body(userJames);

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return response.getBody();
    }

    @Override
    public String getCodePartTwo(String setCookieHeaders) {
        User userTomas = new User(3L, "Thomas", "Shelby", (byte)20);
        headers.set("Cookie", setCookieHeaders);
        RequestEntity<User> request = RequestEntity
                .put(URI.create(URL))
                .headers(headers)
                .body(userTomas);

        ResponseEntity<String> response = restTemplate.exchange(request, String.class);
        return response.getBody();
    }

    @Override
    public String getCodePartThree(String setCookieHeader, int id) {
        headers.set("Cookie", setCookieHeader);
        RequestEntity<Void> request = RequestEntity
                .delete(URI.create(URL + "/" + id))
                .headers(headers)
                .build();

        ResponseEntity<String> response2 = restTemplate.exchange(request, String.class);
        return response2.getBody();
    }
}
