/*
 * Copyright 2022 Infernal Studios
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

import org.infernalstudios.config.annotation.Category;
import org.infernalstudios.config.annotation.Configurable;
import org.infernalstudios.config.annotation.DoubleRange;
import org.infernalstudios.config.annotation.IntegerRange;

public class FoodEffectsConfig {
    @Category("General")
    public static class General {
        @Configurable(description = "Determines if Sweet Berries and Cookies should be eaten as fast as Dried Kelp")
        public static boolean eat_cookies_berries_fast = true;
    }

    @Category("Effects")
    public static class Effects {
        @Configurable(description = "Determines what effect consuming Pufferfish will give (in addition to existing effects)")
        public static String pufferfish_effect = "minecraft:water_breathing";

        @Configurable(description = "Determines how long (in seconds) the Pufferfish Food Effect will last")
        @DoubleRange(min = 0, max = 1000000)
        public static double pufferfish_effect_duration = 10.0;

        @Configurable(description = "Determines what level the Pufferfish Food Effect will be (starting at 0 as level 1)")
        @IntegerRange(min = 0, max = 255)
        public static int pufferfish_effect_amplifier = 0;

        @Configurable(description = "Determines what effect consuming Mushroom Stew will give")
        public static String mushroom_stew_effect = "minecraft:regeneration";

        @Configurable(description = "Determines how long (in seconds) the Mushroom Stew Food Effect will last")
        @DoubleRange(min = 0, max = 1000000)
        public static double mushroom_stew_effect_duration = 5.0;

        @Configurable(description = "Determines what level the Mushroom Stew Food Effect will be (starting at 0 as level 1)")
        @IntegerRange(min = 0, max = 255)
        public static int mushroom_stew_effect_amplifier = 1;

        @Configurable(description = "Determines what effect consuming Rabbit Stew will give")
        public static String rabbit_stew_effect = "minecraft:jump_boost";

        @Configurable(description = "Determines how long (in seconds) the Rabbit Stew Food Effect will last")
        @DoubleRange(min = 0, max = 1000000)
        public static double rabbit_stew_effect_duration = 10.0;

        @Configurable(description = "Determines what level the Rabbit Stew Food Effect will be (starting at 0 as level 1)")
        @IntegerRange(min = 0, max = 255)
        public static int rabbit_stew_effect_amplifier = 1;

        @Configurable(description = "Determines what effect consuming Beetroot Soup will give")
        public static String beetroot_soup_effect = "minecraft:health_boost";

        @Configurable(description = "Determines how long (in seconds) the Beetroot Soup Food Effect will last")
        @DoubleRange(min = 0, max = 1000000)
        public static double beetroot_soup_effect_duration = 30.0;

        @Configurable(description = "Determines what level the Beetroot Soup Food Effect will be (starting at 0 as level 1)")
        @IntegerRange(min = 0, max = 255)
        public static int beetroot_soup_effect_amplifier = 0;

        @Configurable(description = "Determines what effect consuming Cookies will give")
        public static String cookie_effect = "minecraft:speed";

        @Configurable(description = "Determines how long (in seconds) the Cookie Food Effect will last")
        @DoubleRange(min = 0, max = 1000000)
        public static double cookie_effect_duration = 10.0;

        @Configurable(description = "Determines what level the Cookie Food Effect will be (starting at 0 as level 1)")
        @IntegerRange(min = 0, max = 255)
        public static int cookie_effect_amplifier = 0;

        @Configurable(description = "Determines what effect consuming Pumpkin Pie will give")
        public static String pumpkin_pie_effect = "minecraft:haste";

        @Configurable(description = "Determines how long (in seconds) the Pumpkin Pie Food Effect will last")
        @DoubleRange(min = 0, max = 1000000)
        public static double pumpkin_pie_effect_duration = 15.0;

        @Configurable(description = "Determines what level the Pumpkin Pie Food Effect will be (starting at 0 as level 1)")
        @IntegerRange(min = 0, max = 255)
        public static int pumpkin_pie_effect_amplifier = 1;

        @Configurable(description = "Determines what effect consuming Honey Bottles will give")
        public static String honey_bottle_effect = "minecraft:instant_health";

        @Configurable(description = "Determines how long (in seconds) the Honey Bottle Food Effect will last")
        @DoubleRange(min = 0, max = 1000000)
        public static double honey_bottle_effect_duration = 0.05;

        @Configurable(description = "Determines what level the Honey Bottle Food Effect will be (starting at 0 as level 1)")
        @IntegerRange(min = 0, max = 255)
        public static int honey_bottle_effect_amplifier = 0;

        @Configurable(description = "Determines what effect consuming Baked Potatoes will give")
        public static String baked_potato_effect = "minecraft:strength";

        @Configurable(description = "Determines how long (in seconds) the Baked Potatoes Food Effect will last")
        @DoubleRange(min = 0, max = 1000000)
        public static double baked_potato_effect_duration = 10.0;

        @Configurable(description = "Determines what level the Baked Potato Food Effect will be (starting at 0 as level 1)")
        @IntegerRange(min = 0, max = 255)
        public static int baked_potato_effect_amplifier = 0;

        @Configurable(description = "Determines what effect consuming a slice of Cake will give")
        public static String cake_effect = "minecraft:speed";

        @Configurable(description = "Determines how long (in seconds) the Cake Food Effect will last")
        @DoubleRange(min = 0, max = 1000000)
        public static double cake_effect_duration = 20.0;

        @Configurable(description = "Determines what level the Cake Food Effect will be (starting at 0 as level 1)")
        @IntegerRange(min = 0, max = 255)
        public static int cake_effect_amplifier = 1;
    }
}