package com.loganalyzer.models.filepath;

import com.loganalyzer.FilePathToLogs;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import static com.loganalyzer.models.filepath.FilePathHelper.listFilesInPath;

public class FilePathsToAmdHardwareLogs implements FilePathToLogs {
    List<Path> logFiles;
    @Override
    public boolean isAmdHardwareLoggs() { return true; }
    @Override
    public void fetchFilePaths() {
        System.out.println("Fetching file paths for Amd hardware");
        scanForAmdLogFiles();
    }

    @Override
    public List<Path> getFilePaths() {
        return this.logFiles;
    }

    private void scanForAmdLogFiles() {
        List<Path> allFiles;
        Path path = Paths.get(System.getProperty("user.home"),"somefolder");
        System.out.println("Validating path: "+path);


        try {
            allFiles = listFilesInPath(path);
        } catch (IOException e) {
            System.out.println("Failed to list files in path:" + path + e);
            return;
        }
        if(!allFiles.isEmpty()){
            System.out.println("Found home path: "+path+" \n*Start searching for AMD logfiles*");
        }

        //TODO: Find out what logs that should be filtered out
        logFiles = allFiles.stream()
                .filter(p -> p.toString().endsWith(".log"))
                .filter(p -> p.toString().contains("someAmdSpecificName-"))
                // Framtida AMD-specifik filtrering här
                .toList();

        if (logFiles.isEmpty()) {
            System.out.println("No AMD logs found in path: "+ path);
            return;
        }
        System.out.println("Found "+logFiles.size()+" AMD log files in path: "+ path);
    }

    public List<Path> getLogFiles() {
        return logFiles;
    }

    @Override
    public String toString() {
        return "FilePathsToAmdHardwareLogs{" +
                "logFiles=" + logFiles +
                '}';
    }
}
