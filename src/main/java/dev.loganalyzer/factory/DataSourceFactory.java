package dev.loganalyzer.factory;


import com.fasterxml.jackson.databind.ObjectMapper;
import dev.loganalyzer.GameDataSource;
import dev.loganalyzer.models.datasource.ProtonDbDataSource;
import dev.loganalyzer.models.datasource.SteamDataSource;

import java.net.http.HttpClient;

public class DataSourceFactory {
    private static ProtonDbDataSource protonDbDataSourceInstance;
    private static SteamDataSource steamDbDataSourceinstance;
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public static GameDataSource createProtonDbSingleton() {
        if(protonDbDataSourceInstance == null){
            return new ProtonDbDataSource(client,mapper);
        }
        return protonDbDataSourceInstance;

    }

    public static GameDataSource createSteamDbSingleton() {
        if(steamDbDataSourceinstance == null){
            return new SteamDataSource(client,mapper);
        }
        return steamDbDataSourceinstance;
    }


}
