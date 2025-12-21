package com.loganalyzer.analyzer;

import com.loganalyzer.api.scanner.LogAnalyzer;
import com.loganalyzer.util.FilePathHelper;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ErrorAnalyzer implements LogAnalyzer {
    String appId;
    private List<String> errors = new ArrayList<>();
    List<LogAnalyzer> errorResults;

    @Override
    public void analyze(Map<Path, List<String>> logFilesContent) {
        List<LogAnalyzer> results = new ArrayList<>();
        System.out.println("---------------------------------------");
        System.out.println("Analyzing proton logfile for errors...");
        System.out.println("---------------------------------------\n");
        for (var entry : logFilesContent.entrySet()) {
            ErrorAnalyzer analyzer = new ErrorAnalyzer();
            Path path = entry.getKey();
            analyzer.appId = FilePathHelper.extractAppIdFromLogPath(path);
            System.out.println("Fil: " + path);
            List<String> lines = entry.getValue();

            analyzer.setAllErrLines(lines);
            results.add(analyzer);


            System.out.println("Errors: "+analyzer.errors.size());
        }
        errorResults = results;
        System.out.println("---------------------------------------");

    }

    private void setAllErrLines(List<String> lines) {
        //116.478:0090:00b4:err:hid:set_report_from_event TODO: Process Report (21, 0)
        List<String> tempErrors = new ArrayList<>();
        for(String line : lines){
            if(line.contains("err:") && !line.contains("TODO:")){
                tempErrors.add(line);
            }
        }
        errors = tempErrors;
    }

    @Override
    public List<LogAnalyzer> getAnalyzerResults() {
        return errorResults;
    }
}
