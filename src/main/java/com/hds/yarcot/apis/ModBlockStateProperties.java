package com.hds.yarcot.apis;

import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

import javax.annotation.Nonnull;

public class ModBlockStateProperties {

    public static final IntegerProperty NORTH_CONNECTION = IntegerProperty.create("north_connection", 0, 3);
    public static final IntegerProperty SOUTH_CONNECTION = IntegerProperty.create("south_connection", 0, 3);
    public static final IntegerProperty UP_CONNECTION = IntegerProperty.create("up_connection"   , 0, 3);
    public static final IntegerProperty DOWN_CONNECTION = IntegerProperty.create("down_connection" , 0, 3);
    public static final IntegerProperty EAST_CONNECTION = IntegerProperty.create("east_connection" , 0, 3);
    public static final IntegerProperty WEST_CONNECTION = IntegerProperty.create("west_connection" , 0, 3);

    public enum CONNECTION_TYPE {
        ALL(0), INPUT(1), OUTPUT(2), NONE(3);

        private final int VALUE;
        CONNECTION_TYPE(int value) { this.VALUE = value; }
        public int get() { return this.VALUE; }
    }

    public static class DirectionalProperty<T extends Property<PT>, PT extends Comparable<PT>> {
        private final T NORTH;
        private final T SOUTH;
        private final T UP;
        private final T DOWN;
        private final T EAST;
        private final T WEST;

        private DirectionalProperty(T north, T south, T up, T down, T east, T west) {
            this.NORTH = north;
            this.SOUTH = south;
            this.UP    = up;
            this.DOWN  = down;
            this.EAST  = east;
            this.WEST  = west;
        }

        @Nonnull
        public T getFromDirection(@Nonnull Direction direction) {
            switch (direction) {
                case NORTH:
                    return this.NORTH;
                case SOUTH:
                    return this.SOUTH;
                case UP:
                    return this.UP;
                case DOWN:
                    return this.DOWN;
                case EAST:
                    return this.EAST;
                default:
                    return this.WEST;
            }
        }
    }

    public static final DirectionalProperty<BooleanProperty, Boolean> BOOLEAN_DIRECTION = new DirectionalProperty<>(
            BlockStateProperties.NORTH,
            BlockStateProperties.SOUTH,
            BlockStateProperties.UP,
            BlockStateProperties.DOWN,
            BlockStateProperties.EAST,
            BlockStateProperties.WEST
    );

    public static final DirectionalProperty<IntegerProperty, Integer> CONNECTION = new DirectionalProperty<>(
            NORTH_CONNECTION,
            SOUTH_CONNECTION,
            UP_CONNECTION,
            DOWN_CONNECTION,
            EAST_CONNECTION,
            WEST_CONNECTION
    );

    public static BlockState incrementIntegerProperty(BlockState state, IntegerProperty property, int min, int max) {
        int currentValue = state.get(property);
        if (currentValue >= max)
            currentValue = min;
        else
            currentValue++;

        return state.with(property, currentValue);
    }
}
