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

import com.electronwill.nightconfig.core.io.ParsingException;

import org.infernalstudios.config.Config;
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

        try {
            Config
                .builder(FMLPaths.CONFIGDIR.get().resolve("foodeffects-common.toml"))
                .loadClass(FoodEffectsConfig.General.class)
                .loadClass(FoodEffectsConfig.Effects.class)
                .build();
        } catch (IllegalStateException | IllegalArgumentException | IOException | ParsingException e) {
            throw new RuntimeException("Failed to load Food Effects config", e);
        }
    }
}
