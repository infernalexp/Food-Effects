package org.infernalstudios.foodeffects.mixin;

import static net.minecraftforge.registries.ForgeRegistries.MOB_EFFECTS;
import static org.infernalstudios.foodeffects.config.FoodEffectsConfig.COMMON;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.CakeBlock;
import net.minecraft.world.level.block.state.BlockState;

@Mixin(CakeBlock.class)
public class MixinCakeBlock {

    @Inject(method = "eat", at = @At(target = "Lnet/minecraft/world/entity/player/Player;awardStat(Lnet/minecraft/resources/ResourceLocation;)V", value = "INVOKE"))
    private static void FE_giveCakeEffect(LevelAccessor world, BlockPos pos, BlockState state, Player player, CallbackInfoReturnable<InteractionResult> cir) {
        MobEffect cakeEffect = MOB_EFFECTS.getValue(new ResourceLocation(COMMON.CAKE_EFFECT.get()));

        if (cakeEffect != null) {
            player.addEffect(new MobEffectInstance(cakeEffect, (int) (COMMON.CAKE_EFFECT_DURATION.get() * 20), COMMON.CAKE_EFFECT_AMPLIFIER.get()));
        }
    }
}
