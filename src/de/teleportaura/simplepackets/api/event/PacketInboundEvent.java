package de.teleportaura.simplepackets.api.event;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.event.HandlerList;

@SuppressWarnings("rawtypes")
public class PacketInboundEvent<T extends Packet> extends PacketEvent<T> {

    private static HandlerList handlers;

    public PacketInboundEvent(T packet) {
        super(packet);
    }

    @Override
    public HandlerList getHandlers() {
        return getHandlerList();
    }
    public static HandlerList getHandlerList() {
        return handlers;
    }
}
