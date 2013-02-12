package com.ne0nx3r0.lurker;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

class LurkerCommand implements CommandExecutor
{
    private final Lurker plugin;
    
    public LurkerCommand(Lurker plugin)
    {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String alias, String[] args)
    {
        if(!cs.hasPermission("lurker.lurk"))
        {
            cs.sendMessage(ChatColor.RED+"You do not have permission to use this command.");
            
            return true;
        }
        
        if(args.length == 0)
        {
            cs.sendMessage("Usage:");
            cs.sendMessage("/lurk <playername> Message here");
            cs.sendMessage("/lurk <playername> &DARK_REDColored message!");
            cs.sendMessage("/lurk all Message!");
            
            return true;
        }
        
        if(args[0].equalsIgnoreCase("all"))
        {
            String sMessage = "";
            for(int i = 1;i<args.length;i++)
            {
                sMessage += args[i]+" ";
            }

            sMessage = replaceColorCodes(sMessage);

            cs.sendMessage(ChatColor.GREEN+"Sent to all: "+ChatColor.RESET+sMessage);
            
            plugin.getServer().broadcastMessage(sMessage);
            
            return true;
        }
        else
        {
            Player p = plugin.getServer().getPlayer(args[0]);

            if(p == null)
            {
                for(Player player : plugin.getServer().getOnlinePlayers())
                {
                    if(player.getName().toLowerCase().contains(args[0].toLowerCase()))
                    {
                        p = player;
                    }
                }
            }

            if(p != null)
            {
                String sMessage = "";
                for(int i = 1;i<args.length;i++)
                {
                    sMessage += args[i]+" ";
                }

                sMessage = replaceColorCodes(sMessage);

                p.sendMessage(sMessage);
                cs.sendMessage(ChatColor.GREEN+"To "+ChatColor.WHITE+p.getName()+ChatColor.GREEN+": "+ChatColor.RESET+sMessage);
            
                return true;
            }
            else
            {
                cs.sendMessage(ChatColor.RED+"Player "+args[0]+" not found.");
                
                return true;
            }
        }
    }
    
    private String replaceColorCodes(String str)
    {
        for(ChatColor cc : ChatColor.values())
        {
            str = str.replace("&"+cc.name(), cc.toString());
        }
        
        return str;
    }
}
