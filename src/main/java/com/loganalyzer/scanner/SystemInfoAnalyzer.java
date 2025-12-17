package com.loganalyzer.scanner;

import com.loganalyzer.api.scanner.LogAnalyzer;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class SystemInfoAnalyzer implements LogAnalyzer {
    static String protonVersion;
    static String gpuVendor;
    static String gpuDriverVersion;
    static String gpuRadv;
    static String mesaVersion;
    @Override
    public Map<Path, String> analyze(Map<Path, List<String>> logFilesContent) {
        for (var entry : logFilesContent.entrySet()) {
            Path path = entry.getKey();
            System.out.println("Fil: " + path);
            List<String> lines = entry.getValue();
            getProtonVersion(lines);
            getGpuVendor(lines);
            System.out.println();

        }
        return null;
    }

    private static void getProtonVersion(List<String> lines){
        for (String line : lines) {
            if(line.contains("proton-")){
                protonVersion = line.split(" ")[2];
            }
        }

    }
    private static void getGpuVendor(List<String> lines){
        //info:  Vulkan: 1.3.274 - NVIDIA GeForce RTX 3070 (NVIDIA 550.67)
        //info:  Vulkan: 1.3.278 - AMD Radeon RX 6700 XT (RADV NAVI22) (Mesa 24.0.5-arch1.1)
        for (String line : lines) {
            if(line.contains("Vulkan:")){
                if(line.contains("NVIDIA")){
                    System.out.println("Found NVIDIA");
                    gpuVendor = "NVIDIA";
                    getNvidiaGpuDriverVersion(line);
                }else if(line.contains("AMD")){
                    System.out.println("Found AMD");
                    gpuVendor = "AMD";
                    getAmdGpuDriverVersion(line);
                }
            }
        }
        System.out.println("Proton version "+protonVersion+" vendor: "+gpuVendor+" driver version: "+gpuDriverVersion + " radv: "+gpuRadv+" mesa: "+mesaVersion);

    }

    private static void getNvidiaGpuDriverVersion(String line) {
        var temp = line.split("\\(");
        var tepList = temp[1].split(" ");
        gpuDriverVersion  = tepList[1].replace(")", "");

    }
    private static void getAmdGpuDriverVersion(String line) {
        var temp = line.split("\\(");
        gpuRadv = temp[1].replace(")","");
        mesaVersion = temp[2].replace(")","");
        var tepList = temp[1].split(" ");
        gpuDriverVersion  = tepList[1].replace(")", "");

    }

    //TODO: Structure accoring to below
    /*
    * Nvidia-exempel:

Proton: 9.0-1c

Vendor: NVIDIA

GPU: RTX 3070

Driver: 550.67

Platform: X11 eller Wayland (om du kan hitta det i loggen, då det ofta orsakar problem).

AMD-exempel:

Proton: 9.0-1c

Vendor: AMD

GPU: RX 6700 XT

Driver (Mesa): 24.0.5

Kernel: (Valfritt, men bra på Linux)*/


}