package com.loganalyzer.models.datasource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.loganalyzer.GameDataSource;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class SteamDataSource implements GameDataSource {
    HttpClient httpClient;
    ObjectMapper objectMapper;
    List<GameDetails> gameDetailsSteam;
    public SteamDataSource(HttpClient client, ObjectMapper mapper) {
        this.httpClient = client;
        this.objectMapper = mapper;
        this.gameDetailsSteam = new ArrayList<GameDetails>();
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
        this.gameDetailsSteam.add(objectMapper.readValue(response.body(), GameDetails.class));
    }
    public List<GameDetails> getGameDetailsSteam() {
        return this.gameDetailsSteam;
    }

}


class Demo {
    private int appid;

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }
}
class Platforms {
    private boolean windows;
    private boolean mac;
    private boolean linux;

    public boolean isWindows() {
        return windows;
    }

    public void setWindows(boolean windows) {
        this.windows = windows;
    }

    public boolean isMac() {
        return mac;
    }

    public void setMac(boolean mac) {
        this.mac = mac;
    }

    public boolean isLinux() {
        return linux;
    }

    public void setLinux(boolean linux) {
        this.linux = linux;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;

        if (windows) {
            sb.append("Windows");
            first = false;
        }
        if (mac) {
            if (!first) sb.append(", ");
            sb.append("Mac");
            first = false;
        }
        if (linux) {
            if (!first) sb.append(", ");
            sb.append("Linux");
        }

        sb.append("}");
        return sb.toString();
    }
}
class Requirements {
    private String minimum;
    private String recommended;

    public String getMinimum() {
        return minimum;
    }

    public void setMinimum(String minimum) {
        this.minimum = minimum;
    }

    public String getRecommended() {
        return recommended;
    }

    public void setRecommended(String recommended) {
        this.recommended = recommended;
    }

    @Override
    public String toString() {
        return "Requirements{" +
                "minimum='" + minimum + '\'' +
                ", recommended='" + recommended + '\'' +
                '}';
    }
}
class SupportInfo {
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
