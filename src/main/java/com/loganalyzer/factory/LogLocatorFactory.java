package com.loganalyzer.factory;
import com.loganalyzer.locator.AmdLogLocator;
import com.loganalyzer.locator.ProtonLogLocator;

public class LogLocatorFactory {
    private static ProtonLogLocator protonLogLocatorInstance;
    private static AmdLogLocator amdLogLocatorInstance;

    public static ProtonLogLocator createProtonLogLocatorSingleton() {
        if (protonLogLocatorInstance == null) {
            protonLogLocatorInstance = new ProtonLogLocator();
        }
        return protonLogLocatorInstance;
    }
    public static AmdLogLocator createAmdLogLocatorSingleton() {
        if (amdLogLocatorInstance == null) {
            amdLogLocatorInstance = new AmdLogLocator();
        }
        return amdLogLocatorInstance;
    }

    //Nya instanser
    public static ProtonLogLocator createNewProtonLogLocator(){return new ProtonLogLocator();}
    public static AmdLogLocator createNewAmdLogLocator(){return new AmdLogLocator();}

}
