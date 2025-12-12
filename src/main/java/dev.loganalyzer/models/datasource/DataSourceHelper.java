package dev.loganalyzer.models.datasource;

import dev.loganalyzer.GameDataSource;

import javax.sql.DataSource;
import java.util.List;

public class DataSourceHelper {
    public static ProtonDbDataSource getProtonDbDataSource(List<GameDataSource> dataSource) {
        return (ProtonDbDataSource) dataSource.stream().filter(gameDataSource -> gameDataSource instanceof ProtonDbDataSource).findFirst().get();
    }
    public static SteamDataSource getSteamDataSource(List<GameDataSource> dataSource) {
        return (SteamDataSource) dataSource.stream().filter(gameDataSource -> gameDataSource instanceof SteamDataSource).findFirst().get();

    }
}
