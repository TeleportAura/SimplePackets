package de.teleportaura.simplepackets.api.event;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.event.Event;

@SuppressWarnings("rawtypes")
public interface InboundPacketListener<T extends Packet> extends PacketListener{

    @SuppressWarnings("unchecked")
    default void handle(Event e) {
        handleInbound((PacketInboundEvent<T>) e);
    }

    void handleInbound(PacketInboundEvent<T> e);

}
