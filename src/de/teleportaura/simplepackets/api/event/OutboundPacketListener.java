package de.teleportaura.simplepackets.api.event;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.event.Event;

@SuppressWarnings("rawtypes")
public interface OutboundPacketListener<T extends Packet> extends PacketListener{

    @SuppressWarnings("unchecked")
    default void handle(Event e) {
        handleOutbound((PacketOutboundEvent<T>) e);
    }

    void handleOutbound(PacketOutboundEvent<T> e);

}
