# Steam Crash Analyzer
Ett verktyg för att snabbt samla loggar efter en spelkrasch i Steam/Proton på Linux (t.ex. Bazzite + AMD-hårdvara). 
Verktyget hämtar: 
- Proton loggar 
- Spelets loggar 
- Systemd / kernel / Mesa loggar 
- Xorg / Wayland loggar 
- Shader cache 
- GPU-driver info 
## Kör 
```bash mvn package java -jar target/steam-crash-analyzer-1.0.0-jar-with-dependencies.jar```

Följande url verkar kunna användas för att hämta info från steam om spelet:
`curl "https://store.steampowered.com/api/appdetails?appids=1634860"`