package com.loganalyzer.model.datasource;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Map;

public class SteamGameResponse {
    private Map<String, SteamAppEntry> entries = new java.util.HashMap<>();

    @JsonAnySetter
    public void addEntry(String key, SteamAppEntry value) {
        entries.put(key, value);
    }

    public Map<String, SteamAppEntry> getEntries() {
        return entries;
    }
}
