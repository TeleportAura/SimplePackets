package de.teleportaura.simplepackets.api.event;

import de.teleportaura.simplepackets.api.packet.SimplePacketsPacket;
import net.minecraft.server.v1_8_R3.Packet;

import java.util.ArrayList;
import java.util.HashMap;

public class PacketEventManager {

    private HashMap<Class<? extends Packet<?>>, ArrayList<SimplePacketListener<? extends Packet<?>>>> packetHandlers
            = new HashMap<>();
    private ArrayList<SimplePacketListener<Packet<?>>> everyPacketHandlers
            = new ArrayList<>();


    public <T extends Packet<?>> boolean dispatchInbound(T packet) {
        PacketInboundEvent<T> vanillaPacketEvent = new PacketInboundEvent<>(packet);
        for(SimplePacketListener<Packet<?>> listener: everyPacketHandlers) {
            listener.handleInbound(vanillaPacketEvent);
        }
        for(SimplePacketListener<?> listener: packetHandlers.get(packet.getClass())) {
            listener.handleInbound(vanillaPacketEvent);
        }
        if(vanillaPacketEvent.isCancelled()) return false;
        SimplePacketsPacket<T> simplePacketsPacket = SimplePacketsPacket.get(packet);
        if(simplePacketsPacket==null) return false;
        PacketInboundEvent<SimplePacketsPacket<T>> simplePacketsPacketPacketEvent = new PacketInboundEvent<>(simplePacketsPacket);
        for(SimplePacketListener<?> listener: packetHandlers.get(simplePacketsPacket.getClass())) {
            listener.handleInbound(simplePacketsPacketPacketEvent);
        }
        return !simplePacketsPacketPacketEvent.isCancelled();
    }

    public <T extends Packet<?>> boolean dispatchOutbound(T packet) {
        PacketOutboundEvent<T> vanillaPacketEvent = new PacketOutboundEvent<>(packet);
        for(SimplePacketListener<Packet<?>> listener: everyPacketHandlers) {
            listener.handleOutbound(vanillaPacketEvent);
        }
        for(SimplePacketListener<?> listener: packetHandlers.get(packet.getClass())) {
            listener.handleOutbound(vanillaPacketEvent);
        }
        if(vanillaPacketEvent.isCancelled()) return false;
        SimplePacketsPacket<T> simplePacketsPacket = SimplePacketsPacket.get(packet);
        if(simplePacketsPacket==null) return false;
        PacketOutboundEvent<SimplePacketsPacket<T>> simplePacketsPacketPacketEvent = new PacketOutboundEvent<>(simplePacketsPacket);
        for(SimplePacketListener<?> listener: packetHandlers.get(simplePacketsPacket.getClass())) {
            listener.handleOutbound(simplePacketsPacketPacketEvent);
        }
        return !simplePacketsPacketPacketEvent.isCancelled();
    }

}
