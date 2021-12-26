package de.teleportaura.simplepackets.internal.netty;

import io.netty.channel.ChannelPipeline;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

public class NettyInjector {

    public static void inject(Player p) {
        ChannelPipeline pipeline =
                ((CraftPlayer) p).getHandle().playerConnection.networkManager.channel.pipeline();
        pipeline.addAfter("decoder", "SPDecoder", new SPDecoder(p.getUniqueId()));
        pipeline.addAfter("encoder", "SPEncoder", new SPEncoder(p.getUniqueId()));
    }

}
