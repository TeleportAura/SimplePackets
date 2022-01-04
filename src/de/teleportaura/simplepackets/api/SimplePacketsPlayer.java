package de.teleportaura.simplepackets.api;

import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.util.UUID;

public class SimplePacketsPlayer {

    private UUID uuid;
    private PlayerConnection connection;

    /**
     * @deprecated only for (de)serialization
     */
    @Deprecated
    public SimplePacketsPlayer() {
    }

    private SimplePacketsPlayer(UUID uuid, Player player) {
        this.uuid = uuid;
        connection = ((CraftPlayer) getHandle()).getHandle().playerConnection;
    }

    public SimplePacketsPlayer(Player player) {
        this(player.getUniqueId(), player);
    }

    public static SimplePacketsPlayer fromUUID(UUID uuid) {
        Player p = Bukkit.getPlayer(uuid);
        if (p == null) throw new Error("There is no player with uuid \"" + uuid + "\"!");
        return new SimplePacketsPlayer(uuid, p);
    }

    public void sendPacket(Packet<?> packet) {
        connection.sendPacket(packet);
    }

    public UUID getUUID() {
        return uuid;
    }

    public Player getHandle() {
        return Bukkit.getPlayer(uuid);
    }

}
