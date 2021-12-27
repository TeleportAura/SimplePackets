package de.teleportaura.simplepackets.api.event;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.event.Event;

@SuppressWarnings("rawtypes")
public abstract class PacketEvent<T extends Packet> extends Event {

    protected T packet;

    protected boolean cancelled = false;

    public PacketEvent(T packet) {
        this.packet = packet;
    }


    public T getPacket() {
        return packet;
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
