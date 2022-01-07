package de.teleportaura.simplepackets.api.event.listeners;

import de.teleportaura.simplepackets.api.event.events.PacketEvent;
import de.teleportaura.simplepackets.api.event.events.PacketInboundEvent;
import net.minecraft.server.v1_8_R3.Packet;

@SuppressWarnings("rawtypes")
public interface InboundPacketListener<T extends Packet> extends PacketListener {

    @SuppressWarnings("unchecked")
    default void handle(PacketEvent e) {
        handleInbound((PacketInboundEvent<T>) e);
    }

    void handleInbound(PacketInboundEvent<T> e);

}
