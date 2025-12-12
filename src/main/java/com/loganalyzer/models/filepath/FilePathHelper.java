package com.loganalyzer.models.filepath;

import com.loganalyzer.FilePathToLogs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FilePathHelper {

    public static List<Path> getAmdPaths(List<FilePathToLogs> filePathToLogs) {
        return filePathToLogs.stream()
                .filter(FilePathToLogs::isAmdHardwareLoggs)
                .map(FilePathToLogs::getLogFiles)
                .findFirst()
                .orElse(null);
    }

    public static List<Path> convertFilePathsToLogs_ToPaths(List<FilePathToLogs> filePathToLogs) {
        return filePathToLogs.stream()
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
