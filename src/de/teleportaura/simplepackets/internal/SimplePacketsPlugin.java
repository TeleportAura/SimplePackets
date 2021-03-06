package de.teleportaura.simplepackets.internal;

import de.teleportaura.simplepackets.api.SimplePacketsAPI;
import de.teleportaura.simplepackets.api.event.PacketEventManager;
import de.teleportaura.simplepackets.internal.netty.NettyInjector;
import de.teleportaura.simplepackets.test.CoolHandler;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;


public class SimplePacketsPlugin extends JavaPlugin implements Listener {

    private static SimplePacketsPlugin instance;

    public PacketEventManager eventManager = new PacketEventManager();

    public static SimplePacketsPlugin instance() {
        return instance;
    }

    public void onEnable() {
        instance = this;
        Bukkit.getPluginManager().registerEvents(this, this);

        //test
        SimplePacketsAPI.registerListener(new CoolHandler());
        /*SimplePacketsAPI.registerListener(new ChatMessageListener(), S02PacketChat.class);
        SimplePacketsAPI.Unsafe.registerListenerUnsafe(new UnsafeListener(), PacketPlayInArmAnimation.class);*/
    }

    public void onDisable() {
        eventManager.onDisable();
    }


    /*TODO inject as soon as the "hello packet" is received by the server by
     *  using bytecode manipulation on net.minecraft.server.v1_8_R3.LoginListener.class*/
    @EventHandler
    public void onPlayerPreLogin(PlayerJoinEvent e) {
        NettyInjector.inject(e.getPlayer());
    }


}
