package com.loganalyzer;

public interface GameDataSource {
    GameDataSource fetchDataFromWeb(String appId);
}
