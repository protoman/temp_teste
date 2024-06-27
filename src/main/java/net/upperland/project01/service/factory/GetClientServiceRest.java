package net.upperland.project01.service.factory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.upperland.project01.model.dto.ClientDTO;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
@RequiredArgsConstructor
public class GetClientServiceRest implements GetClientInterface {
    @Override
    public ClientDTO findClientById(Integer id) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://echo.jsontest.com/id/152/name/Client/gender/FEMALE"))
                .build();
        HttpResponse<String> response = HttpClient.newBuilder().proxy(ProxySelector.getDefault()).build().send(request, HttpResponse.BodyHandlers.ofString());
        return clientFromJsonString(response.body());
    }

    public ClientDTO clientFromJsonString(String text) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(text, ClientDTO.class);
    }
}
