# Proton log helper
Ett verktyg för att snabbt analysera loggar i Steam/Proton på Linux (t.ex. Bazzite + AMD-hårdvara).
Letar i hemkatalogen efter steams protonloggar och ger en snabb sammanfattning per spel.
## Förutsättningar
- Java 21 eller senare.
- Maven. (För mac brew install maven)
- Slå på loggning i Steam för spelet du vill analysera: I steam manage->properties->General->**Launch Options** = `PROTON_LOG=1 %command%`

Verktyget läser just nu: 
- Proton loggar 

Verktyget läser i framtiden(?):
- ~~Spelets loggar~~ 
- ~~Systemd / kernel / Mesa loggar~~ 
- ~~Xorg / Wayland loggar~~ 
- ~~Shader cache~~ 
- ~~GPU-driver info~~ 
## Kör 
```
mvn clean package
java -jar target/*-jar-with-dependencies.jar
```

## Övrigt
Just nu läggs loggarna direkt i hemkatalogen men överväger att lägga dem på specifik plats för att inte få problem med behörigheter.
Går att styra med `PROTON_LOG=1 PROTON_LOG_DIR=~/proton-logs %command%`
