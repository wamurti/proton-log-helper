package com.loganalyzer.factory;

import com.loganalyzer.analyzer.ErrorAnalyzer;
import com.loganalyzer.analyzer.SystemInfoAnalyzer;

public class LogAnalyzerFactory {
    private static SystemInfoAnalyzer systemInfoAnalyzerInstance;
    private static ErrorAnalyzer errorAnalyzerInstance;

    public static SystemInfoAnalyzer createSystemInfoAnalyzerSingleton() {
        if (systemInfoAnalyzerInstance == null) {
            return new SystemInfoAnalyzer();
        }
        else {
            return systemInfoAnalyzerInstance;
        }
    }

    public static ErrorAnalyzer createErrorAnalyzerSingleton() {
        if (errorAnalyzerInstance == null) {
            return new ErrorAnalyzer();
        }
        else {
            return errorAnalyzerInstance;
        }
    }
}
