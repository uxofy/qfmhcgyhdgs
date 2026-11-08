package ru.animatium;

import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.option.KeyBinding;

import java.util.Locale;

public class AnimatiumClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        // Бинды не трогаем — они продолжают работать.
        // Скрытие происходит в миксине KeyBindingEntryMixin (не даём строке отрисоваться).
    }

    /**
     * Сюда добавлять id клавиш, которые нужно скрыть из Настройки -> Управление.
     */
    public static boolean shouldHide(KeyBinding binding) {
        if (binding == null) {
            return false;
        }
        String t = binding.getTranslationKey().toLowerCase(Locale.ROOT);
        String plain = t.replace('\u00e9', 'e'); // macécore -> macecore
        return plain.contains("macecore")
                || t.equals("key.shieldbreaker.open_gui");
    }
}
