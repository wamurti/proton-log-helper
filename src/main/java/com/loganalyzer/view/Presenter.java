package com.loganalyzer.view;

import com.loganalyzer.FilePathToLogs;
import com.loganalyzer.GameDataSource;
import com.loganalyzer.models.datasource.*;
import com.loganalyzer.models.filepath.FilePathHelper;
import com.loganalyzer.models.filepath.FilePathsToProtonLogs;

import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class Presenter {
    public static void present(List<GameDataSource> allDataSources, List<FilePathToLogs> allFilePaths){

        List<ProtonDbDetails> protonDbDetails = DataSourceHelper.getProtonDbDataSource(allDataSources).getProtonDbDetails();
        List<GameDetails> gameDetails = DataSourceHelper.getSteamDataSource(allDataSources).getGameDetailsSteam();

        List<AppData> gameDetailsSteam =
                gameDetails.stream()
                        .flatMap(gd -> gd.getEntries().values().stream())  // AppEntry
                        .map(AppEntry::getData)
                        .filter(Objects::nonNull)
                        .toList();


        List<Path> amdPaths = FilePathHelper.getAmdPaths(allFilePaths);
        List<Path> allFiles = FilePathHelper.convertFilePathsToLogs_ToPaths(allFilePaths);
        List<String> appIds =
                allFilePaths.stream()
                        .filter(FilePathsToProtonLogs.class::isInstance)
                        .map(FilePathsToProtonLogs.class::cast)
                        .flatMap(fp -> fp.getAppIds().stream())
                        .toList();

        System.out.println("Gathered a total of " + allFiles.size() + " files:");
        for(Path p : allFiles){
            System.out.println(p.toString());
        }
        System.out.println();

        System.out.println("Games found: "+appIds.size());
        for(AppData appData : gameDetailsSteam){
            String appIdString = String.valueOf(appData.getSteam_appid());
            System.out.println(appData.getName()+" --> "+appData.getSteam_appid());
            protonDbDetails.stream().filter(pd-> pd.getAppId().equals(appIdString)).findFirst().ifPresent(pd->{
                System.out.println("Tier: "+pd.getTrendingTier()+"\nScore: "+pd.getScore()+"\nReviews: "+pd.getTotal());
                System.out.println("Native support for: "+appData.getPlatforms());
                System.out.println();
            });
        }
        System.out.println("---------------------------------------");
        System.out.println();
        System.out.println("Amd Logs found: "+amdPaths.size());

        //För Amd


    }
}
