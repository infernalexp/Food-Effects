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

package org.infernalstudios.foodeffects;

import static net.minecraftforge.registries.ForgeRegistries.MOB_EFFECTS;
import static org.infernalstudios.foodeffects.config.FoodEffectsConfig.COMMON;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = FoodEffects.MOD_ID)
public class FoodEffectsEvents {
    @SubscribeEvent
    public void onLivingEntityUseItemStart(LivingEntityUseItemEvent.Start event) {
        ItemStack stack = event.getItem();

        if ((stack.getItem() == Items.COOKIE || stack.getItem() == Items.SWEET_BERRIES) && COMMON.EAT_COOKIES_BERRIES_FAST.get()) {
            // Default is 32, 16 is twice as fast, just like Dried Kelp
            event.setDuration(16);
        }
    }

    private static record EffectData(Supplier<MobEffect> effect, Supplier<Integer> duration, Supplier<Integer> amplifier) {}
    
    private static final Map<Item, EffectData> EFFECT_MAP = new HashMap<>();
    static {
        EFFECT_MAP.put(
            Items.PUFFERFISH,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(COMMON.PUFFERFISH_EFFECT.get())),
                () -> (int)(COMMON.PUFFERFISH_EFFECT_DURATION.get() * 20),
                () -> COMMON.PUFFERFISH_EFFECT_AMPLIFIER.get()
            )
        );
        EFFECT_MAP.put(
            Items.MUSHROOM_STEW,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(COMMON.MUSHROOM_STEW_EFFECT.get())),
                () -> (int)(COMMON.MUSHROOM_STEW_EFFECT_DURATION.get() * 20),
                () -> COMMON.MUSHROOM_STEW_EFFECT_AMPLIFIER.get()
            )
        );
        EFFECT_MAP.put(
            Items.RABBIT_STEW,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(COMMON.RABBIT_STEW_EFFECT.get())),
                () -> (int)(COMMON.RABBIT_STEW_EFFECT_DURATION.get() * 20),
                () -> COMMON.RABBIT_STEW_EFFECT_AMPLIFIER.get()
            )
        );
        EFFECT_MAP.put(
            Items.BEETROOT_SOUP,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(COMMON.BEETROOT_SOUP_EFFECT.get())),
                () -> (int)(COMMON.BEETROOT_SOUP_EFFECT_DURATION.get() * 20),
                () -> COMMON.BEETROOT_SOUP_EFFECT_AMPLIFIER.get()
            )
        );
        EFFECT_MAP.put(
            Items.COOKIE,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(COMMON.COOKIE_EFFECT.get())),
                () -> (int)(COMMON.COOKIE_EFFECT_DURATION.get() * 20),
                () -> COMMON.COOKIE_EFFECT_AMPLIFIER.get()
            )
        );
        EFFECT_MAP.put(
            Items.PUMPKIN_PIE,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(COMMON.PUMPKIN_PIE_EFFECT.get())),
                () -> (int)(COMMON.PUMPKIN_PIE_EFFECT_DURATION.get() * 20),
                () -> COMMON.PUMPKIN_PIE_EFFECT_AMPLIFIER.get()
            )
        );
        EFFECT_MAP.put(
            Items.HONEY_BOTTLE,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(COMMON.HONEY_BOTTLE_EFFECT.get())),
                () -> (int)(COMMON.HONEY_BOTTLE_EFFECT_DURATION.get() * 20),
                () -> COMMON.HONEY_BOTTLE_EFFECT_AMPLIFIER.get()
            )
        );
        EFFECT_MAP.put(
            Items.BAKED_POTATO,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(COMMON.BAKED_POTATO_EFFECT.get())),
                () -> (int)(COMMON.BAKED_POTATO_EFFECT_DURATION.get() * 20),
                () -> COMMON.BAKED_POTATO_EFFECT_AMPLIFIER.get()
            )
        );
    }


    @SubscribeEvent
    public void onLivingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            ItemStack stack = event.getItem();
    
            if (stack.getItem() == Items.DRIED_KELP) {
                player.removeEffect(MobEffects.POISON);
                player.removeEffect(MobEffects.CONFUSION);
                player.removeEffect(MobEffects.BLINDNESS);
            } else {
                EffectData effectData = EFFECT_MAP.get(stack.getItem());
                if (effectData != null) {
                    MobEffectInstance effect = new MobEffectInstance(
                        effectData.effect.get(),
                        effectData.duration.get(),
                        effectData.amplifier.get()
                    );
                    player.addEffect(effect);
                }
            }
        }
    }
}
