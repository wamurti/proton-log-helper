package com.loganalyzer.factory;

import com.loganalyzer.analyzer.SystemInfoAnalyzer;

public class LogAnalyzerFactory {
    private static SystemInfoAnalyzer systemInfoAnalyzerInstance;

    public static SystemInfoAnalyzer createSystemInfoAnalyzerSingleton() {
        if (systemInfoAnalyzerInstance == null) {
            return new SystemInfoAnalyzer();
        }
        else {
            return systemInfoAnalyzerInstance;
        }
    }
}
