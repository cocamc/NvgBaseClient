package net.coca.baseclient.api.gui;

import net.coca.baseclient.api.gui.comp.ModButton;
import net.coca.baseclient.helper.NVGUtil;
import net.coca.baseclient.mod.Mods;
import net.coca.baseclient.mod.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.util.ArrayList;

import static org.lwjgl.nanovg.NanoVG.nvgBeginFrame;
import static org.lwjgl.nanovg.NanoVG.nvgEndFrame;

public class ClickGui extends GuiScreen {
    ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
    public static Minecraft minecraft = Minecraft.getMinecraft();
    public float screenWidth = scaledResolution.getScaledWidth(), screenHeight = scaledResolution.getScaledHeight();
    public float width = 350, height = 350;
    public float windowX = screenWidth / 2f - width / 2f;
    public float windowY = screenHeight / 2f - height / 2f;
    public final ArrayList<ModButton> modButtons = new ArrayList<>();

    @Override
    public void initGui() {
        modButtons.clear();

        float modY = 90;
        float modX = windowX + 30;
        for(Module module : Mods.INSTANCE.getModules()) {
            if(modX >=windowX + width){
                modX = windowX + 30;
                modY += 40;
            }
            modButtons.add(new ModButton(module, modX, modY));
            modX += 110;
        }
        super.initGui();
    }


    @Override
    public void drawScreen(int mouseX, int mouseY, float delta) {
        super.drawScreen(mouseX, mouseY, delta);

        NVGUtil.beginFrame(scaledResolution);
        NVGUtil.drawDropShadow(windowX, windowY, width,height,3, 3,6, new Color(25,25,25,225).getRGB(),255);
        NVGUtil.drawRoundedRect(windowX, windowY, width,height,3, new Color(25,25,25,225));
        float modX = windowX + 30;
        float modY = 90;
        NVGUtil.endFrame();
        for(ModButton modButton : modButtons) {
            if(modX >=windowX + width){
                modX = windowX + 30;
                modY += 40;
            }
            modButton.setY(modY);
            modButton.setX(modX);
            modButton.draw();
            modX += 110;
        }
    }

    @Override
    public void mouseClicked(int mouseX, int mouseY, int button) {
        for(ModButton modButton : modButtons) {
            if(Mods.INSTANCE.getModules().contains(modButton.getModule())) {
                modButton.mouseClicked(mouseX, mouseY, button);
            }
        }
        super.mouseClicked(mouseX, mouseY, button);
    }
}
