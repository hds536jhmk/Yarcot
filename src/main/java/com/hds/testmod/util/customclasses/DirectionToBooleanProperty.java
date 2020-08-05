package com.hds.testmod.util.customclasses;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;

import javax.annotation.Nonnull;

public class DirectionToBooleanProperty {
    @Nonnull
    public static BooleanProperty get(Direction dir) {
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
