package com.brunocollingridge.foodeffects.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class FoodEffectsConfig {
    public final ForgeConfigSpec.BooleanValue EAT_COOKIES_BERRIES_FAST;

    public final ForgeConfigSpec.ConfigValue<String> PUFFERFISH_EFFECT;
    public final ForgeConfigSpec.IntValue PUFFERFISH_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> MUSHROOM_STEW_EFFECT;
    public final ForgeConfigSpec.IntValue MUSHROOM_STEW_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> RABBIT_STEW_EFFECT;
    public final ForgeConfigSpec.IntValue RABBIT_STEW_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> BEETROOT_SOUP_EFFECT;
    public final ForgeConfigSpec.IntValue BEETROOT_SOUP_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> COOKIE_EFFECT;
    public final ForgeConfigSpec.IntValue COOKIE_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> PUMPKIN_PIE_EFFECT;
    public final ForgeConfigSpec.IntValue PUMPKIN_PIE_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> HONEY_BOTTLE_EFFECT;
    public final ForgeConfigSpec.IntValue HONEY_BOTTLE_EFFECT_DURATION;

    public static final ForgeConfigSpec COMMON_SPEC;
    public static final FoodEffectsConfig COMMON;

    static {
        final Pair<FoodEffectsConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(FoodEffectsConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public FoodEffectsConfig(ForgeConfigSpec.Builder builder) {
        builder.comment("Food Effects Config");

        builder.push("General");
            EAT_COOKIES_BERRIES_FAST = builder.comment(" Whether or not Cookies and Berries eat faster.").define("eat_cookies_berries_fast", true);
        builder.pop();

        builder.push("Effects");
            PUFFERFISH_EFFECT_DURATION = builder.comment("Duration in seconds").defineInRange("pufferfish_effect_duration", 120, 0, 999999);
            PUFFERFISH_EFFECT = builder.define("pufferfish_effect", "minecraft:water_breathing");
            MUSHROOM_STEW_EFFECT_DURATION = builder.comment("Duration in seconds").defineInRange("mushroom_stew_effect_duration", 5, 0, 999999);
            MUSHROOM_STEW_EFFECT = builder.define("mushroom_stew_effect", "minecraft:regeneration");
            RABBIT_STEW_EFFECT_DURATION = builder.comment("Duration in seconds").defineInRange("rabbit_stew_effect_duration", 10, 0, 999999);
            RABBIT_STEW_EFFECT = builder.define("rabbit_stew_effect", "minecraft:jump_boost");
            BEETROOT_SOUP_EFFECT_DURATION = builder.comment("Duration in seconds").defineInRange("beetroot_soup_effect_duration", 30, 0, 999999);
            BEETROOT_SOUP_EFFECT = builder.define("beetroot_soup_effect", "minecraft:health_boost");
            COOKIE_EFFECT_DURATION = builder.comment("Duration in seconds").defineInRange("cookie_effect_duration", 5, 0, 999999);
            COOKIE_EFFECT = builder.define("cookie_effect", "minecraft:speed");
            PUMPKIN_PIE_EFFECT_DURATION = builder.comment("Duration in seconds").defineInRange("pumpkin_pie_effect_duration", 15, 0, 999999);
            PUMPKIN_PIE_EFFECT = builder.define("pumpkin_pie_effect", "minecraft:haste");
            HONEY_BOTTLE_EFFECT_DURATION = builder.comment("Duration in seconds").defineInRange("honey_bottle_effect_duration", 1, 0, 999999);
            HONEY_BOTTLE_EFFECT = builder.define("honey_bottle_effect", "minecraft:instant_health");
        builder.pop();

        builder.build();
    }
}