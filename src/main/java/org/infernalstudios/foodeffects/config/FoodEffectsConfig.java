/*
 * Copyright 2021 Infernal Studios
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.infernalstudios.foodeffects.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import org.apache.commons.lang3.tuple.Pair;

public class FoodEffectsConfig {
    public static final ForgeConfigSpec COMMON_SPEC;
    public static final FoodEffectsConfig COMMON;

    static {
        final Pair<FoodEffectsConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(FoodEffectsConfig::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public final ForgeConfigSpec.BooleanValue EAT_COOKIES_BERRIES_FAST;

    public final ForgeConfigSpec.ConfigValue<String> PUFFERFISH_EFFECT;
    public final ForgeConfigSpec.DoubleValue PUFFERFISH_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> MUSHROOM_STEW_EFFECT;
    public final ForgeConfigSpec.DoubleValue MUSHROOM_STEW_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> RABBIT_STEW_EFFECT;
    public final ForgeConfigSpec.DoubleValue RABBIT_STEW_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> BEETROOT_SOUP_EFFECT;
    public final ForgeConfigSpec.DoubleValue BEETROOT_SOUP_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> COOKIE_EFFECT;
    public final ForgeConfigSpec.DoubleValue COOKIE_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> PUMPKIN_PIE_EFFECT;
    public final ForgeConfigSpec.DoubleValue PUMPKIN_PIE_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> HONEY_BOTTLE_EFFECT;
    public final ForgeConfigSpec.DoubleValue HONEY_BOTTLE_EFFECT_DURATION;
    public final ForgeConfigSpec.ConfigValue<String> BAKED_POTATO_EFFECT;
    public final ForgeConfigSpec.DoubleValue BAKED_POTATO_EFFECT_DURATION;

    public FoodEffectsConfig(Builder builder) {
        builder.push("General");

        EAT_COOKIES_BERRIES_FAST = builder
                .comment("Determines if Sweet Berries and Cookies should be eaten as fast as Dried Kelp")
                .define("eat_cookies_berries_fast", true);

        builder.pop();

        builder.push("Effects");

        PUFFERFISH_EFFECT_DURATION = builder
                .comment("Determines how long (in seconds) the Pufferfish Food Effect will last")
                .defineInRange("pufferfish_effect_duration", 10.0, 0, 1000000);

        PUFFERFISH_EFFECT = builder
                .comment("Determines what effect consuming Pufferfish will give (in addition to existing effects)")
                .define("pufferfish_effect", "minecraft:water_breathing");

        MUSHROOM_STEW_EFFECT_DURATION = builder
                .comment("Determines how long (in seconds) the Mushroom Stew Food Effect will last")
                .defineInRange("mushroom_stew_effect_duration", 5.0, 0, 1000000);

        MUSHROOM_STEW_EFFECT = builder
                .comment("Determines what effect consuming Mushroom Stew will give")
                .define("mushroom_stew_effect", "minecraft:regeneration");

        RABBIT_STEW_EFFECT_DURATION = builder
                .comment("Determines how long (in seconds) the Rabbit Stew Food Effect will last")
                .defineInRange("rabbit_stew_effect_duration", 10.0, 0, 1000000);

        RABBIT_STEW_EFFECT = builder
                .comment("Determines what effect consuming Rabbit Stew will give")
                .define("rabbit_stew_effect", "minecraft:jump_boost");

        BEETROOT_SOUP_EFFECT_DURATION = builder
                .comment("Determines how long (in seconds) the Beetroot Soup Food Effect will last")
                .defineInRange("beetroot_soup_effect_duration", 30.0, 0, 1000000);

        BEETROOT_SOUP_EFFECT = builder
                .comment("Determines what effect consuming Beetroot Soup will give")
                .define("beetroot_soup_effect", "minecraft:health_boost");

        COOKIE_EFFECT_DURATION = builder
                .comment("Determines how long (in seconds) the Cookie Food Effect will last")
                .defineInRange("cookie_effect_duration", 5.0, 0, 1000000);

        COOKIE_EFFECT = builder
                .comment("Determines what effect consuming Cookies will give")
                .define("cookie_effect", "minecraft:speed");

        PUMPKIN_PIE_EFFECT_DURATION = builder
                .comment("Determines how long (in seconds) the Pumpkin Pie Food Effect will last")
                .defineInRange("pumpkin_pie_effect_duration", 15.0, 0, 1000000);

        PUMPKIN_PIE_EFFECT = builder
                .comment("Determines what effect consuming Pumpkin Pie will give")
                .define("pumpkin_pie_effect", "minecraft:haste");

        HONEY_BOTTLE_EFFECT_DURATION = builder
                .comment("Determines how long (in seconds) the Honey Bottle Food Effect will last")
                .defineInRange("honey_bottle_effect_duration", 0.05, 0, 1000000);

        HONEY_BOTTLE_EFFECT = builder
                .comment("Determines what effect consuming Honey Bottles will give")
                .define("honey_bottle_effect", "minecraft:instant_health");

        BAKED_POTATO_EFFECT = builder
                .comment("Determines what effect consuming Baked Potatoes will give")
                .define("baked_potato_effect", "minecraft:strength");

        BAKED_POTATO_EFFECT_DURATION = builder
                .comment("Determines how long (in seconds) the Baked Potatoes Food Effect will last")
                .defineInRange("baked_potato_effect_duration", 10.0, 0, 1000000);

        builder.pop();
    }
}