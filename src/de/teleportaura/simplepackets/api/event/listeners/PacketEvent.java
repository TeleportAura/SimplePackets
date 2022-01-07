package de.teleportaura.simplepackets.api.event.listeners;

import net.minecraft.server.v1_8_R3.Packet;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PacketEvent {

    @SuppressWarnings("rawtypes")
    Class<? extends Packet> packetType();

}
