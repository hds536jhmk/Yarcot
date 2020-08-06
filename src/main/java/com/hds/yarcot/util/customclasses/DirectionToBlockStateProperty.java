package com.hds.yarcot.util.customclasses;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

import javax.annotation.Nonnull;

public class DirectionToBlockStateProperty {
    @Nonnull
    public static BooleanProperty getBooleanProperty(Direction dir) {
        switch (dir) {
            case WEST:
                return BlockStateProperties.WEST;
            case UP:
                return BlockStateProperties.UP;
            case SOUTH:
                return BlockStateProperties.SOUTH;
            case NORTH:
                return BlockStateProperties.NORTH;
            case EAST:
                return BlockStateProperties.EAST;
            default:
                return BlockStateProperties.DOWN;
        }
    }
}
