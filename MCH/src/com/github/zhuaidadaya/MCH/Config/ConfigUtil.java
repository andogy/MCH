package com.github.zhuaidadaya.MCH.Config;

public class ConfigUtil {
    private String configPath;
    private String configName;
    private String configVersion;

    public ConfigUtil() {
        configName = "settings.conf";
    }

    public ConfigUtil(String configPath) {
        this.configPath = configPath;
        configName = "settings.conf";
    }

    public ConfigUtil(String configPath,String configName) {
        this.configPath = configPath;
        this.configName = configName;
    }

    public ConfigUtil(String configPath,String configName,String configVersion) {
        this.configPath = configPath;
        this.configName = configName;
        this.configVersion = configVersion;
    }

//    public static
}
