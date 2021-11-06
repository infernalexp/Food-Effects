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

package org.infernalstudios.foodeffects;

import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import org.infernalstudios.foodeffects.config.FoodEffectsConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid = FoodEffects.MOD_ID)
public class FoodEffectsEvents {
    private final FoodEffectsConfig config = FoodEffectsConfig.COMMON;

    @SubscribeEvent
    public void onLivingEntityUseItemStart(LivingEntityUseItemEvent.Start event) {
        ItemStack stack = event.getItem();

        if ((stack.getItem() == Items.COOKIE || stack.getItem() == Items.SWEET_BERRIES) && this.config.EAT_COOKIES_BERRIES_FAST.get()) {
            // Default is 32, 16 is twice as fast, just like Dried Kelp
            event.setDuration(16);
        }
    }

    @SubscribeEvent
    public void onLivingEntityUseItemFinish(LivingEntityUseItemEvent.Finish event) {
        ItemStack stack = event.getItem();
        PlayerEntity player = (PlayerEntity) event.getEntity();
        IForgeRegistry<Effect> EFFECTS = ForgeRegistries.POTIONS;

        if (stack.getItem() == Items.DRIED_KELP) {
            player.removePotionEffect(Effects.POISON);
            player.removePotionEffect(Effects.NAUSEA);
            player.removePotionEffect(Effects.BLINDNESS);
        } else if (stack.getItem() == Items.PUFFERFISH) {
            Effect pufferfishEffect = EFFECTS.getValue(new ResourceLocation(this.config.PUFFERFISH_EFFECT.get()));
            if (pufferfishEffect != null) {
                player.addPotionEffect(new EffectInstance(pufferfishEffect, (int)(this.config.PUFFERFISH_EFFECT_DURATION.get() * 20), 0));
            }
        } else if (stack.getItem() == Items.MUSHROOM_STEW) {
            Effect mushroomStewEffect = EFFECTS.getValue(new ResourceLocation(this.config.MUSHROOM_STEW_EFFECT.get()));
            if (mushroomStewEffect != null) {
                player.addPotionEffect(new EffectInstance(mushroomStewEffect, (int)(this.config.MUSHROOM_STEW_EFFECT_DURATION.get() * 20), 1));
            }
        } else if (stack.getItem() == Items.RABBIT_STEW) {
            Effect rabbitStewEffect = EFFECTS.getValue(new ResourceLocation(this.config.RABBIT_STEW_EFFECT.get()));
            if (rabbitStewEffect != null) {
                player.addPotionEffect(new EffectInstance(rabbitStewEffect, (int)(this.config.RABBIT_STEW_EFFECT_DURATION.get() * 20), 1));
            }
        } else if (stack.getItem() == Items.BEETROOT_SOUP) {
            Effect beetrootSoupEffect = EFFECTS.getValue(new ResourceLocation(this.config.BEETROOT_SOUP_EFFECT.get()));
            if (beetrootSoupEffect != null) {
                player.addPotionEffect(new EffectInstance(beetrootSoupEffect, (int)(this.config.BEETROOT_SOUP_EFFECT_DURATION.get() * 20), 0));
            }
        } else if (stack.getItem() == Items.COOKIE) {
            Effect cookieEffect = EFFECTS.getValue(new ResourceLocation(this.config.COOKIE_EFFECT.get()));
            if (cookieEffect != null) {
                player.addPotionEffect(new EffectInstance(cookieEffect, (int)(this.config.COOKIE_EFFECT_DURATION.get() * 20), 0));
            }
        } else if (stack.getItem() == Items.PUMPKIN_PIE) {
            Effect pumpkinPieEffect = EFFECTS.getValue(new ResourceLocation(this.config.PUMPKIN_PIE_EFFECT.get()));
            if (pumpkinPieEffect != null) {
                player.addPotionEffect(new EffectInstance(pumpkinPieEffect, (int)(this.config.PUMPKIN_PIE_EFFECT_DURATION.get() * 20), 1));
            }
        } else if (stack.getItem() == Items.HONEY_BOTTLE) {
            Effect honeyBottleEffect = EFFECTS.getValue(new ResourceLocation(this.config.HONEY_BOTTLE_EFFECT.get()));
            if (honeyBottleEffect != null) {
                player.addPotionEffect(new EffectInstance(honeyBottleEffect, (int)(this.config.HONEY_BOTTLE_EFFECT_DURATION.get() * 20), 0));
            }
        } else if (stack.getItem() == Items.BAKED_POTATO) {
            Effect bakedPotatoEffect = EFFECTS.getValue(new ResourceLocation(this.config.BAKED_POTATO_EFFECT.get()));
            if (bakedPotatoEffect != null) {
                player.addPotionEffect(new EffectInstance(bakedPotatoEffect, (int)(this.config.BAKED_POTATO_EFFECT_DURATION.get() * 20), 0));
            }
        }
    }
}