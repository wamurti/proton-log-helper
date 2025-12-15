package com.loganalyzer.factory;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.loganalyzer.api.GameInfoProvider;
import com.loganalyzer.fetcher.ProtonDbInfoProvider;
import com.loganalyzer.fetcher.SteamInfoProvider;

import java.net.http.HttpClient;

public class GameInfoProviderFactory {
    private static ProtonDbInfoProvider protonDbDataSourceInstance;
    private static SteamInfoProvider steamDbDataSourceinstance;
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();


    public static GameInfoProvider createProtonDbSingleton() {
        if(protonDbDataSourceInstance == null){
            return new ProtonDbInfoProvider(client,mapper);
        }
        return protonDbDataSourceInstance;

    }

    public static GameInfoProvider createSteamDbSingleton() {
        if(steamDbDataSourceinstance == null){
            return new SteamInfoProvider(client,mapper);
        }
        return steamDbDataSourceinstance;
    }


}
