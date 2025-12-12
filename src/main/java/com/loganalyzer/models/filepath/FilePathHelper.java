package com.loganalyzer.models.filepath;

import com.loganalyzer.FilePathToLogs;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FilePathHelper {

    public static List<Path> getAmdPaths(List<FilePathToLogs> filePathToLogs) {
        return filePathToLogs.stream()
                .filter(FilePathToLogs::isAmdHardwareLoggs)
                .map(FilePathToLogs::getLogFiles)
                .findFirst()
                .orElse(null);
    }

    public static List<Path> convertFilePathsToLogs_ToPaths(List<FilePathToLogs> filePathToLogs) {
        List<Path> allFiles = new ArrayList<>();
        //För proton
        for(FilePathToLogs fp : filePathToLogs){
            var tempList = fp.getFilePaths();
            allFiles.addAll(tempList);
        }
        return allFiles;
    }

    public static List<Path> listFilesInPath(Path basePath) throws IOException {
        if (!Files.exists(basePath)) {
            System.out.println("The path: " + basePath + " does not exist");
            return List.of();
        }

        try (Stream<Path> stream = Files.list(basePath)) {
            return stream.filter(Files::isRegularFile).toList();
        }
    }

    public static String extractAppIdFromLogPath(Path logPath){
        String fileName = logPath.getFileName().toString();
        return fileName.replaceAll("\\D+", "");
    }

}
