package net.coca.baseclient.mod.impl.render;


import net.coca.baseclient.mod.*;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.ScaledResolution;

import java.awt.*;
import java.util.List;

@ModInfo(name = "placeholder1", description = "Shows a all enabled Modules", category = Category.RENDER)
public class placeholder1 extends RenderModule {

    public placeholder1() {
        super(0, 0, 1, 1);
    }
    @Override
    public void draw() {

    }
}
