package de.teleportaura.simplepackets.api;

import de.teleportaura.simplepackets.api.event.listeners.InboundPacketListener;
import de.teleportaura.simplepackets.api.event.listeners.OutboundPacketListener;
import de.teleportaura.simplepackets.api.event.listeners.PacketHandler;
import de.teleportaura.simplepackets.api.event.listeners.PacketListener;
import de.teleportaura.simplepackets.internal.SimplePacketsPlugin;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SimplePacketsAPI {

    public static void registerListener(PacketHandler packetHandler) {
        packetHandler.register();
    }

    public static <T extends Packet<?>> void registerListener(InboundPacketListener<T> packetListener, Class<T> packetClazz) {
        SimplePacketsPlugin.instance().eventManager.registerListener(packetListener, packetClazz);
    }

    public static <T extends Packet<?>> void registerListener(OutboundPacketListener<T> packetListener, Class<T> packetClazz) {
        SimplePacketsPlugin.instance().eventManager.registerListener(packetListener, packetClazz);
    }

    public static SimplePacketsPlayer getPlayer(Player bukkitPlayer) {
        return new SimplePacketsPlayer(bukkitPlayer);
    }

    public static SimplePacketsPlayer getPlayer(UUID id) {
        return SimplePacketsPlayer.fromUUID(id);
    }

    public static class Unsafe {

        @SuppressWarnings("unchecked")
        public static void registerListenerUnsafe(PacketListener packetListener, Class<?>... packetClazz) {
            for (Class<?> clazz : packetClazz)
                SimplePacketsPlugin.instance().eventManager.registerListenerInternally(packetListener, (Class<? extends Packet<?>>) clazz);
        }

        @SuppressWarnings("unchecked")
        public static void unregisterListener(PacketListener listener, Class<?>... packetClazz) {
            for (Class<?> clazz : packetClazz)
                SimplePacketsPlugin.instance().eventManager.unregisterListener(listener, (Class<? extends Packet<?>>) clazz);
        }

    }

}
