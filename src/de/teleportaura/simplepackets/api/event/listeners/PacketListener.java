package de.teleportaura.simplepackets.api.event.listeners;

import de.teleportaura.simplepackets.api.event.events.PacketEvent;

public interface PacketListener {

    @SuppressWarnings("rawtypes")
    default void handle(PacketEvent e) {
        System.out.println("nonononono");
    }

}
