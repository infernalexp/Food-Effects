package org.infernalstudios.foodeffects;

import java.util.HashMap;
import java.util.function.Supplier;

import com.electronwill.nightconfig.core.CommentedConfig;
import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.toml.TomlFormat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.ForgeRegistries;

public record EffectData(Supplier<Item> item, Supplier<MobEffect> effect, int duration, int amplifier) {
    public Item getItem() {
        return item.get();
    }

    public MobEffect getEffect() {
        return effect.get();
    }
    
    public int getDuration() {
        return duration;
    }
    
    public int getAmplifier() {
        return amplifier;
    }

    public Config toConfig() {
        CommentedConfig config = CommentedConfig.of(() -> new HashMap<>(4), TomlFormat.instance());

        config.set("item", this.getItem().getRegistryName().toString());
        config.set("effect", this.getEffect().getRegistryName().toString());
        config.set("duration", (double) this.getDuration() / 20);
        config.set("amplifier", this.getAmplifier());

        config.setComment("item", " Determines what item this effect is applied to.");
        config.setComment("effect", " Determines what effect consuming the item will give in addition to any existing effects.");
        config.setComment("duration", " Determines how long the effect lasts. If set to 0, the effect will be removed.\n Range: [0, 1000000]");
        config.setComment("amplifier", " Determines how strong the effect is. Amplifiers start at 0.\n Range: [0, 255]");
        
        return config;
    }

    public static EffectData fromConfig(Config config) {
        return new EffectData(
            () -> ForgeRegistries.ITEMS.getValue(ResourceLocation.tryParse(config.<String>get("item"))),
            () -> ForgeRegistries.MOB_EFFECTS.getValue(ResourceLocation.tryParse(config.<String>get("effect"))),
            (int) (config.<Number>get("duration").doubleValue() * 20),
            config.getInt("amplifier")
        );
    }
}
