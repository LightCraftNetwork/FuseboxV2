package mc.lightcraft.java.remote.BungeeCord;

import mc.lightcraft.java.remote.falsePlayer.PluginMessager;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

public class BungeeCordAPI {
	
	private BungeeCordAPI() {} // Private constructor to prevent instantiation
	
	/**
	 * 
	 * @param plugin the plugin requesting the server list
	 * @param servers the servers you want the player list from. Leave blank for all servers
	 */
	public static void requestPlayerList(Plugin plugin, String... servers) {
		if (servers.length == 0)
			servers = new String[] { "ALL" };
		for (String srv : servers) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("PlayerList");
			out.writeUTF(srv);
			PluginMessager.getInstance().sendPluginMessage(plugin,
					"BungeeCord", out.toByteArray());
		}
	}
	
	/**
	 * 
	 * @param plugin the plugin requesting the player count
	 * @param servers the servers you want the player count from. Leave blank for all servers
	 */
	public static void requestPlayerCount(Plugin plugin, String... servers) {
		if (servers.length == 0)
			servers = new String[] { "ALL" };
		for (String srv : servers) {
			ByteArrayDataOutput out = ByteStreams.newDataOutput();
			out.writeUTF("PlayerCount");
			out.writeUTF(srv);
			PluginMessager.getInstance().sendPluginMessage(plugin,
					"BungeeCord", out.toByteArray());
		}
	}
	
	/**
	 * Requests the names of all the servers currently connected to BungeeCord as identified in BungeeCord's config.yml
	 * @param plugin The plugin requesting the connected BungeeCord servers
	 */
	public static void getBungeeServers(Plugin plugin) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("GetServers");
		PluginMessager.getInstance().sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}
	
	/**
	 * Sends a specified player to a specified server
	 * @param player The name of the player you wish to send
	 * @param server The name of the server you wish to send them to
	 */
	public static void sendPlayerToServer(String player, String server) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("ConnectOther");
		out.writeUTF(player);
		out.writeUTF(server);
		PluginMessager.getInstance().sendPluginMessage(null, "BungeeCord", out.toByteArray());
	}
	
	/**
	 * Sends a specified player to a specified server
	 * @param player The player you wish to send
	 * @param server The name of the server you wish to send them to
	 */
	public static void sendPlayerToServer(OfflinePlayer player, String server) {
		sendPlayerToServer(player.getName(), server);
	}
	
	/**
	 * 
	 * @param plugin The plugin requesting the player's IP
	 * @param player the player you want the IP of. This must be a play object as you cannot use a string-based IP request
	 */
	public static void requestPlayerIP(Plugin plugin, Player player) {
		ByteArrayDataOutput out = ByteStreams.newDataOutput();
		out.writeUTF("IP");
		player.sendPluginMessage(plugin, "BungeeCord", out.toByteArray());
	}
}
