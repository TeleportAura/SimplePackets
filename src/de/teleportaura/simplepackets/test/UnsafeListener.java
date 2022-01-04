package de.teleportaura.simplepackets.test;

import de.teleportaura.simplepackets.api.event.InboundPacketListener;
import de.teleportaura.simplepackets.api.event.PacketInboundEvent;
import net.minecraft.server.v1_8_R3.PacketPlayInArmAnimation;
import org.bukkit.Bukkit;

public class UnsafeListener implements InboundPacketListener<PacketPlayInArmAnimation> {

    @Override
    public void handleInbound(PacketInboundEvent<PacketPlayInArmAnimation> e) {
        Bukkit.broadcastMessage(e.toString());
    }
}
