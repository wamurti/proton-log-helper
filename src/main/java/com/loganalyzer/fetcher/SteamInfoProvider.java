package com.loganalyzer.fetcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loganalyzer.api.GameInfoProvider;
import com.loganalyzer.model.datasource.SteamGameResponse;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class SteamInfoProvider implements GameInfoProvider {
    HttpClient httpClient;
    ObjectMapper objectMapper;
    final List<SteamGameResponse> steamGameResponseSteam;

    public SteamInfoProvider(HttpClient client, ObjectMapper mapper) {
        this.httpClient = client;
        this.objectMapper = mapper;
        this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.steamGameResponseSteam = new ArrayList<>();
    }


    @Override
    public void fetchDataFromWebAsync(List<String> appIds) {
        System.out.println("Fetching steamDb data from web...");
        List<CompletableFuture<Void>> futures = appIds.stream()
                .map(this::fetchGameDetailsAsync)
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private CompletableFuture<Void> fetchGameDetailsAsync(String appId) {
        if (appId == null || appId.isEmpty()) {
            return CompletableFuture.completedFuture(null);
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://store.steampowered.com/api/appdetails?appids=" + appId))
                .GET()
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() == 200) {
                        try {
                            objectMapper.configure(
                                    com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
                                    false
                            );
                            synchronized (steamGameResponseSteam) {
                                steamGameResponseSteam.add(
                                        objectMapper.readValue(response.body(), SteamGameResponse.class)
                                );
                            }
                        } catch (Exception e) {
                            System.out.println("Error parsing response for appId: " + appId);
                        }
                    } else {
                        System.out.println("HTTP error " + response.statusCode() + " for appId: " + appId);
                    }
                })
                .exceptionally(e -> {
                    System.out.println("Error fetching data for appId: " + appId);
                    return null;
                });

    }

    public List<SteamGameResponse> getGameDetailsSteam() {
        return this.steamGameResponseSteam;
    }

}


