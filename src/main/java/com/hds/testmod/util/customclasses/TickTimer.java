package com.hds.testmod.util.customclasses;

public abstract class TickTimer {
    public static final int TICKS_PER_SECOND = 20;
    private int currentTicks = 0;
    private int timeout = 0;

    public TickTimer(int timeout) {
        this.timeout = timeout;
    }

    public abstract void onTimeout();

    public void tick() {
        if (++currentTicks / TICKS_PER_SECOND >= timeout) {
            onTimeout();
            currentTicks = 0;
        }
    }
}
