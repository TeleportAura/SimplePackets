package de.teleportaura.simplepackets.api;

import de.teleportaura.simplepackets.api.event.InboundPacketListener;
import de.teleportaura.simplepackets.api.event.OutboundPacketListener;
import de.teleportaura.simplepackets.api.event.PacketListener;
import de.teleportaura.simplepackets.internal.SimplePacketsPlugin;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SimplePacketsAPI {

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
        public static void registerListenerUnsafe(PacketListener packetListener, Class<?> packetClazz) {
            SimplePacketsPlugin.instance().eventManager.registerListenerInternally(packetListener, (Class<? extends Packet<?>>) packetClazz);
        }

        public static void unregisterListener(PacketListener listener, Class<Packet<?>> packetClazz) {
            SimplePacketsPlugin.instance().eventManager.unregisterListener(listener, packetClazz);
        }

    }

}
