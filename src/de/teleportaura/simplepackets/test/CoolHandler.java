package de.teleportaura.simplepackets.test;

import de.teleportaura.simplepackets.api.event.events.PacketOutboundEvent;
import de.teleportaura.simplepackets.api.event.listeners.PacketEvent;
import de.teleportaura.simplepackets.api.event.listeners.PacketHandler;
import de.teleportaura.simplepackets.api.packet.impl.s2c.S02PacketChat;
import net.minecraft.server.v1_8_R3.ChatMessage;

public class CoolHandler extends PacketHandler {

    @PacketEvent(packetType = S02PacketChat.class)
    public void handleS02PacketChat(PacketOutboundEvent<S02PacketChat> e) {
        e.getPacket().setMessage(new ChatMessage("helo guys and welcome to my mainguaft lets play"));
    }

}
