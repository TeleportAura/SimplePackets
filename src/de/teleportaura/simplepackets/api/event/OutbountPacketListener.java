package de.teleportaura.simplepackets.api.event;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.event.Event;

@SuppressWarnings("rawtypes")
public interface OutbountPacketListener<T extends Packet> extends PacketListener{

    @SuppressWarnings("unchecked")
    default void handle(PacketOutboundEvent<T> e) {
        handleInbound(e);
    }

    void handleInbound(PacketOutboundEvent<T> e);

}
