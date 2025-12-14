package com.loganalyzer.api;

import com.loganalyzer.LogType;

import java.nio.file.Path;
import java.util.List;

public interface LogFileLocator {
    void fetchFilePaths();
    LogType getLogType();
    List<Path> getLogFiles();
    List<String> getAppIds();
}
