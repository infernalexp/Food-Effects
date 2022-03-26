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

import java.util.ArrayList;
import java.util.List;

import org.infernalstudios.config.annotation.Configurable;
import org.infernalstudios.foodeffects.EffectData;

import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.Items;

public class FoodEffectsConfig {
    static {
    }

    @Configurable(description = "Determines if Sweet Berries and Cookies should be eaten as fast as Dried Kelp", category = "General")
    public static boolean eat_cookies_berries_fast = true;

    @Configurable(handler = "org.infernalstudios.foodeffects.config.handler.EffectDataListConfigHandler.INSTANCE")
    public static List<EffectData> effects = new ArrayList<>(List.of(
        new EffectData(() -> Items.BAKED_POTATO,  () -> MobEffects.DAMAGE_BOOST,    200, 0),
        new EffectData(() -> Items.BEETROOT_SOUP, () -> MobEffects.HEALTH_BOOST,    600, 0),
        new EffectData(() -> Items.CAKE,          () -> MobEffects.MOVEMENT_SPEED,  400, 1),
        new EffectData(() -> Items.COOKIE,        () -> MobEffects.MOVEMENT_SPEED,  200, 0),
        new EffectData(() -> Items.DRIED_KELP,    () -> MobEffects.BLINDNESS,       0,   0),
        new EffectData(() -> Items.DRIED_KELP,    () -> MobEffects.CONFUSION,       0,   0),
        new EffectData(() -> Items.DRIED_KELP,    () -> MobEffects.POISON,          0,   0),
        new EffectData(() -> Items.HONEY_BOTTLE,  () -> MobEffects.HEAL,            1,   0),
        new EffectData(() -> Items.MUSHROOM_STEW, () -> MobEffects.REGENERATION,    100, 0),
        new EffectData(() -> Items.PUFFERFISH,    () -> MobEffects.WATER_BREATHING, 200, 0),
        new EffectData(() -> Items.PUMPKIN_PIE,   () -> MobEffects.DIG_SPEED,       300, 1),
        new EffectData(() -> Items.RABBIT_STEW,   () -> MobEffects.JUMP,            200, 1)
    )) {
        @Override
        public String toString() {
            return "";
        }
    };
}