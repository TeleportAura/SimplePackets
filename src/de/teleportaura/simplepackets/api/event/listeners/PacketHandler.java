package de.teleportaura.simplepackets.api.event.listeners;

import de.teleportaura.simplepackets.api.SimplePacketsAPI;

import java.lang.reflect.Method;
import java.util.HashMap;

public class PacketHandler implements PacketListener {

    private final HashMap<String, Method> functionMap = new HashMap<>();

    public void register() {
        for (Method m : getClass().getMethods()) {
            PacketEvent annotation = m.getAnnotation(PacketEvent.class);
            if (annotation == null) continue;
            SimplePacketsAPI.Unsafe.registerListenerUnsafe(this, annotation.packetType());
            functionMap.put(annotation.packetType().getName(), m);
        }
    }


    @SuppressWarnings("rawtypes")
    public void handle(de.teleportaura.simplepackets.api.event.events.PacketEvent event) {
        try {
            functionMap.get(event.getPacket().getClass().getName()).invoke(this, event);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

}
