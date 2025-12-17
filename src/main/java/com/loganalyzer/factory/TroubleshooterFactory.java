package com.loganalyzer.factory;

import com.loganalyzer.api.scanner.LogAnalyzer;
import com.loganalyzer.scanner.SystemInfoAnalyzer;
import com.loganalyzer.scanner.Troubleshooter;

import java.util.List;

public class TroubleshooterFactory {
    private static Troubleshooter instance;

    public static Troubleshooter createTroubleshooterSingleton() {
        if (instance == null) {
            List<LogAnalyzer> analyzers = List.of(
                    new SystemInfoAnalyzer()
                    // Add more analyzers here:
                    // new ErrorAnalyzer(),
                    // new CrashAnalyzer(),
                    // new VulkanAnalyzer()
            );
            instance = new Troubleshooter(analyzers);
        }
        return instance;
    }
}
