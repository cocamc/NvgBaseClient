package net.coca.baseclient.mixin.nanovg;

import org.lwjgl.system.FunctionProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import patch.Lwjgl2FunctionProvider;

@Mixin(targets = "org.lwjgl.nanovg.NanoVGGLConfig")
public class NVGPatcher {
	/**
	 * @author coca
	 * @reason patching
	 */
	@Overwrite(remap = false)
	public static FunctionProvider getFunctionProvider(String className) {
		return new Lwjgl2FunctionProvider();
	}
}
