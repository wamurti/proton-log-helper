package com.loganalyzer.model.datasource;

import java.util.List;

public class SteamAppData {
    private String name;
    private int steam_appid;
    private Requirements pc_requirements;
    private Requirements mac_requirements;
    private Requirements linux_requirements;
    private List<Demo> demos;
    private Platforms platforms;
    private SupportInfo support_info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSteam_appid() {
        return steam_appid;
    }

    public void setSteam_appid(int steam_appid) {
        this.steam_appid = steam_appid;
    }

    public Requirements getPc_requirements() {
        return pc_requirements;
    }

    public void setPc_requirements(Requirements pc_requirements) {
        this.pc_requirements = pc_requirements;
    }

    public Requirements getMac_requirements() {
        return mac_requirements;
    }

    public void setMac_requirements(Requirements mac_requirements) {
        this.mac_requirements = mac_requirements;
    }

    public Requirements getLinux_requirements() {
        return linux_requirements;
    }

    public void setLinux_requirements(Requirements linux_requirements) {
        this.linux_requirements = linux_requirements;
    }

    public List<Demo> getDemos() {
        return demos;
    }

    public void setDemos(List<Demo> demos) {
        this.demos = demos;
    }

    public Platforms getPlatforms() {
        return platforms;
    }

    public void setPlatforms(Platforms platforms) {
        this.platforms = platforms;
    }

    public SupportInfo getSupport_info() {
        return support_info;
    }

    public void setSupport_info(SupportInfo support_info) {
        this.support_info = support_info;
    }
}
