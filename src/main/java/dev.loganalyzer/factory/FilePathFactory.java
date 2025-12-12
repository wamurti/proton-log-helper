package dev.loganalyzer.factory;
import dev.loganalyzer.models.filepath.FilePathsToAmdHardwareLogs;
import dev.loganalyzer.models.filepath.FilePathsToProtonLogs;

public class FilePathFactory {
    private static FilePathsToProtonLogs filePathsToProtonLogsInstance;
    private static FilePathsToAmdHardwareLogs filePathsToAmdHardwareLogsInstance;

    public static FilePathsToProtonLogs createProtonLogsSingleton() {
        if (filePathsToProtonLogsInstance == null) {
            filePathsToProtonLogsInstance = new FilePathsToProtonLogs();
        }
        return filePathsToProtonLogsInstance;
    }
    public static FilePathsToAmdHardwareLogs createAmdHardwareLogsSingleton() {
        if (filePathsToAmdHardwareLogsInstance == null) {
            filePathsToAmdHardwareLogsInstance = new FilePathsToAmdHardwareLogs();
        }
        return filePathsToAmdHardwareLogsInstance;
    }

    //Nya instanser
    public static FilePathsToProtonLogs createNewFilePathsToProtonLogs(){return new FilePathsToProtonLogs();}
    public static FilePathsToAmdHardwareLogs createNewFilePathsToAmdHardwareLogs(){return new FilePathsToAmdHardwareLogs();}

}
