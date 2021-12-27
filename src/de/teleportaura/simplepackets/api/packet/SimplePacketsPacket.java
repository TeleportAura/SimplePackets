package de.teleportaura.simplepackets.api.packet;

import de.teleportaura.simplepackets.api.packet.impl.s2c.S02PacketChat;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketDataSerializer;
import net.minecraft.server.v1_8_R3.PacketListener;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

import java.io.IOException;

@SuppressWarnings("rawtypes")
public class SimplePacketsPacket<T extends Packet> implements Packet {


    public static void main(String[] args) {

    }

    protected T packet;

    public SimplePacketsPacket(T packet) {
        this.packet = packet;
    }


    public static SimplePacketsPacket get(Packet packet) {
        switch (packet.getClass().getName()) {
            case "net.minecraft.server.v1_8_R3.PacketPlayOutChat":
                return new S02PacketChat((PacketPlayOutChat) packet);
            default: return null;
        }
    }


    @Override
    public void a(PacketDataSerializer packetDataSerializer) throws IOException {
        packet.a(packetDataSerializer);
    }

    @Override
    public void b(PacketDataSerializer packetDataSerializer) throws IOException {
        packet.b(packetDataSerializer);
    }

    @SuppressWarnings("unchecked")
    @Override
    public void a(PacketListener packetListener) {
        packet.a(packetListener);
    }
}
