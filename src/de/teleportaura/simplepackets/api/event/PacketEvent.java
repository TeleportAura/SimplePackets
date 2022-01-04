package de.teleportaura.simplepackets.api.event;

import de.teleportaura.simplepackets.api.SimplePacketsPlayer;
import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;

import java.util.UUID;

@SuppressWarnings("rawtypes")
public abstract class PacketEvent<T extends Packet> extends Event {

    protected T packet;
    protected UUID player;

    protected boolean cancelled = false;

    public PacketEvent(T packet, UUID player) {
        this.packet = packet;
    }

    public T getPacket() {
        return packet;
    }

    public UUID getPlayerUUID() {
        return player;
    }

    public SimplePacketsPlayer getPlayer() {
        return SimplePacketsPlayer.fromUUID(player);
    }

    public Player getBukkitPlayer() {
        return Bukkit.getPlayer(player);
    }

    public boolean isCancelled() {
        return this.cancelled;
    }

    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

}
