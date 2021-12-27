package de.teleportaura.simplepackets.internal.netty;

import java.util.ArrayList;

public class FakeArrayList<E> extends ArrayList<E> {

    public int size(){
        return 1;
    }

}
