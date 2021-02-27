package com.brunocollingridge.foodeffects;

import com.brunocollingridge.foodeffects.core.FoodEffectsConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(FoodEffects.MOD_ID)
public class FoodEffects
{
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "novus_foodeffects";

    public FoodEffects() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FoodEffectsConfig.COMMON_SPEC);
    }

    private void setup(final FMLCommonSetupEvent event)
    {
    	
    }
    
    
}
