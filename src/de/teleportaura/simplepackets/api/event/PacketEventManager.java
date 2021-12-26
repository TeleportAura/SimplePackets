package de.teleportaura.simplepackets.api.event;

import de.teleportaura.simplepackets.api.packet.SimplePacketsPacket;
import net.minecraft.server.v1_8_R3.Packet;

import java.util.ArrayList;
import java.util.HashMap;

public class PacketEventManager {

    private final HashMap<Class<? extends Packet<?>>, ArrayList<PacketListener>> packetHandlers
            = new HashMap<>();
    private final ArrayList<PacketListener> everyPacketHandlers
            = new ArrayList<>();


    public <T extends Packet<?>> void registerListener(InboundPacketListener<T> packetListener, Class<T> packetClazz) {
        this.registerListenerInternally(packetListener, packetClazz);
    }

    public <T extends Packet<?>> void registerListener(OutbountPacketListener<T> packetListener, Class<T> packetClazz) {
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

    @SuppressWarnings("unchecked")
    public <T extends Packet<?>> void unregisterListener(InboundPacketListener<T> packetListener, Class<T> packetClazz) {
        if (packetClazz.equals(Packet.class)) {
            everyPacketHandlers.remove((InboundPacketListener<Packet<?>>) packetListener);
            return;
        }
        ArrayList<PacketListener> list = packetHandlers.get(packetClazz);
        if (list == null) return;
        list.remove(packetListener);
    }

    public <T extends Packet<?>> boolean dispatchInbound(T packet) {
        PacketInboundEvent<T> vanillaPacketEvent = new PacketInboundEvent<>(packet);
        for (PacketListener listener : everyPacketHandlers) {
            listener.handle(vanillaPacketEvent);
        }
        for (PacketListener listener : packetHandlers.get(packet.getClass())) {
            listener.handle(vanillaPacketEvent);
        }
        if (vanillaPacketEvent.isCancelled()) return false;
        SimplePacketsPacket<T> simplePacketsPacket = SimplePacketsPacket.get(packet);
        if (simplePacketsPacket == null) return false;
        PacketInboundEvent<SimplePacketsPacket<T>> simplePacketsPacketPacketEvent = new PacketInboundEvent<>(simplePacketsPacket);
        for (PacketListener listener : packetHandlers.get(simplePacketsPacket.getClass())) {
            listener.handle(simplePacketsPacketPacketEvent);
        }
        return !simplePacketsPacketPacketEvent.isCancelled();
    }

    public <T extends Packet<?>> boolean dispatchOutbound(T packet) {
        PacketOutboundEvent<T> vanillaPacketEvent = new PacketOutboundEvent<>(packet);
        for (PacketListener listener : everyPacketHandlers) {
            listener.handle(vanillaPacketEvent);
        }
        for (PacketListener listener : packetHandlers.get(packet.getClass())) {
            listener.handle(vanillaPacketEvent);
        }
        if (vanillaPacketEvent.isCancelled()) return false;
        SimplePacketsPacket<T> simplePacketsPacket = SimplePacketsPacket.get(packet);
        if (simplePacketsPacket == null) return false;
        PacketOutboundEvent<SimplePacketsPacket<T>> simplePacketsPacketPacketEvent = new PacketOutboundEvent<>(simplePacketsPacket);
        for (PacketListener listener : packetHandlers.get(simplePacketsPacket.getClass())) {
            listener.handle(simplePacketsPacketPacketEvent);
        }
        return !simplePacketsPacketPacketEvent.isCancelled();
    }

}
