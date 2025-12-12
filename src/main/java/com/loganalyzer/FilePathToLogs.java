package com.loganalyzer;

import java.nio.file.Path;
import java.util.List;

public interface FilePathToLogs {
    void fetchFilePaths();
    boolean isAmdHardwareLoggs();
    List<Path> getLogFiles();
}
