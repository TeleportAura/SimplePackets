package de.teleportaura.simplepackets.api.event;

import de.teleportaura.simplepackets.api.packet.SimplePacketsPacket;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class PacketEventManager {

    private final HashMap<Class<? extends Packet<?>>, ArrayList<PacketListener>> packetHandlers
            = new HashMap<>();
    private final ArrayList<PacketListener> everyPacketHandlers
            = new ArrayList<>();

    public <T extends Packet<?>> void registerListener(InboundPacketListener<T> packetListener, Class<T> packetClazz) {
        this.registerListenerInternally(packetListener, packetClazz);
    }

    public <T extends Packet<?>> void registerListener(OutboundPacketListener<T> packetListener, Class<T> packetClazz) {
        registerListenerInternally(packetListener, packetClazz);
    }

    private <T extends Packet<?>> void registerListenerInternally(PacketListener packetListener, Class<T> packetClazz) {
        if (packetClazz.equals(Packet.class)) {
            everyPacketHandlers.add(packetListener);
            return;
        }
        ArrayList<PacketListener> list = packetHandlers.get(packetClazz);
        if (list == null) {
            list = new ArrayList<>();
            list.add(packetListener);
            packetHandlers.put(packetClazz, list);
        }
        list.add(packetListener);
    }

    public <T extends Packet<?>> void unregisterListener(InboundPacketListener<T> packetListener, Class<T> packetClazz) {
        if (packetClazz.equals(Packet.class)) {
            everyPacketHandlers.remove(packetListener);
            return;
        }
        ArrayList<PacketListener> list = packetHandlers.get(packetClazz);
        if (list == null) return;
        list.remove(packetListener);
    }

    @SuppressWarnings("unchecked")
    public <T extends Packet<?>> boolean dispatchInbound(T packet, UUID player) {
        PacketInboundEvent<T> vanillaPacketEvent = new PacketInboundEvent<>(packet, player);
        for (PacketListener listener : everyPacketHandlers) {
            listener.handle(vanillaPacketEvent);
        }
        for (PacketListener listener : packetHandlers.getOrDefault(packet.getClass(), new ArrayList<>())) {
            listener.handle(vanillaPacketEvent);
        }
        if (vanillaPacketEvent.isCancelled()) return false;
        SimplePacketsPacket<T> simplePacketsPacket = (SimplePacketsPacket<T>) SimplePacketsPacket.get(packet);
        if (simplePacketsPacket == null) return true;
        PacketInboundEvent<SimplePacketsPacket<T>> simplePacketsPacketPacketEvent = new PacketInboundEvent<>(simplePacketsPacket, player);
        for (PacketListener listener : packetHandlers.getOrDefault(simplePacketsPacket.getClass(), new ArrayList<>())) {
            listener.handle(simplePacketsPacketPacketEvent);
        }
        return !simplePacketsPacketPacketEvent.isCancelled();
    }

    @SuppressWarnings("unchecked")
    public <T extends Packet<?>> boolean dispatchOutbound(T packet, UUID player) {
        PacketOutboundEvent<T> vanillaPacketEvent = new PacketOutboundEvent<>(packet, player);
        for (PacketListener listener : everyPacketHandlers) {
            listener.handle(vanillaPacketEvent);
        }
        for (PacketListener listener : packetHandlers.getOrDefault(packet.getClass(), new ArrayList<>())) {
            listener.handle(vanillaPacketEvent);
        }
        if (vanillaPacketEvent.isCancelled()) return false;
        SimplePacketsPacket<T> simplePacketsPacket = (SimplePacketsPacket<T>) SimplePacketsPacket.get(packet);
        if (simplePacketsPacket == null) return true;
        PacketOutboundEvent<SimplePacketsPacket<T>> simplePacketsPacketPacketEvent = new PacketOutboundEvent<>(simplePacketsPacket, player);
        for (PacketListener listener : packetHandlers.getOrDefault(simplePacketsPacket.getClass(), new ArrayList<>())) {
            listener.handle(simplePacketsPacketPacketEvent);
        }
        return !simplePacketsPacketPacketEvent.isCancelled();
    }

}
