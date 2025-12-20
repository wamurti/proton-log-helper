package com.loganalyzer.util;

import com.loganalyzer.api.LogFileLocator;
import com.loganalyzer.LogType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FilePathHelper {

    public static List<Path> getPathsByLogType(List<LogFileLocator> logFilePathToLogs, LogType logType) {
        return logFilePathToLogs.stream()
                .filter(fp -> fp.getLogType() == logType)
                .flatMap(fp -> fp.getLogFiles().stream())
                .toList();
    }

    public static List<Path> convertFilePathsToLogs_ToPaths(List<LogFileLocator> logFilePathToLogs) {
        return logFilePathToLogs.stream()
                .flatMap(f -> f.getLogFiles().stream())
                .toList();
    }

    public static List<Path> getFilesInFolder(Path basePath) throws IOException {
        if (Files.notExists(basePath)) {
            System.out.println("The path does not exist: " + basePath);
            return List.of();
        }

        try (var stream = Files.list(basePath)) {
            return stream
                    .filter(Files::isRegularFile)
                    .toList();
        }
    }

    public static String extractAppIdFromLogPath(Path logPath){
        String fileName = logPath.getFileName().toString();
        return fileName.replaceAll("\\D+", "");
    }
    public static Map<Path, List<String>> getLogsContent(List<LogFileLocator> logFilePathToLogs) {
        List<Path> protonLogs = convertFilePathsToLogs_ToPaths(logFilePathToLogs);
        System.out.println("Getting file contents for Proton logs\n");
        try {
            Map<Path, List<String>> result = new HashMap<>();
            for (Path path : protonLogs) {
                if(Files.size(path) != 0){
                    result.put(path, Files.readAllLines(path));
                }else {
                    System.out.println("File is empty: "+path+" Skipping..");
                }

            }
            return result;
        }
        catch (IOException e) {
            System.out.println("Failed to read log files: " + e);
        }
        return new HashMap<>();
    }

}
