package de.teleportaura.simplepackets.api.event;

import net.minecraft.server.v1_8_R3.Packet;

@SuppressWarnings("rawtypes")
public interface OutboundPacketListener<T extends Packet> extends PacketListener{

    @SuppressWarnings("unchecked")
    default void handle(PacketEvent e) {
        handleOutbound((PacketOutboundEvent<T>) e);
    }

    void handleOutbound(PacketOutboundEvent<T> e);

}
