package de.teleportaura.simplepackets.api.event;

import net.minecraft.server.v1_8_R3.Packet;

@SuppressWarnings("rawtypes")
public interface SimplePacketListener<T extends Packet> {

    void handleInbound(PacketInboundEvent e);

    void handleOutbound(PacketOutboundEvent e);

}
