package com.hds.yarcot.util.customclasses;

public abstract class TickTimer {
    public static final int TICKS_PER_SECOND = 20;
    private final int TIMEOUT;
    private int currentTicks;

    public TickTimer(int timeout) {
        this.TIMEOUT = timeout;
    }

    public abstract void onTimeout();

    public void tick() {
        if (++currentTicks / TICKS_PER_SECOND >= TIMEOUT) {
            onTimeout();
            currentTicks = 0;
        }
    }
}
