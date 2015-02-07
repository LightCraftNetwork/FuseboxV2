package mc.lightcraft.java;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class FuseBox extends JavaPlugin {
	private static FuseBox instance;
	
	
	public void onEnable() {
		instance = this;
		super.onEnable();
	}

	public void onDisable() {
		HandlerList.unregisterAll(instance);
		instance = null;
		super.onDisable();
	}
	
	public static FuseBox getFuseBox() {
		return instance;
	}
}
