package de.teleportaura.simplepackets.api.event;

public interface PacketListener {

    default void handle(PacketEvent e) {
        System.out.println("nonononono");
    }

}
