package de.teleportaura.simplepackets.api.packet.impl.s2c;

import de.teleportaura.simplepackets.api.packet.SimplePacketsPacket;
import de.teleportaura.simplepackets.internal.util.ReflectionUtil;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

public class S02PacketChat extends SimplePacketsPacket<PacketPlayOutChat> {

    public S02PacketChat(PacketPlayOutChat packet) {
        super(packet);
    }

    public IChatBaseComponent getMessage() {
        return ReflectionUtil.getFieldValue(packet, PacketPlayOutChat.class, "a", IChatBaseComponent.class);
    }

    public void setMessage(IChatBaseComponent message) {
        ReflectionUtil.setFieldValue(packet, PacketPlayOutChat.class, "a", message);
    }

}
