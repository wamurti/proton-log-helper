package com.loganalyzer.api.scanner;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;

public interface LogAnalyzer {
    Map<Path, String> analyze(Map<Path, List<String>> logFilesContent);
}
