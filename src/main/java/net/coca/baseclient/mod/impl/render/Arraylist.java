package net.coca.baseclient.mod.impl.render;


import net.coca.baseclient.mod.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.util.List;

import static net.coca.baseclient.helper.NVGUtil.*;

@ModInfo(name = "Arraylist", description = "Shows a all enabled Modules", category = Category.RENDER)
public class Arraylist extends RenderModule {

    public Arraylist() {
        super(0, 0, 1, 1);
    }
    @Override
    public void draw() {
        ScaledResolution scaledResolution = new ScaledResolution(Minecraft.getMinecraft());
        float screenWidth = scaledResolution.getScaledWidth();
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        List<Module> enabledModules = Mods.INSTANCE.getEnabledModules();

        enabledModules.sort((mod1, mod2) ->
                fontRenderer.getStringWidth(mod2.getName()) - fontRenderer.getStringWidth(mod1.getName()));

        float moduleY = 4;
        for (Module module : enabledModules) {
            String moduleName = module.getName();
            float moduleX = screenWidth - fontRenderer.getStringWidth(moduleName) - 2;
            fontRenderer.drawString(moduleName, (int)moduleX,(int) moduleY, Color.CYAN.getRGB());
            moduleY += 9;
        }
    }
}
