package me.snazzy.xpsend;

import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        System.out.println("XpSend has loaded");
        getCommand("sendxp").setExecutor(new SendCommand());
        getCommand("xpchart").setExecutor(new ChartCommand());
    }
}
