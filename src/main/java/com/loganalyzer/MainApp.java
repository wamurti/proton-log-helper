package com.loganalyzer;

import com.loganalyzer.api.GameInfoProvider;
import com.loganalyzer.api.LogFileLocator;
import com.loganalyzer.factory.GameInfoProviderFactory;
import com.loganalyzer.factory.LogLocatorFactory;
import com.loganalyzer.view.Presenter;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private final List<GameInfoProvider> gameInfoProviders;
    private final List<LogFileLocator> logFileLocators;


    public MainApp(List<GameInfoProvider> gameInfoProviders, List<LogFileLocator> logFileLocators) {
        this.gameInfoProviders = gameInfoProviders;
        this.logFileLocators = logFileLocators;
    }

    public void run() {

        //Collecting file paths
        List<String> foundAppIds = new ArrayList<>();
        for (LogFileLocator filePath : logFileLocators) {
            filePath.fetchFilePaths();
            foundAppIds.addAll(filePath.getAppIds());
        }

        System.out.println("Getting extra data from web.");

        //Collecting gameData
        for (GameInfoProvider source : gameInfoProviders) {

            source.fetchDataFromWebAsync(foundAppIds);
        }

        //Display relevant info
        Presenter.present(gameInfoProviders, logFileLocators);
    }

    public static void main(String[] args) {

        LogFileLocator protonFilePaths = LogLocatorFactory.createProtonLogLocatorSingleton();
        LogFileLocator amdFilePaths = LogLocatorFactory.createAmdLogLocatorSingleton();

        List<LogFileLocator> listOfAllFilePathsToLogs = new ArrayList<>();
        listOfAllFilePathsToLogs.add(protonFilePaths);
        listOfAllFilePathsToLogs.add(amdFilePaths);

        GameInfoProvider proton = GameInfoProviderFactory.createProtonDbSingleton();
        GameInfoProvider steam = GameInfoProviderFactory.createSteamDbSingleton();

        List<GameInfoProvider> listOfAllDataSources = new ArrayList<>();
        listOfAllDataSources.add(proton);
        listOfAllDataSources.add(steam);


        MainApp app = new MainApp(listOfAllDataSources, listOfAllFilePathsToLogs);

        app.run();
    }
}
