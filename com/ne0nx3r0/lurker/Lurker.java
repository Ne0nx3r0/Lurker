package com.ne0nx3r0.lurker;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import net.h31ix.updater.Updater;
import org.bukkit.plugin.java.JavaPlugin;

public class Lurker extends JavaPlugin
{
    boolean UPDATE_AVAILABLE = false;
    String UPDATE_STRING;
    
    @Override
    public void onEnable()
    {
        this.getCommand("lurk").setExecutor(new LurkerCommand(this));
        
        File configFile = new File(this.getDataFolder(),"config.yml");
        
        if(!configFile.exists())
        {
            configFile.mkdirs();
            
            copy(this.getResource("config.yml"),configFile);
        }
        
        if(this.getConfig().getBoolean("notify-about-updates"))
        {
            Updater updater = new Updater(this, "lurker", this.getFile(), Updater.UpdateType.NO_DOWNLOAD, false);
            UPDATE_AVAILABLE = updater.getResult() == Updater.UpdateResult.UPDATE_AVAILABLE;
            
            if(UPDATE_AVAILABLE)
            {
                UPDATE_STRING = updater.getLatestVersionString();
                
                getServer().getPluginManager().registerEvents(new LurkerListener(this), this);
            }
        }
    }
    
    private void copy(InputStream in, File file)
    {
        try
        {
            OutputStream out = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while((len=in.read(buf))>0)
            {
                out.write(buf,0,len);
            }
            out.close();
            in.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
