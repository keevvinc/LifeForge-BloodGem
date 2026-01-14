package net.lifeforge.bloodgem;

import net.lifeforge.bloodgem.listeners.KillListener;
import net.lifeforge.bloodgem.managers.BloodGemManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class BloodGemPlugin extends JavaPlugin {

    private static BloodGemPlugin instance;
    private BloodGemManager manager;

    @Override
    public void onEnable() {
        instance = this;
        manager = new BloodGemManager(this);
        getServer().getPluginManager().registerEvents(new KillListener(manager), this);
        getLogger().info("BloodGemPlugin enabled!");
    }

    @Override
    public void onDisable() {
        getLogger().info("BloodGemPlugin disabled!");
    }

    public static BloodGemPlugin getInstance() {
        return instance;
    }

    public BloodGemManager getManager() {
        return manager;
    }
}
