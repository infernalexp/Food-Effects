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

public class EffectData {
    private final ResourceLocation item;
    private final ResourceLocation effect;
    private final int duration;
    private final int amplifier;
    private Item itemValue = null;
    private MobEffect effectValue = null;

    public EffectData(ResourceLocation item, ResourceLocation effect, int duration, int amplifier) {
        this.item = item;
        this.effect = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public EffectData(Item item, MobEffect effect, int duration, int amplifier) {
        this.item = item.getRegistryName();
        this.effect = effect.getRegistryName();
        this.itemValue = item;
        this.effectValue = effect;
        this.duration = duration;
        this.amplifier = amplifier;
    }

    public ResourceLocation getItemLocation() {
        return item;
    }

    public Item getItem() {
        if (itemValue == null) {
            itemValue = ForgeRegistries.ITEMS.getValue(this.getItemLocation());
        }
        return itemValue;
    }

    public ResourceLocation getEffectLocation() {
        return effect;
    }

    public MobEffect getEffect() {
        if (effectValue == null) {
            effectValue = ForgeRegistries.MOB_EFFECTS.getValue(this.getEffectLocation());
        }
        return effectValue;
    }
    
    public int getDuration() {
        return duration;
    }
    
    public int getAmplifier() {
        return amplifier;
    }

    public Config toConfig() {
        CommentedConfig config = CommentedConfig.of(() -> new HashMap<>(4), TomlFormat.instance());

        config.set("item", this.getItemLocation().toString());
        config.set("effect", this.getEffectLocation().toString());
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
            ResourceLocation.tryParse(config.<String>get("item")),
            ResourceLocation.tryParse(config.<String>get("effect")),
            (int) (config.<Number>get("duration").doubleValue() * 20),
            config.getInt("amplifier")
        );
    }
}
