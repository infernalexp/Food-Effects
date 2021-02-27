package com.brunocollingridge.foodeffects.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class FoodEffectsConfig {
    public final ForgeConfigSpec.BooleanValue EAT_COOKIES_BERRIES_FAST;

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final FoodEffectsConfig COMMON;

    static {
        final Pair<FoodEffectsConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(FoodEffectsConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public FoodEffectsConfig(ForgeConfigSpec.Builder builder) {
        EAT_COOKIES_BERRIES_FAST = builder.define("eat_cookies_berries_fast", true);
    }
}