package net.lifeforge.bloodgem.listeners;

import net.lifeforge.bloodgem.managers.BloodGemManager;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.entity.Item;

public class KillListener implements Listener {

    private final BloodGemManager manager;

    public KillListener(BloodGemManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player victim = event.getEntity();
        Player killer = victim.getKiller();
        if (killer == null)
            return;

        Item droppedItem = victim.getWorld().dropItemNaturally(
                victim.getLocation(),
                manager.createBloodGem()
        );

        if (droppedItem != null) {
            manager.makeLegendary(droppedItem);
        }
    }
}
