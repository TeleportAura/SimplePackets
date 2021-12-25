package de.teleportaura.simplepackets.internal.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import net.minecraft.server.v1_8_R3.Packet;

import java.util.List;

@SuppressWarnings("rawtypes")
public class SPDecoder extends MessageToMessageDecoder<Packet> {
    
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) {
        list.add(packet);
    }
}