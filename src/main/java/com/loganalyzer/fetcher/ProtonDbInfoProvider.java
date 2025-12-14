package com.loganalyzer.fetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loganalyzer.api.GameInfoProvider;
import com.loganalyzer.model.datasource.ProtonDbDetails;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class ProtonDbInfoProvider implements GameInfoProvider {
    HttpClient httpClient;
    ObjectMapper objectMapper;
    List<ProtonDbDetails> protonDbDetails;

    public ProtonDbInfoProvider(HttpClient client, ObjectMapper mapper) {
        this.httpClient = client;
        this.objectMapper = mapper;
        this.protonDbDetails = new ArrayList<ProtonDbDetails>();
    }

    @Override
    public void fetchDataFromWeb(String appId) {
        System.out.println("Fetching protonDb data for appId: " + appId);
        try {
            fetchGameDetails(appId);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void fetchGameDetails(String appId) throws IOException, InterruptedException {

        if (appId == null || appId.isEmpty()) {
            throw new IllegalArgumentException("appId cannot be null or empty");
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.protondb.com/api/v1/reports/summaries/" + appId + ".json"))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP error: " + response.statusCode());
        }

        objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        ProtonDbDetails protonDbDetails = objectMapper.readValue(response.body(), ProtonDbDetails.class);
        protonDbDetails.setAppId(appId);
        this.protonDbDetails.add(protonDbDetails);
    }
    public List<ProtonDbDetails> getProtonDbDetails() {
        return this.protonDbDetails;
    }
}

