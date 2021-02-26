package com.brunocollingridge.foodeffects.core.event;

import com.brunocollingridge.foodeffects.FoodEffects;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.UseAction;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraftforge.event.entity.living.LivingEntityUseItemEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid=FoodEffects.MOD_ID)
public class EventsHub {
	
	@SubscribeEvent
	static void foodEffectsList(LivingEntityUseItemEvent.Finish event) {
		   ItemStack stack = event.getItem();
		   PlayerEntity player = (PlayerEntity) event.getEntity();
		   
		// Cookie, Speed II for 5 Seconds
	        if (stack.isItemEqual(stack) && stack.getItem()==Items.COOKIE && stack.getUseAction() == UseAction.EAT) {
	        	player.addPotionEffect(new EffectInstance(Effects.SPEED,(100), 1));
	        }
		   
	    // Honey Bottle, Instant Health I
	        if (stack.isItemEqual(stack) && stack.getItem()==Items.HONEY_BOTTLE && stack.getUseAction() == UseAction.DRINK) {
	        	player.addPotionEffect(new EffectInstance(Effects.INSTANT_HEALTH, 0));
	        }
	        
	    // Mushroom Stew, Regeneration II for 5 Seconds
	        if (stack.isItemEqual(stack) && stack.getItem()==Items.MUSHROOM_STEW && stack.getUseAction() == UseAction.EAT) {
	        	player.addPotionEffect(new EffectInstance(Effects.REGENERATION,(100), 1));
	        }
	        
	    // Beetroot Soup, Health Boost for 30 Seconds
	        if (stack.isItemEqual(stack) && stack.getItem()==Items.BEETROOT_SOUP && stack.getUseAction() == UseAction.EAT) {
	        	player.addPotionEffect(new EffectInstance(Effects.HEALTH_BOOST,(600), 1/2));
	        }
		   
	    // Rabbit Stew, Jump Boost II for 10 Seconds
	        if (stack.isItemEqual(stack) && stack.getItem()==Items.RABBIT_STEW && stack.getUseAction() == UseAction.EAT) {
	        	player.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST,(200), 1));
	        }
		   
	    // Pumpkin Pie, Resistance for 30 seconds
	        if (stack.isItemEqual(stack) && stack.getItem()==Items.PUMPKIN_PIE && stack.getUseAction() == UseAction.EAT) {
	        	player.addPotionEffect(new EffectInstance(Effects.RESISTANCE,(400), 0));
	        }
	        
	    // Pufferfish, Water Breathing for 1 Minute
	        if (stack.isItemEqual(stack) && stack.getItem()==Items.PUFFERFISH && stack.getUseAction() == UseAction.EAT) {
	        	player.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING,(2400), 0));
	        }
	        
	    // Dried Kelp, Clears Poison & Nausea & Blindness
	        if (stack.isItemEqual(stack) && stack.getItem()==Items.DRIED_KELP && stack.getUseAction() == UseAction.EAT) {
	        	player.removePotionEffect(Effects.POISON);
	        	player.removePotionEffect(Effects.NAUSEA);
	        	player.removePotionEffect(Effects.BLINDNESS);
	        } 
	        

	    }
       
	      
	
}
