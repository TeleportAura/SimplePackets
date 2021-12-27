package de.teleportaura.simplepackets.internal.netty;

import de.teleportaura.simplepackets.internal.SimplePacketsPlugin;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.Packet;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("rawtypes")
public class SPDecoder extends MessageToMessageDecoder<Packet> {

    public UUID uuid;

    public SPDecoder(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) {
        if(SimplePacketsPlugin.instance().eventManager.dispatchInbound(packet, uuid))
        list.add(packet);
    }
}
