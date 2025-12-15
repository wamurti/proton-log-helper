package com.loganalyzer.fetcher;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.loganalyzer.api.GameInfoProvider;
import com.loganalyzer.model.datasource.ProtonDbDetails;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CopyOnWriteArrayList;

public class ProtonDbInfoProvider implements GameInfoProvider {
    HttpClient httpClient;
    ObjectMapper objectMapper;
    List<ProtonDbDetails> protonDbDetails;

    public ProtonDbInfoProvider(HttpClient client, ObjectMapper mapper) {
        this.httpClient = client;
        this.objectMapper = mapper;
        this.objectMapper.configure(com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.protonDbDetails = new CopyOnWriteArrayList<>();
    }

    public void fetchDataFromWebAsync(List<String> appIds) {
        System.out.println("Fetching protonDb data from web...");
        List<CompletableFuture<Void>> futures = appIds.stream()
                .map(this::fetchGameDetailsAsync)
                .toList();

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
    }

    private CompletableFuture<Void> fetchGameDetailsAsync(String appId) {
        if (appId == null || appId.isEmpty()) {
            return CompletableFuture.failedFuture(new IllegalArgumentException("appId cannot be null or empty"));
        }

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://www.protondb.com/api/v1/reports/summaries/" + appId + ".json"))
                .GET()
                .build();

        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenAccept(response -> {
                    if (response.statusCode() != 200) {
                        System.err.println("HTTP error for appId " + appId + ": " + response.statusCode());
                        return;
                    }
                    try {
                        ProtonDbDetails details = objectMapper.readValue(response.body(), ProtonDbDetails.class);
                        details.setAppId(appId);
                        protonDbDetails.add(details);
                    } catch (IOException e) {
                        System.err.println("Parse error for appId " + appId + ": " + e.getMessage());
                    }
                })
                .exceptionally(ex -> {
                    System.err.println("Failed for appId " + appId + ": " + ex.getMessage());
                    return null;
                });
    }

    public List<ProtonDbDetails> getProtonDbDetails() {
        return this.protonDbDetails;
    }
}

