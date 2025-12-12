package com.loganalyzer.models.filepath;

import com.loganalyzer.FilePathToLogs;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.loganalyzer.models.filepath.FilePathHelper.extractAppIdFromLogPath;
import static com.loganalyzer.models.filepath.FilePathHelper.listFilesInPath;


public class FilePathsToProtonLogs implements FilePathToLogs {
    @Override
    public boolean isAmdHardwareLoggs() { return false; }

    public List<String> getAppIds() {
        return appIds;
    }

    List<Path> logFiles;
    List<String> appIds;
    public List<Path> getLogFiles() {
        return logFiles;
    }
    @Override
    public void fetchFilePaths() {
        System.out.println("Fetching file paths for Proton logs");
        scanForProtonLogFiles();
    }

    @Override
    public List<Path> getFilePaths() {
        return logFiles;
    }

    private void scanForProtonLogFiles() {
        List<Path> allFiles;
        Path path = Paths.get(System.getProperty("user.home"));
        System.out.println("Validating path: "+path);


        try {
            allFiles = listFilesInPath(path);
        } catch (IOException e) {
            System.out.println("Failed to list files in path:" + path + e);
            return;
        }
        if(!allFiles.isEmpty()){
            System.out.println("Found home path: "+path+" \n*Start searching for proton logfiles*");
        }

        logFiles = allFiles.stream()
                .filter(p -> p.toString().endsWith(".log"))
                .filter(p -> p.toString().contains("steam-"))
                // Framtida Proton-specifik filtrering här
                .toList();

        if (logFiles.isEmpty()) {
            System.out.println("No proton logs found in path: "+ path);
            return;
        }
        List<String> foundAppId = new ArrayList<>();

        for(Path logFilePath : logFiles) {
            foundAppId.add(extractAppIdFromLogPath(logFilePath));
        }
        appIds = foundAppId;
        System.out.println("Found "+appIds.size()+" proton log files in path: "+ path);
    }


    @Override
    public String toString() {
        return "FilePathsToProtonLogs{" +
                "logFiles=" + logFiles +
                '}';
    }
}
