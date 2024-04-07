package net.coca.baseclient;

import net.coca.baseclient.api.gui.ClickGui;
import net.coca.baseclient.helper.NVGUtil;
import net.coca.baseclient.mod.Mods;
import net.coca.baseclient.mod.Module;
import net.fabricmc.api.ModInitializer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import org.lwjgl.input.Keyboard;

public class Client implements ModInitializer {
	@Override
	public void onInitialize() {
		Mods.INSTANCE.init();
	}

	public void postInit(){
		NVGUtil.register();
	}


	// Change the keybinding logic to be configured with a keybinding setting in a clickgui if you want to, this is for arraylist debugging
	public void onKeyPress(final int keycode, final boolean state) {
		if (keycode == Keyboard.KEY_H && state) {
			Module mod = Mods.INSTANCE.getModuleByName("Arraylist");
			mod.toggle();
		}
		if (keycode == Keyboard.KEY_F && state) {
			Module mod = Mods.INSTANCE.getModuleByName("place2");
			mod.toggle();
		}
		if (keycode == Keyboard.KEY_G && state) {
			Module mod = Mods.INSTANCE.getModuleByName("placeholder1");
			mod.toggle();
		}

		if (keycode == Keyboard.KEY_RSHIFT && state) {
			Minecraft.getMinecraft().displayGuiScreen(new ClickGui());
		}
	}
}
