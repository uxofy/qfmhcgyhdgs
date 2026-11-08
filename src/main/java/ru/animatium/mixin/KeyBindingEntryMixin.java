package ru.animatium.mixin;

import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.screen.option.ControlsListWidget;
import net.minecraft.client.option.KeyBinding;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import ru.animatium.AnimatiumClient;

// НЕ удаляем бинды из реестра (KeyBinding.ALL) — иначе они перестанут
// нажиматься, потому что ваниль помечает нажатия перебором этого списка.
// Вместо этого просто не даём строке отрисоваться. Бинд остаётся рабочим.
@Mixin(ControlsListWidget.KeyBindingEntry.class)
public abstract class KeyBindingEntryMixin {

    @Shadow
    @Final
    private KeyBinding binding;

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void animatium$hideEntry(DrawContext context, int index, int y, int x,
            int width, int height, int mouseX, int mouseY, boolean hovered,
            float tickDelta, CallbackInfo ci) {
        if (AnimatiumClient.shouldHide(this.binding)) {
            ci.cancel();
        }
    }
}
