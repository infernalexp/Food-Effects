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

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.infernalstudios.foodeffects.config.FoodEffectsConfig;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLConstructModEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;

@EventBusSubscriber(modid = FoodEffects.MOD_ID)
public class FoodEffectsEvents {
    @SubscribeEvent
    public void onLivingEntityUseItemStart(LivingEntityUseItemEvent.Start event) {
        ItemStack stack = event.getItem();

        if ((stack.getItem() == Items.COOKIE || stack.getItem() == Items.SWEET_BERRIES) && FoodEffectsConfig.eat_cookies_berries_fast) {
            // Default is 32, 16 is twice as fast, just like Dried Kelp
            event.setDuration(16);
        }
    }

    protected static final Map<Item, List<EffectData>> EFFECT_MAP = new ConcurrentHashMap<>();

    @SubscribeEvent
    public void onLivingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        if (event.getEntity() instanceof Player player) {
            onEat(player, event.getItem().getItem());
        }
    }

    public static void onEat(Player player, Item item) {
        if (item == Items.DRIED_KELP) {
            player.removeEffect(MobEffects.POISON);
            player.removeEffect(MobEffects.CONFUSION);
            player.removeEffect(MobEffects.BLINDNESS);
        }
        List<EffectData> effectData = EFFECT_MAP.get(item);
        if (effectData == null) {
            return;
        }
        for (EffectData effect : effectData) {
            if (effect.getDuration() == 0) {
                player.removeEffect(effect.getEffect());
            } else {
                player.addEffect(
                    new MobEffectInstance(
                        effect.getEffect(),
                        effect.getDuration(),
                        effect.getAmplifier()
                    )
                );
            }
        }
    }
}
