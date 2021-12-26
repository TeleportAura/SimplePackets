package de.teleportaura.simplepackets.api.event;

import org.bukkit.event.Event;

public interface PacketListener {

    default void handle(Event e){}

}
