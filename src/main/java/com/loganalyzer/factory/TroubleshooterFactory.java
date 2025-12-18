package com.loganalyzer.factory;

import com.loganalyzer.api.scanner.LogAnalyzer;
import com.loganalyzer.scanner.SystemInfoAnalyzer;


import java.util.List;

public class TroubleshooterFactory {
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
