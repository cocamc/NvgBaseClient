package net.coca.baseclient.mod.impl.render;


import net.coca.baseclient.mod.Category;
import net.coca.baseclient.mod.ModInfo;
import net.coca.baseclient.mod.RenderModule;

@ModInfo(name = "placeholder3", description = "Shows a all enabled Modules", category = Category.RENDER)
public class place3 extends RenderModule {

    public place3() {
        super(0, 0, 1, 1);
    }

    @Override
    public void draw() {

    }
}
