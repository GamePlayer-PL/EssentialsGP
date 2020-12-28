package io.github.gameplayerpl;



import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;

import java.io.*;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;


public class EssentialsGP extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("EssentialsGP starting...");
        getLogger().info("Checking libraries:");
        File dir = new File(getDataFolder().getAbsolutePath() + "/libs/py_plugin.txt");
        if (!dir.exists()) {

            getLogger().warning("Py_plugins module not found!");
            getLogger().info("Making Py_plugins.submodule...");
            JSONArray a = new JSONArray();
            
            Date date = Calendar.getInstance().getTime();
            ShellEssentials.writefile(getDataFolder().getAbsolutePath() + "/libs", "py_plugin.txt", String.valueOf(date), "txt");


        } else {
            String i = ShellEssentials.readfile(getDataFolder().getAbsolutePath()+"/libs", "py_plugin.txt", "txt");
            getLogger().info(i);
        }

    }

    //@Override
    public void onDisable() {
        getLogger().info("Disabling libraries...");

    }


}

