package de.teleportaura.simplepackets.test;

import de.teleportaura.simplepackets.api.event.PacketInboundEvent;
import de.teleportaura.simplepackets.api.event.PacketOutboundEvent;
import de.teleportaura.simplepackets.api.event.InboundPacketListener;
import de.teleportaura.simplepackets.api.packet.impl.s2c.S02PacketChat;

public class ChatMessageListener implements InboundPacketListener<S02PacketChat> {


    @Override
    public void handleInbound(PacketInboundEvent e) {

    }

}
