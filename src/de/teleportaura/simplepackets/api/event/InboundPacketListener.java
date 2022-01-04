package de.teleportaura.simplepackets.api.event;

import net.minecraft.server.v1_8_R3.Packet;

@SuppressWarnings("rawtypes")
public interface InboundPacketListener<T extends Packet> extends PacketListener{

    @SuppressWarnings("unchecked")
    default void handle(PacketEvent e) {
        handleInbound((PacketInboundEvent<T>) e);
    }

    void handleInbound(PacketInboundEvent<T> e);

}
