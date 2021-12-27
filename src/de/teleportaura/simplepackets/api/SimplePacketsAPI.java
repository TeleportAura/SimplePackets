package de.teleportaura.simplepackets.api;

import de.teleportaura.simplepackets.api.event.InboundPacketListener;
import de.teleportaura.simplepackets.api.event.OutboundPacketListener;
import de.teleportaura.simplepackets.internal.SimplePacketsPlugin;
import net.minecraft.server.v1_8_R3.Packet;

public class SimplePacketsAPI {



    public static <T extends Packet<?>> void registerListener(InboundPacketListener<T> packetListener, Class<T> packetClazz) {
        SimplePacketsPlugin.instance().eventManager.registerListener(packetListener, packetClazz);
    }

    public static <T extends Packet<?>> void registerListener(OutboundPacketListener<T> packetListener, Class<T> packetClazz) {
        SimplePacketsPlugin.instance().eventManager.registerListener(packetListener, packetClazz);
    }

}
