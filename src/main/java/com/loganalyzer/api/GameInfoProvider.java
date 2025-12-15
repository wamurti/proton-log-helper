package com.loganalyzer.api;

import java.util.List;

public interface GameInfoProvider {
    void fetchDataFromWebAsync(List<String> appIds);
}
