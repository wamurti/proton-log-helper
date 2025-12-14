package com.loganalyzer.locator;

import com.loganalyzer.api.LogFileLocator;
import com.loganalyzer.LogType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import static com.loganalyzer.util.FilePathHelper.getFilesInFolder;

public class AmdLogLocator implements LogFileLocator {
    List<Path> logFiles;
    @Override
    public LogType getLogType() { return LogType.AMD_HARDWARE; }
    @Override
    public void fetchFilePaths() {
        System.out.println("Fetching file paths for Amd hardware");
        scanForAmdLogFiles();
    }

    private void scanForAmdLogFiles() {
        List<Path> allFiles;
        Path path = Paths.get(System.getProperty("user.home"),"somefolder");
        System.out.println("Validating path: "+path);


        try {
            allFiles = getFilesInFolder(path);
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
    public List<String> getAppIds() {
        return Collections.emptyList();
    }

    @Override
    public String toString() {
        return "FilePathsToAmdHardwareLogs{" +
                "logFiles=" + logFiles +
                '}';
    }
}
