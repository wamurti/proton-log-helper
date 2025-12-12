package dev.loganalyzer;

public interface GameDataSource {
    GameDataSource fetchDataFromWeb(String appId);
}
