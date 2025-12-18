package com.loganalyzer.scanner;

import com.loganalyzer.api.scanner.LogAnalyzer;
import com.loganalyzer.util.FilePathHelper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SystemInfoAnalyzer implements LogAnalyzer {
    String appId;
    String protonVersion;
    String gpuModel;
    String gpuDriver;
    String gpuCodename;

    List<SystemInfoAnalyzer> systemInfoResults;


    @Override
    public void analyze(Map<Path, List<String>> logFilesContent) {
        List<SystemInfoAnalyzer> results = new ArrayList<>();
        for (var entry : logFilesContent.entrySet()) {
            SystemInfoAnalyzer analyzer = new SystemInfoAnalyzer();
            Path path = entry.getKey();
            analyzer.appId = FilePathHelper.extractAppIdFromLogPath(path);
            System.out.println("Fil: " + path);
            List<String> lines = entry.getValue();
            analyzer.setProtonVersion(lines);
            analyzer.setGpuModel(lines);
            analyzer.setGpuDriver(lines);
            analyzer.setGpuCodeName(lines);
            results.add(analyzer);

            System.out.println("Proton Version: " + analyzer.protonVersion + " | GPU Model: " + analyzer.gpuModel + " | GPU Driver: " + analyzer.gpuDriver + " | GPU Codename: " + analyzer.gpuCodename);
            System.out.println();
        }
        systemInfoResults = results;

    }
    public List<SystemInfoAnalyzer> getAnalyzerResults() {
        return systemInfoResults;
    }

    private void setGpuCodeName(List<String> lines) {
        for (String line : lines) {
            if (line.contains("info:  AMD")) {
                String[] parts = line.split("\\(");
                String[] gpuDriverPart = parts[1].split(" ");
                gpuCodename = gpuDriverPart[1];
                break;
            }
        }
    }

    private void setGpuDriver(List<String> lines) {
        for (String line : lines) {
            if (line.contains("info:  AMD")) {
                String[] parts = line.split("\\(");
                String[] gpuDriverPart = parts[1].split(" ");
                gpuDriver = gpuDriverPart[0];
                break;
            }
        }
    }

    private void setGpuModel(List<String> lines) {
        for (String line : lines) {
            if (line.contains("info:  AMD")) {
                String[] parts = line.split("\\(");
                gpuModel = parts[0].substring(parts[0].indexOf(":") + 1).trim();
                break;
            }
        }
    }

    private void setProtonVersion(List<String> lines) {
        for (String line : lines) {
            if (line.contains("proton-")) {
                protonVersion = line.split(" ")[2];
                break;
            }
        }

    }
}