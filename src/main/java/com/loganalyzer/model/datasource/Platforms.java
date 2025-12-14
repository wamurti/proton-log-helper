package com.loganalyzer.model.datasource;

public class Platforms {
    private boolean windows;
    private boolean mac;
    private boolean linux;

    public boolean isWindows() {
        return windows;
    }

    public void setWindows(boolean windows) {
        this.windows = windows;
    }

    public boolean isMac() {
        return mac;
    }

    public void setMac(boolean mac) {
        this.mac = mac;
    }

    public boolean isLinux() {
        return linux;
    }

    public void setLinux(boolean linux) {
        this.linux = linux;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{");
        boolean first = true;

        if (windows) {
            sb.append("Windows");
            first = false;
        }
        if (mac) {
            if (!first) sb.append(", ");
            sb.append("Mac");
            first = false;
        }
        if (linux) {
            if (!first) sb.append(", ");
            sb.append("Linux");
        }

        sb.append("}");
        return sb.toString();
    }
}
