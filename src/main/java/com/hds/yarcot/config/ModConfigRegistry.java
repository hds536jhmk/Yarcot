package com.hds.yarcot.config;

import com.hds.yarcot.Yarcot;
import com.hds.yarcot.blocks.batteries.sapphire.SapphireBattery;
import com.hds.yarcot.blocks.conduits.sapphire.SapphireConduit;
import com.hds.yarcot.blocks.furnaces.sapphire.SapphireFurnace;
import com.hds.yarcot.blocks.miners.sapphire.SapphireMiner;
import com.hds.yarcot.blocks.ores.LithiumOre;
import com.hds.yarcot.blocks.ores.RubyOre;
import com.hds.yarcot.blocks.ores.SapphireOre;
import com.hds.yarcot.blocks.ores.SulfurOre;
import com.hds.yarcot.config.blocks.ModBatteryConfig;
import com.hds.yarcot.config.blocks.ModConduitConfig;
import com.hds.yarcot.config.blocks.ModFurnaceConfig;
import com.hds.yarcot.config.blocks.ModMinerConfig;
import com.hds.yarcot.config.world.ModOreConfig;
import com.hds.yarcot.util.StringUtilities;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ModConfigRegistry {

    public static final ForgeConfigSpec SERVER_CONFIG;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment(StringUtilities.capitalize(Yarcot.MOD_ID) + "'s Configuration")
                .push(Yarcot.MOD_ID);

        builder.comment("Blocks' Configuration")
                .push("blocks");

        SapphireBattery.config = new ModBatteryConfig(builder, "sapphire_battery", 10, 50, 1000);
        SapphireConduit.config = new ModConduitConfig(builder, "sapphire_conduit", 100, 100, 200);
        SapphireFurnace.config = new ModFurnaceConfig(builder, "sapphire_furnace", 200, 2, 1000, 0.5F);
        SapphireMiner.config = new ModMinerConfig(builder, "sapphire_miner", 100, 50, 100, 1000, 1.0F);

        builder.pop();

        builder.comment("World's Configuration")
                .push("world");

        builder.comment("Ores' Configuration")
                .push("ores");

        SapphireOre.config = new ModOreConfig(builder, "sapphire_ore", 3, 5, 5, 20);
        RubyOre.config = new ModOreConfig(builder, "ruby_ore", 3, 5, 5, 20);
        SulfurOre.config = new ModOreConfig(builder, "sulfur_ore", 25, 3, 10, 40);
        LithiumOre.config = new ModOreConfig(builder, "lithium_ore", 20, 3, 10, 30);

        builder.pop(2);

        SERVER_CONFIG = builder.build();
    }

    public static void registerConfigs(ModLoadingContext context) {
        context.registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
    }

}