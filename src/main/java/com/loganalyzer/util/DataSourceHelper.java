package com.loganalyzer.util;

import com.loganalyzer.api.GameInfoProvider;
import com.loganalyzer.fetcher.ProtonDbInfoProvider;
import com.loganalyzer.fetcher.SteamInfoProvider;

import java.util.List;

public class DataSourceHelper {
    public static ProtonDbInfoProvider getProtonDbDataSource(List<GameInfoProvider> dataSource) {
        return (ProtonDbInfoProvider) dataSource.stream().filter(gameDataSource -> gameDataSource instanceof ProtonDbInfoProvider).findFirst().get();
    }
    public static SteamInfoProvider getSteamDataSource(List<GameInfoProvider> dataSource) {
        return (SteamInfoProvider) dataSource.stream().filter(gameDataSource -> gameDataSource instanceof SteamInfoProvider).findFirst().get();

    }
}
