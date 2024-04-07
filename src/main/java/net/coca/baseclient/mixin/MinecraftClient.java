package net.coca.baseclient.mixin;

import net.coca.baseclient.Client;
import net.coca.baseclient.api.event.impl.TickEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiControls;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
@Mixin(Minecraft.class)
public class MinecraftClient {
    @Inject(method = "runTick", at = @At("TAIL"))
    public void onUpdate(CallbackInfo ci) {
        new TickEvent().call();
    }

    @Inject(method = "startGame", at = @At("TAIL"))
    public void mixin$initializeGame(CallbackInfo ci) throws IOException {
        new Client().postInit();
    }
    @Inject(method = { "dispatchKeypresses" }, at = { @At("HEAD") })
    private void dispatchKeypresses(final CallbackInfo ci) {
        final int i = (Keyboard.getEventKey() == 0) ? Keyboard.getEventCharacter() : Keyboard.getEventKey();
        boolean state = Keyboard.getEventKeyState();
        new Client().onKeyPress(i, state);
    }

}
