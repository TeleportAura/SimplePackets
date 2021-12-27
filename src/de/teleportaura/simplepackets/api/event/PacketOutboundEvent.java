package de.teleportaura.simplepackets.api.event;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.event.HandlerList;

import java.util.UUID;

@SuppressWarnings("rawtypes")
public class PacketOutboundEvent<T extends Packet> extends PacketEvent<T> {

    private static HandlerList handlers;

    public PacketOutboundEvent(T packet, UUID player) {
        super(packet, player);
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }
}
