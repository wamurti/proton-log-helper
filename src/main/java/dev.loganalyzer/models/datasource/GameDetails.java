package dev.loganalyzer.models.datasource;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.Map;

public class GameDetails {
    private Map<String, AppEntry> entries = new java.util.HashMap<>();

    @JsonAnySetter
    public void addEntry(String key, AppEntry value) {
        entries.put(key, value);
    }

    public Map<String, AppEntry> getEntries() {
        return entries;
    }
}
