package dev.loganalyzer;

import dev.loganalyzer.factory.DataSourceFactory;
import dev.loganalyzer.factory.FilePathFactory;
import dev.loganalyzer.models.filepath.FilePathsToProtonLogs;
import dev.loganalyzer.view.Presenter;

import java.util.ArrayList;
import java.util.List;

public class MainApp {
    private final List<GameDataSource> gameDetailsSources;
    private final List<FilePathToLogs> filePathsToLogs;


    public MainApp(List<GameDataSource> gameDetailsSources, List<FilePathToLogs> filePathsToLogs) {
        this.gameDetailsSources = gameDetailsSources;
        this.filePathsToLogs = filePathsToLogs;
    }

    public void run() {

        //Collecting file paths
        List<String> foundAppIds = new ArrayList<>();
        for (FilePathToLogs filePath : filePathsToLogs) {
            filePath.fetchFilePaths();
            if(filePath.getClass().equals(FilePathsToProtonLogs.class)){
                foundAppIds = ((FilePathsToProtonLogs) filePath).getAppIds();
            }
        }

        System.out.println("Getting extra data from web.");

        //Collecting gameData from web
        for (GameDataSource source : gameDetailsSources) {
            try{
                for(String appId : foundAppIds){
                    source.fetchDataFromWeb(appId);
                }
            } catch (Exception e) {
                System.out.println("Error while fetching data "+e.getMessage());
            }
        }

        //Display relevant info to user
        Presenter.present(gameDetailsSources, filePathsToLogs);
    }
    public static void main(String[] args) {
        // Skapa och hämta filePaths för proton och hårdvaruloggar
        FilePathToLogs protonFilePaths = FilePathFactory.createProtonLogsSingleton();
        FilePathToLogs amdFilePaths = FilePathFactory.createAmdHardwareLogsSingleton();

        List<FilePathToLogs> listOfAllFilePathsToLogs = new ArrayList<>();
        listOfAllFilePathsToLogs.add(protonFilePaths);
        listOfAllFilePathsToLogs.add(amdFilePaths);

        //Skapa och hämta data från filerna
        GameDataSource proton = DataSourceFactory.createProtonDbSingleton();
        GameDataSource steam = DataSourceFactory.createSteamDbSingleton();

        List<GameDataSource> listOfAllDataSources = new ArrayList<>();
        listOfAllDataSources.add(proton);
        listOfAllDataSources.add(steam);



        MainApp app = new MainApp(listOfAllDataSources,listOfAllFilePathsToLogs);

        app.run();
    }
}
