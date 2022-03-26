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

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import org.infernalstudios.foodeffects.config.FoodEffectsConfig.Effects;
import org.infernalstudios.foodeffects.config.FoodEffectsConfig.General;

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

        if ((stack.getItem() == Items.COOKIE || stack.getItem() == Items.SWEET_BERRIES) && General.eat_cookies_berries_fast) {
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
                () -> MOB_EFFECTS.getValue(new ResourceLocation(Effects.pufferfish_effect)),
                () -> (int)(Effects.pufferfish_effect_duration * 20),
                () -> Effects.pufferfish_effect_amplifier
            )
        );
        EFFECT_MAP.put(
            Items.MUSHROOM_STEW,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(Effects.mushroom_stew_effect)),
                () -> (int)(Effects.mushroom_stew_effect_duration * 20),
                () -> Effects.mushroom_stew_effect_amplifier
            )
        );
        EFFECT_MAP.put(
            Items.RABBIT_STEW,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(Effects.rabbit_stew_effect)),
                () -> (int)(Effects.rabbit_stew_effect_duration * 20),
                () -> Effects.rabbit_stew_effect_amplifier
            )
        );
        EFFECT_MAP.put(
            Items.BEETROOT_SOUP,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(Effects.beetroot_soup_effect)),
                () -> (int)(Effects.beetroot_soup_effect_duration * 20),
                () -> Effects.beetroot_soup_effect_amplifier
            )
        );
        EFFECT_MAP.put(
            Items.COOKIE,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(Effects.cookie_effect)),
                () -> (int)(Effects.cookie_effect_duration * 20),
                () -> Effects.cookie_effect_amplifier
            )
        );
        EFFECT_MAP.put(
            Items.PUMPKIN_PIE,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(Effects.pumpkin_pie_effect)),
                () -> (int)(Effects.pumpkin_pie_effect_duration * 20),
                () -> Effects.pumpkin_pie_effect_amplifier
            )
        );
        EFFECT_MAP.put(
            Items.HONEY_BOTTLE,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(Effects.honey_bottle_effect)),
                () -> (int)(Effects.honey_bottle_effect_duration * 20),
                () -> Effects.honey_bottle_effect_amplifier
            )
        );
        EFFECT_MAP.put(
            Items.BAKED_POTATO,
            new EffectData(
                () -> MOB_EFFECTS.getValue(new ResourceLocation(Effects.baked_potato_effect)),
                () -> (int)(Effects.baked_potato_effect_duration * 20),
                () -> Effects.baked_potato_effect_amplifier
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
