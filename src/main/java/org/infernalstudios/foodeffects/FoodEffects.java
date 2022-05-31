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

import java.io.IOException;
import java.util.ArrayList;

import com.electronwill.nightconfig.core.io.ParsingException;

import org.infernalstudios.config.Config;
import org.infernalstudios.config.Config.ReloadStage;
import org.infernalstudios.foodeffects.config.FoodEffectsConfig;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.IExtensionPoint.DisplayTest;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.network.NetworkConstants;

@Mod(FoodEffects.MOD_ID)
public class FoodEffects {
    public static final String MOD_ID = "foodeffects";

    public FoodEffects() {
        final ModLoadingContext context = ModLoadingContext.get();

        // Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        context.registerExtensionPoint(DisplayTest.class, () -> new DisplayTest(() -> NetworkConstants.IGNORESERVERONLY, (a, b) -> true));

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new FoodEffectsEvents());

        Config config;
        try {
            config = Config
                .builder(FMLPaths.CONFIGDIR.get().resolve("foodeffects-common.toml"))
                .loadClass(FoodEffectsConfig.class)
                .build();
        } catch (IllegalStateException | IllegalArgumentException | IOException | ParsingException e) {
            throw new RuntimeException(
                "Failed to load Food Effects config" +
                (e instanceof ParsingException ? ", try deleting your config file" : ""), e);
        }

        config.onReload(stage -> {
            if (stage.equals(ReloadStage.POST)) {
                FoodEffectsEvents.EFFECT_MAP.clear();
                for (EffectData effect : FoodEffectsConfig.effects) {
                    if (!FoodEffectsEvents.EFFECT_MAP.containsKey(effect.getItem())) {
                        FoodEffectsEvents.EFFECT_MAP.put(effect.getItem(), new ArrayList<>());
                    }
                    FoodEffectsEvents.EFFECT_MAP.get(effect.getItem()).add(effect);
                }
            }
        });
    }
}
