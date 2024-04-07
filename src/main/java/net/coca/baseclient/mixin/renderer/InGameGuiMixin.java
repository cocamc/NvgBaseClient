package net.coca.baseclient.mixin.renderer;

import net.coca.baseclient.api.event.impl.RenderEvent;
import net.coca.baseclient.api.gui.ClickGui;
import net.coca.baseclient.mod.Mods;
import net.coca.baseclient.mod.RenderModule;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiIngame;
import net.minecraft.client.gui.ScaledResolution;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import static net.coca.baseclient.helper.NVGUtil.*;

@Mixin(GuiIngame.class)
public class InGameGuiMixin {
    @Inject(method = "renderGameOverlay", at = @At("RETURN"))
    private void renderGameOverlay(float partialTicks, CallbackInfo callbackInfo) {
        if(Minecraft.getMinecraft().currentScreen instanceof ClickGui){
            return;
        }
        beginFrame(new ScaledResolution(Minecraft.getMinecraft()));
        new RenderEvent().call();
        for(RenderModule renderModule : Mods.INSTANCE.getEnabledRenderModules()) {
            if(renderModule.isEnabled()) renderModule.draw();
        }
        endFrame();
    }

}
