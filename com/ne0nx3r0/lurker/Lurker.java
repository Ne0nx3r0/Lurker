package com.ne0nx3r0.lurker;

import org.bukkit.plugin.java.JavaPlugin;

public class Lurker extends JavaPlugin
{
    @Override
    public void onEnable()
    {
        this.getCommand("lurk").setExecutor(new LurkerCommand(this));
    }
}
