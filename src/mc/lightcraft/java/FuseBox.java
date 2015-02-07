package mc.lightcraft.java;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.java.JavaPlugin;

public class FuseBox extends JavaPlugin {
	private static FuseBox instance;
	
	/**
	 * Called when FuseBox initiates. Creates the active instance and registers any needed information
	 */
	public void onEnable() {
		instance = this;
		super.onEnable();
	}

	/**
	 * This function is called when FuseBox is disabled. Clears the active instance and unregisters all event for this plugin
	 */
	public void onDisable() {
		HandlerList.unregisterAll(instance);
		instance = null;
		super.onDisable();
	}
	
	/**
	 * This will get the active instance of FuseBox
	 * @return active FuseeBox instance
	 */
	public static FuseBox getFuseBox() {
		return instance;
	}
}
