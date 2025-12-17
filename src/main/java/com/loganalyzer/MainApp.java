package com.loganalyzer;

import com.loganalyzer.api.GameInfoProvider;
import com.loganalyzer.api.LogFileLocator;
import com.loganalyzer.scanner.Troubleshooter;
import com.loganalyzer.factory.GameInfoProviderFactory;
import com.loganalyzer.factory.LogLocatorFactory;
import com.loganalyzer.factory.TroubleshooterFactory;
import com.loganalyzer.view.Presenter;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private final List<GameInfoProvider> gameInfoProviders;
    private final List<LogFileLocator> logFileLocators;
    private final Troubleshooter troubleshooter;

    public MainApp(List<GameInfoProvider> gameInfoProviders, List<LogFileLocator> logFileLocators, Troubleshooter troubleshooter) {
        this.gameInfoProviders = gameInfoProviders;
        this.logFileLocators = logFileLocators;
        this.troubleshooter = troubleshooter;
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

        //Scan the proton log files and collect errors
        troubleshooter.troubleShoot(logFileLocators);

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

        Troubleshooter troubleshooter = TroubleshooterFactory.createTroubleshooterSingleton();


        MainApp app = new MainApp(listOfAllDataSources, listOfAllFilePathsToLogs,troubleshooter);

        app.run();
    }
}
