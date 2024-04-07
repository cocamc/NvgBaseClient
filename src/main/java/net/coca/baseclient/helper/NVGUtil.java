package net.coca.baseclient.helper;

import net.minecraft.client.gui.ScaledResolution;
import net.minecraft.client.renderer.GlStateManager;
import org.lwjgl.nanovg.NVGColor;
import org.lwjgl.nanovg.NVGPaint;
import org.lwjgl.nanovg.NanoVG;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Objects;

import static org.lwjgl.nanovg.NanoVG.*;
import static org.lwjgl.nanovg.NanoVGGL3.*;
import static org.lwjgl.opengl.GL11.GL_ALPHA_TEST;
import static org.lwjgl.opengl.GL11.glDisable;

public class NVGUtil {
    public static long ctx;

    public static void register() {
        ctx = nvgCreate(NVG_ANTIALIAS | NVG_STENCIL_STROKES);
    }

    public static void drawRect(float x, float y, float w, float h, Color color) {
        nvgBeginPath(ctx);
        nvgRect(ctx, x, y, w, h);
        NVGColor nvgColor = colorToNVGColor(color);
        nvgFillColor(ctx, nvgColor);
        nvgFill(ctx);
        nvgClosePath(ctx);
        nvgColor.free();
    }
    public static void drawRoundedRect(float x, float y, float w, float h, float radius, Color color) {
        nvgBeginPath(ctx);
        nvgRoundedRect(ctx, x, y, w, h,radius);
        NVGColor nvgColor = colorToNVGColor(color);
        nvgFillColor(ctx, nvgColor);
        nvgFill(ctx);
        nvgClosePath(ctx);
        nvgColor.free();
    }

    public static void drawDropShadow(float x, float y, float width, float height, float radius, float blur, float spread, int shadowColor, int alpha) {
        try (NVGPaint paint = NVGPaint.calloc()) {
            Color col = new Color(shadowColor);
            glDisable(GL_ALPHA_TEST);
            nvgBoxGradient(ctx, x - spread, y - spread, width + spread * 2, height + spread * 2, radius, blur, colorToNVGColor(new Color(col.getRed(), col.getGreen(), col.getBlue(), alpha)), colorToNVGColor(new Color(0x00000000, true)), paint);
            nvgBeginPath(ctx);
            nvgRect(ctx, x - 50, y - 50, width + 100, height + 100);
            nvgRoundedRectVarying(ctx, x, y, width, height, radius, radius, radius, radius);
            nvgPathWinding(ctx, NVG_HOLE);
            nvgFillPaint(ctx, paint);
            nvgFill(ctx);
        }
    }
    public static NVGColor colorToNVGColor(Color color) {
        NVGColor nvgColor = NVGColor.calloc();
        nvgColor.r(color.getRed() / 255.0f);
        nvgColor.g(color.getGreen() / 255.0f);
        nvgColor.b(color.getBlue() / 255.0f);
        nvgColor.a(color.getAlpha() / 255.0f);
        return nvgColor;
    }

    public static void beginFrame(ScaledResolution sr) {
        GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
        GL11.glEnable(GL11.GL_STENCIL_TEST);
        GL11.glEnable(GL11.GL_BLEND);
        GlStateManager.disableAlpha();
        nvgBeginFrame(ctx, sr.getScaledWidth(), sr.getScaledHeight(), (float) sr.getScaledWidth() /sr.getScaledHeight());
    }
    public static void endFrame() {
        NanoVG.nvgEndFrame(ctx);
        GlStateManager.enableAlpha();
        GL11.glDisable(GL11.GL_STENCIL_TEST);
        GL11.glDisable(GL11.GL_BLEND);
        GlStateManager.popAttrib();
    }
}
