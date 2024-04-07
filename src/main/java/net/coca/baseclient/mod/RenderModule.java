package net.coca.baseclient.mod;

import lombok.Getter;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;

@Getter
public abstract class RenderModule extends Module {

    private int x, y, width, height;


    public RenderModule() {
        ModInfo modInfo = this.getClass().getAnnotation(ModInfo.class);
        if (modInfo != null) {
            this.setName(modInfo.name());
            this.setDescription(modInfo.description());
            this.setCategory(modInfo.category());
        }
    }

    public RenderModule(int x, int y, int width, int height) {
        this();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public abstract void draw();

    public void setPositionAndSize(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}

