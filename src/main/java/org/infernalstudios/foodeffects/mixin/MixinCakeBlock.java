package org.infernalstudios.foodeffects.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.CakeBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectInstance;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import org.infernalstudios.foodeffects.config.FoodEffectsConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CakeBlock.class)
public class MixinCakeBlock {

    @Inject(method = "eatSlice", at = @At(target = "Lnet/minecraft/entity/player/PlayerEntity;addStat(Lnet/minecraft/util/ResourceLocation;)V", value = "INVOKE"))
    private void FE_giveCakeEffect(IWorld world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfoReturnable<ActionResultType> cir) {
        IForgeRegistry<Effect> EFFECTS = ForgeRegistries.POTIONS;
        Effect cakeEffect = EFFECTS.getValue(new ResourceLocation(FoodEffectsConfig.COMMON.CAKE_EFFECT.get()));

        if (cakeEffect != null) {
            player.addPotionEffect(new EffectInstance(cakeEffect, (int) (FoodEffectsConfig.COMMON.CAKE_EFFECT_DURATION.get() * 20), FoodEffectsConfig.COMMON.CAKE_EFFECT_AMPLIFIER.get()));
        }
    }
}
