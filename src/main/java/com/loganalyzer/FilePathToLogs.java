package com.loganalyzer;

import java.nio.file.Path;
import java.util.List;

public interface FilePathToLogs {
    void fetchFilePaths();
    LogType getLogType();
    List<Path> getLogFiles();
    List<String> getAppIds();
}
