package com.brunocollingridge.foodeffects.core.event;

import com.brunocollingridge.foodeffects.FoodEffects;

import com.brunocollingridge.foodeffects.core.FoodEffectsConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;

@EventBusSubscriber(modid=FoodEffects.MOD_ID)
public class EventsHub {
	
	@SubscribeEvent
	static void foodEffectsList(LivingEntityUseItemEvent.Finish event) {
		   ItemStack stack = event.getItem();
		   PlayerEntity player = (PlayerEntity) event.getEntity();
		   IForgeRegistry<Effect> EFFECTS = ForgeRegistries.POTIONS;
		   FoodEffectsConfig config = FoodEffectsConfig.COMMON;
		   
		   // Eating Dried Kelp now clears the poison, nausea, and blindness effects.
		   if (stack.isItemEqual(stack) && stack.getItem() == Items.DRIED_KELP && stack.getUseAction() == UseAction.EAT) {
			   player.removePotionEffect(Effects.POISON);
			   player.removePotionEffect(Effects.NAUSEA);
			   player.removePotionEffect(Effects.BLINDNESS);
		   }
		   
		   // Eating a Pufferfish gives the effect Water Breathing for 120 seconds.
		   if (stack.isItemEqual(stack) && stack.getItem() == Items.PUFFERFISH && stack.getUseAction() == UseAction.EAT && EFFECTS.getValue(new ResourceLocation(config.PUFFERFISH_EFFECT.get())) != null) {
			   player.addPotionEffect(new EffectInstance(EFFECTS.getValue(new ResourceLocation(config.PUFFERFISH_EFFECT.get())), config.PUFFERFISH_EFFECT_DURATION.get() * 20, 0));
		   }
		   
		   // Eating a Mushroom Stew gives the effect Regeneration II for 5 seconds.
		   if (stack.isItemEqual(stack) && stack.getItem() == Items.MUSHROOM_STEW && stack.getUseAction() == UseAction.EAT && EFFECTS.getValue(new ResourceLocation(config.MUSHROOM_STEW_EFFECT.get())) != null) {
			   player.addPotionEffect(new EffectInstance(EFFECTS.getValue(new ResourceLocation(config.MUSHROOM_STEW_EFFECT.get())), config.MUSHROOM_STEW_EFFECT_DURATION.get() * 20, 1));
		   }
		   
		   // Eating a Rabbit Stew gives the effect Jump Boost II for 10 seconds.
		   if (stack.isItemEqual(stack) && stack.getItem() == Items.RABBIT_STEW && stack.getUseAction() == UseAction.EAT && EFFECTS.getValue(new ResourceLocation(config.RABBIT_STEW_EFFECT.get())) != null) {
			   player.addPotionEffect(new EffectInstance(EFFECTS.getValue(new ResourceLocation(config.RABBIT_STEW_EFFECT.get())), config.RABBIT_STEW_EFFECT_DURATION.get() * 20, 1));
		   }
		   
		   // Eating a Beetroot Soup gives the effect Health Boost 1/2 for 30 seconds.
		   if (stack.isItemEqual(stack) && stack.getItem() == Items.BEETROOT_SOUP && stack.getUseAction() == UseAction.EAT && EFFECTS.getValue(new ResourceLocation(config.BEETROOT_SOUP_EFFECT.get())) != null) {
			   player.addPotionEffect(new EffectInstance(EFFECTS.getValue(new ResourceLocation(config.BEETROOT_SOUP_EFFECT.get())), config.BEETROOT_SOUP_EFFECT_DURATION.get() * 20, 0));
		   }
		   
		   
		   // Eating a Cookie gives the effect Speed I for 5 seconds.
		   if (stack.isItemEqual(stack) && stack.getItem() == Items.COOKIE && stack.getUseAction() == UseAction.EAT && EFFECTS.getValue(new ResourceLocation(config.COOKIE_EFFECT.get())) != null) {
			   player.addPotionEffect(new EffectInstance(EFFECTS.getValue(new ResourceLocation(config.COOKIE_EFFECT.get())), config.COOKIE_EFFECT_DURATION.get() * 20, 0));
		   }
		   
		   // Eating a Pumpkin Pie gives the effect Haste I for 15 seconds.
		   if (stack.isItemEqual(stack) && stack.getItem() == Items.PUMPKIN_PIE && stack.getUseAction() == UseAction.EAT && EFFECTS.getValue(new ResourceLocation(config.PUMPKIN_PIE_EFFECT.get())) != null) {
			   player.addPotionEffect(new EffectInstance(EFFECTS.getValue(new ResourceLocation(config.PUMPKIN_PIE_EFFECT.get())), config.PUMPKIN_PIE_EFFECT_DURATION.get() * 20, 1));
		   }
		   
		   // Drinking a Bottle of Honey restores 2 hearts.
	       if (stack.isItemEqual(stack) && stack.getItem()==Items.HONEY_BOTTLE && stack.getUseAction() == UseAction.DRINK && EFFECTS.getValue(new ResourceLocation(config.HONEY_BOTTLE_EFFECT.get())) != null) {
	           player.addPotionEffect(new EffectInstance(EFFECTS.getValue(new ResourceLocation(config.HONEY_BOTTLE_EFFECT.get())), config.HONEY_BOTTLE_EFFECT_DURATION.get() * 20, 1/4));
	       }
	}

	@SubscribeEvent
	static void fasterEating(LivingEntityUseItemEvent.Start event) {
		if (FoodEffectsConfig.COMMON.EAT_COOKIES_BERRIES_FAST.get()) {
			ItemStack stack = event.getItem();
			if (stack.getItem() == Items.COOKIE || stack.getItem() == Items.SWEET_BERRIES) {
				// Default is 32, 16 is twice as fast, just like Dried Kelp
				event.setDuration(16);
			}
		}
	}
}