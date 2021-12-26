package de.teleportaura.simplepackets.internal;

import de.teleportaura.simplepackets.internal.netty.NettyInjector;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class SimplePacketsPlugin extends JavaPlugin implements Listener {

    private static SimplePacketsPlugin instance;

    public static SimplePacketsPlugin instance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(this, this);
    }


    /*TODO inject as soon as the "hello packet" is received by the server by
    *  using bytecode manipulation on net.minecraft.server.v1_8_R3.LoginListener.class*/
    @EventHandler
    public void onPlayerPreLogin(PlayerJoinEvent e) {
        NettyInjector.inject(e.getPlayer());
    }


}
