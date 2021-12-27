package de.teleportaura.simplepackets.internal.netty;

import de.teleportaura.simplepackets.internal.SimplePacketsPlugin;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.MessageToMessageEncoder;
import net.minecraft.server.v1_8_R3.Packet;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("rawtypes")
public class SPEncoder extends MessageToMessageEncoder<Packet> {

    public UUID uuid;

    public SPEncoder(UUID uuid) {
        this.uuid = uuid;
    }

    /*TODO make this approach more clean by making the write method do the work thus rendering encode useless
    *  and bypassing netty's internal checks*/

    public void write(ChannelHandlerContext chCtx, Object packet, ChannelPromise channelPromise) throws Exception {
        if(SimplePacketsPlugin.instance().eventManager.dispatchOutbound((Packet<?>) packet, uuid))
            chCtx.write(packet, channelPromise);
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) {}
}
