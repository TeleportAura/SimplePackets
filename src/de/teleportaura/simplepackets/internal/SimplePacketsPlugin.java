package de.teleportaura.simplepackets.internal;

import de.teleportaura.simplepackets.internal.netty.NettyInjector;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class SimplePacketsPlugin extends JavaPlugin implements Listener {

    private static SimplePacketsPlugin instance;

    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        NettyInjector.inject(e.getPlayer());
    }

    public static SimplePacketsPlugin instance() {
        return instance;
    }

}
