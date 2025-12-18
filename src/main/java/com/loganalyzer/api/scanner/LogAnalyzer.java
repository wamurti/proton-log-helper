package com.loganalyzer.api.scanner;

import com.loganalyzer.scanner.SystemInfoAnalyzer;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface LogAnalyzer {
    void analyze(Map<Path, List<String>> logFilesContent);
    List<SystemInfoAnalyzer> getAnalyzerResults();
}
