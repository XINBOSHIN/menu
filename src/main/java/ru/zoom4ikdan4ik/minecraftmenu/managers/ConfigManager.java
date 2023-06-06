package ru.zoom4ikdan4ik.minecraftmenu.managers;

import net.minecraftforge.common.config.Configuration;

import java.io.File;

public class ConfigManager {
    public static final String MODID = "MinecraftMenu";
    public static final String MODNAME = "MinecraftMenu";

    private Configuration config;

    private String CATEGORY_CLIENT = "client";
    public String project;
    public String client;
    public String vk;

    private String CATEGORY_SERVER = "server";
    public String[] servers;

    public void loadConfig(File suggestedConfigurationFile) {
        this.config = new Configuration(suggestedConfigurationFile);

        this.project = this.config.getString("project", this.CATEGORY_CLIENT, "§aZD Project", "");
        this.client = this.config.getString("client", this.CATEGORY_CLIENT, "§aZD Client", "");
        this.vk = this.config.getString("vk", this.CATEGORY_CLIENT, "https://vk.com/zoom4ikdan4ik", "");

        this.servers = this.config.getStringList("servers", this.CATEGORY_SERVER, new String[]{"Server Minecraft->localhost:25565"}, "");
    }

    public void saveConfig() {
        this.config.save();
    }
}
