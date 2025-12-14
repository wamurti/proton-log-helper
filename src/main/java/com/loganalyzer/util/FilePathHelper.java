package com.loganalyzer.util;

import com.loganalyzer.api.LogFileLocator;
import com.loganalyzer.LogType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

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

}
