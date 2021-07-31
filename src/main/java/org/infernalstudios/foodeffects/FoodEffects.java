/*
 * Copyright 2021 Infernal Studios
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

import org.infernalstudios.foodeffects.config.FoodEffectsConfig;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ExtensionPoint;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.network.FMLNetworkConstants;
import org.apache.commons.lang3.tuple.Pair;

@Mod(FoodEffects.MOD_ID)
public class FoodEffects {
    public static final String MOD_ID = "foodeffects";

    public FoodEffects() {
        final ModLoadingContext modLoadingContext = ModLoadingContext.get();

        //Make sure the mod being absent on the other network side does not cause the client to display the server as incompatible
        modLoadingContext.registerExtensionPoint(ExtensionPoint.DISPLAYTEST, () -> Pair.of(() -> FMLNetworkConstants.IGNORESERVERONLY, (a, b) -> true));

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new FoodEffectsEvents());

        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, FoodEffectsConfig.COMMON_SPEC);
    }
}
