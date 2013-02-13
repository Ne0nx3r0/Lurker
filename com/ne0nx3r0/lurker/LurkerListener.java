package com.ne0nx3r0.lurker;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

class LurkerListener implements Listener
{
    private final Lurker plugin;

    public LurkerListener(Lurker plugin)
    {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        if(plugin.UPDATE_AVAILABLE)
        {
            Player player = event.getPlayer();
        
            if(player.isOp() || player.hasPermission("lurker.manage"))
            {
                player.sendMessage(ChatColor.DARK_RED+"I'm watching you. "+ChatColor.GREEN+"Also there is a Lurker update avilable:");
                player.sendMessage(plugin.UPDATE_STRING);
            }
        }
    }
}
