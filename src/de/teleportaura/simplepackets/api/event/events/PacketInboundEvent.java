package de.teleportaura.simplepackets.api.event.events;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.event.HandlerList;

import java.util.UUID;

@SuppressWarnings("rawtypes")
public class PacketInboundEvent<T extends Packet> extends PacketEvent<T> {

    private static HandlerList handlers;

    public PacketInboundEvent(T packet, UUID player) {
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
