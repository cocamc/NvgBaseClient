package net.coca.baseclient.api.gui.comp;

import lombok.Getter;
import lombok.Setter;
import net.coca.baseclient.api.gui.ClickGui;
import net.coca.baseclient.helper.NVGUtil;
import net.coca.baseclient.mod.Module;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.Gui;

import java.awt.*;

public class ModButton {

    @Getter
    private final Module module;
    @Setter
    private float y;
    @Setter
    private float x;

    public ClickGui gui = new ClickGui();


    public ModButton(Module module, float x, float y) {
        this.module = module;
        this.y = y;
        this.x = x;
    }

    public void draw(){
        FontRenderer fontRenderer = Minecraft.getMinecraft().fontRendererObj;
        fontRenderer.drawString(module.getName(), (int)x,(int)y,getColor(module));
    }

    public int getColor(Module module){
        if(module.isEnabled()){
            return Color.GREEN.getRGB();
        }else{
            return -1;
        }
    }

    public void mouseClicked(double mouseX, double mouseY, int button) {
        int textWidth = Minecraft.getMinecraft().fontRendererObj.getStringWidth(module.getName()) + 2;
        int textHeight = Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT + 2;

        if (mouseX >= x && mouseX <= x + textWidth && mouseY >= y && mouseY <= y + textHeight && button == 0) {
            module.toggle();
        }
    }

    private boolean hovered(float x, float y, float x2, float y2, double mouseX, double mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }

}