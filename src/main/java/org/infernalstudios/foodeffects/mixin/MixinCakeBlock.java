package org.infernalstudios.foodeffects.mixin;

import static net.minecraftforge.registries.ForgeRegistries.MOB_EFFECTS;

import org.infernalstudios.foodeffects.config.FoodEffectsConfig.Effects;
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
        MobEffect cakeEffect = MOB_EFFECTS.getValue(new ResourceLocation(Effects.cake_effect));

        if (cakeEffect != null) {
            player.addEffect(new MobEffectInstance(cakeEffect, (int) (Effects.cake_effect_duration * 20), Effects.cake_effect_amplifier));
        }
    }
}
