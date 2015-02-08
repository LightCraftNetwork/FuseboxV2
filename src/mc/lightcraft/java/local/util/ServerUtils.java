package mc.lightcraft.java.local.util;

import org.bukkit.Bukkit;
import org.bukkit.Server;
import org.bukkit.entity.Player;

public class ServerUtils {

	private ServerUtils() {
	}

	/**
	 * Get all the players currently on the server in an array
	 * 
	 * @return a Player[] containing all the players currently connected to the
	 *         server
	 */
	public static Player[] getPlayers() {
		return getServer().getOnlinePlayers().toArray(
				new Player[getServer().getOnlinePlayers().size()]);
	}

	/**
	 * Get the current instance of the server. Equivalent to just calling
	 * "Bukkit.#" but usable for NMS
	 * 
	 * @return the current instance of the server
	 */
	public static Server getServer() {
		return Bukkit.getServer();
	}
}
