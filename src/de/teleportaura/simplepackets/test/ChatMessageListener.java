package de.teleportaura.simplepackets.test;

import de.teleportaura.simplepackets.api.event.events.PacketOutboundEvent;
import de.teleportaura.simplepackets.api.event.listeners.OutboundPacketListener;
import de.teleportaura.simplepackets.api.packet.impl.s2c.S02PacketChat;
import net.minecraft.server.v1_8_R3.ChatMessage;

public class ChatMessageListener implements OutboundPacketListener<S02PacketChat> {

    @Override
    public void handleOutbound(PacketOutboundEvent<S02PacketChat> e) {
        e.getPacket().setMessage(new ChatMessage("hehe boi"));
    }

}
