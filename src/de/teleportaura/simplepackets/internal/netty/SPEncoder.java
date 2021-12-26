package de.teleportaura.simplepackets.internal.netty;

import io.netty.channel.ChannelHandlerContext;
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

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Packet packet, List<Object> list) {
        list.add(packet);
    }
}
