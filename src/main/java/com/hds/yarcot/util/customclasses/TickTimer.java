package com.hds.yarcot.util.customclasses;

public class TickTimer {
    public static final int TICKS_PER_SECOND = 20;
    private final int TIMEOUT;
    private int currentTicks;

    public TickTimer(int timeout) {
        this.TIMEOUT = timeout;
    }

    public void onTimeout() {}

    public boolean tick() {
        if (++currentTicks / TICKS_PER_SECOND >= TIMEOUT) {
            onTimeout();
            currentTicks = 0;
            return true;
        }
        return false;
    }
}
