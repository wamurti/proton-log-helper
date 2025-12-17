package com.loganalyzer.scanner;

import com.loganalyzer.LogType;
import com.loganalyzer.api.LogFileLocator;
import com.loganalyzer.api.scanner.LogAnalyzer;
import com.loganalyzer.util.FilePathHelper;


import java.nio.file.Path;
import java.util.List;
import java.util.Map;

import static com.loganalyzer.util.FilePathHelper.getLogsContent;

public class Troubleshooter {
    private final List<LogAnalyzer> analyzers;

    public Troubleshooter(List<LogAnalyzer> analyzers) {
        this.analyzers = analyzers;
    }

    public void troubleShoot(List<LogFileLocator> logFiles) {
        Map<Path, List<String>> content = getLogsContent(logFiles);
        for (LogAnalyzer analyzer : analyzers) {
            analyzer.analyze(content);
        }
    }
}
