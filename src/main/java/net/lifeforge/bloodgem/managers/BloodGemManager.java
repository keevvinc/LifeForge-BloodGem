package net.lifeforge.bloodgem.managers;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.List;

public class BloodGemManager {

    private final JavaPlugin plugin;

    public BloodGemManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public ItemStack createBloodGem() {
        ItemStack gem = new ItemStack(Material.REDSTONE);
        ItemMeta meta = gem.getItemMeta();
        if (meta != null) {
            meta.displayName(Component.text("Blood Gem", NamedTextColor.DARK_RED, TextDecoration.ITALIC));
            meta.lore(List.of(
                    Component.text("Forged from the essence of fallen warriors.", NamedTextColor.LIGHT_PURPLE),
                    Component.text("A relic of battle, mysterious and rare.", NamedTextColor.DARK_PURPLE)
            ));
            meta.addEnchant(org.bukkit.enchantments.Enchantment.LURE, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            gem.setItemMeta(meta);
        }
        return gem;
    }

    /** Make a dropped gem legendary (glow + float + particles + sound) */
    public void makeLegendary(Item item) {
        item.setGlowing(true);

        new BukkitRunnable() {
            int ticks = 0;
            @Override
            public void run() {
                if (ticks++ > 40 || item.isDead() || !item.isValid()) {
                    cancel();
                    return;
                }
                item.setVelocity(new Vector(0, 0.03, 0));
                item.getWorld().spawnParticle(
                        Particle.ENCHANT,
                        item.getLocation().add(0, 0.2, 0),
                        1, 0.15, 0.15, 0.15, 0.01
                );
            }
        }.runTaskTimer(plugin, 0, 2);

        item.getWorld().playSound(item.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 2.0f, 1.0f);
    }
}
