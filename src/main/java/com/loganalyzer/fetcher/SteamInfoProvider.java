package com.loganalyzer.fetcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loganalyzer.api.GameInfoProvider;
import com.loganalyzer.model.datasource.SteamGameResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class SteamInfoProvider implements GameInfoProvider {
    HttpClient httpClient;
    ObjectMapper objectMapper;
    List<SteamGameResponse> steamGameResponseSteam;
    public SteamInfoProvider(HttpClient client, ObjectMapper mapper) {
        this.httpClient = client;
        this.objectMapper = mapper;
        this.steamGameResponseSteam = new ArrayList<SteamGameResponse>();
    }

    @Override
    public void fetchDataFromWeb(String appId) {
        System.out.println("Fetching steamDb data for appId: "+appId);
        try{
            fetchGameDetails(appId);
        }catch(Exception e){
            System.out.println("Error fetching steamDb data for appId: "+appId);
        }

    }
    private void fetchGameDetails(String appId) throws IOException, InterruptedException {

        if (appId == null || appId.isEmpty()) {
            throw new IllegalArgumentException("appId cannot be null or empty");
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://store.steampowered.com/api/appdetails?appids="+appId))
                .GET()
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        if (response.statusCode() != 200) {
            throw new RuntimeException("HTTP error: " + response.statusCode());
        }

        // Deserialisera JSON → Root
        objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //ar test = objectMapper.readValue(response.body(), GameDetails.class);
        this.steamGameResponseSteam.add(objectMapper.readValue(response.body(), SteamGameResponse.class));
    }
    public List<SteamGameResponse> getGameDetailsSteam() {
        return this.steamGameResponseSteam;
    }

}


