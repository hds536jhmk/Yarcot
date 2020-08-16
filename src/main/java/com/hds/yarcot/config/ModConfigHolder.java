package com.hds.yarcot.config;

import com.hds.yarcot.Yarcot;
import com.hds.yarcot.config.blocks.batteries.SapphireBatteryConfig;
import com.hds.yarcot.config.blocks.conduits.SapphireConduitConfig;
import com.hds.yarcot.config.blocks.furnaces.SapphireFurnaceConfig;
import com.hds.yarcot.config.blocks.miners.SapphireMinerConfig;
import com.hds.yarcot.config.world.ores.LithiumOreConfig;
import com.hds.yarcot.config.world.ores.RubyOreConfig;
import com.hds.yarcot.config.world.ores.SapphireOreConfig;
import com.hds.yarcot.config.world.ores.SulfurOreConfig;
import com.hds.yarcot.util.StringUtilities;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

public class ModConfigHolder {

    public static final ForgeConfigSpec SERVER_CONFIG;

    static {
        final ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment(StringUtilities.capitalize(Yarcot.MOD_ID) + "'s Configuration")
                .push(Yarcot.MOD_ID);

        builder.comment("Blocks' Configuration")
                .push("blocks");

        SapphireBatteryConfig.pushConfig(builder);
        SapphireConduitConfig.pushConfig(builder);
        SapphireFurnaceConfig.pushConfig(builder);
        SapphireMinerConfig.pushConfig(builder);

        builder.pop();

        builder.comment("World's Configuration")
                .push("world");

        builder.comment("Ores' Configuration")
                .push("ores");

        SapphireOreConfig.pushConfig(builder);
        RubyOreConfig.pushConfig(builder);
        SulfurOreConfig.pushConfig(builder);
        LithiumOreConfig.pushConfig(builder);

        builder.pop(2);

        SERVER_CONFIG = builder.build();
    }

    public static void registerConfigs(ModLoadingContext context) {
        context.registerConfig(ModConfig.Type.SERVER, SERVER_CONFIG);
    }

}
