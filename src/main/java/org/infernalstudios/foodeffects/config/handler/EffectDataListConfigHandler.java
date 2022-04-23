package org.infernalstudios.foodeffects.config.handler;

import java.lang.reflect.Field;
import java.util.List;

import com.electronwill.nightconfig.core.Config;
import com.google.common.collect.ImmutableList;

import org.infernalstudios.config.element.ConfigElement;
import org.infernalstudios.config.element.IConfigElement;
import org.infernalstudios.config.element.handler.IConfigElementHandler;
import org.infernalstudios.config.util.annotation.Nullable;
import org.infernalstudios.foodeffects.EffectData;

public class EffectDataListConfigHandler implements IConfigElementHandler<List<EffectData>, List<Config>> {
    public static final EffectDataListConfigHandler INSTANCE = new EffectDataListConfigHandler();

    private EffectDataListConfigHandler() {}

    @Override
    public IConfigElement<List<EffectData>> create(Field field) {
        return new ConfigElement<>(field, this);
    }

    @Override
    public IConfigElement<List<EffectData>> update(IConfigElement<List<EffectData>> element, @Nullable List<EffectData> obj) {
        if (obj != null) {
            element.set(ImmutableList.copyOf(obj));
        }

        return element;
    }

    @Override
    public List<Config> serialize(IConfigElement<List<EffectData>> element) {
        List<EffectData> value = element.getFromField();
        if (value == null) {
            value = element.getDefault();
        }
        return value.stream().map(EffectData::toConfig).collect(ImmutableList.toImmutableList());
    }

    @Override
    @Nullable
    public List<EffectData> deserialize(List<Config> obj) {
        if (obj != null) {
            return obj.stream().map(EffectData::fromConfig).collect(ImmutableList.toImmutableList());
        }
        return null;
    }

    @Override
    public boolean canHandle(Class<?> clazz) {
        return clazz == List.class || List.class.isAssignableFrom(clazz);
    }
}
