package com.loganalyzer.models.datasource;

import com.loganalyzer.GameDataSource;

import java.util.List;

public class DataSourceHelper {
    public static ProtonDbDataSource getProtonDbDataSource(List<GameDataSource> dataSource) {
        return (ProtonDbDataSource) dataSource.stream().filter(gameDataSource -> gameDataSource instanceof ProtonDbDataSource).findFirst().get();
    }
    public static SteamDataSource getSteamDataSource(List<GameDataSource> dataSource) {
        return (SteamDataSource) dataSource.stream().filter(gameDataSource -> gameDataSource instanceof SteamDataSource).findFirst().get();

    }
}
