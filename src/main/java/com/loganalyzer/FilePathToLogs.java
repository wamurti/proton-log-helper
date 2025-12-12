package com.loganalyzer;

import java.nio.file.Path;
import java.util.List;

public interface FilePathToLogs {
    void fetchFilePaths();
    List<Path> getFilePaths();
    boolean isAmdHardwareLoggs();
    List<Path> getLogFiles();
}
