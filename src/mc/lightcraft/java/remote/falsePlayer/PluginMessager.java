package mc.lightcraft.java.remote.falsePlayer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import mc.lightcraft.java.FuseBox;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageRecipient;

public class PluginMessager implements PluginMessageRecipient {
	private static ArrayList<QueuedMessage> messages;
	private static PluginMessager instance;

	private PluginMessager() {
		messages = new ArrayList<QueuedMessage>();
	}

	public static PluginMessager getInstance() {
		if (instance == null)
			instance = new PluginMessager();
		return instance;
	}

	@Override
	/**
	 * Completely unnecessary method, please make no attempt to use this as it only exists to please the inherited interface and will always return null
	 */
	public Set<String> getListeningPluginChannels() {
		return null;
	}

	public boolean pushQueue() {
		Collection<? extends Player> players = FuseBox.getFuseBox().getServer()
				.getOnlinePlayers();
		if (players.size() == 0)
			return false;

		for (QueuedMessage message : messages) {
			sendMessageRaw(message);
		}
		messages.clear();
		return true;
	}

	/**
	 * Just try to send the plugin message, who cares if it works
	 * @param message The message to be sent
	 */
	private void sendMessageRaw(QueuedMessage message) {
		Collection<? extends Player> players = FuseBox.getFuseBox().getServer()
				.getOnlinePlayers();
		Player player = players.toArray(new Player[players.size()])[0];
		if (player == null)
			return; // Make sure we arent't breaking anything
		player.sendPluginMessage(message.getPlugin(), message.getKey(),
				message.getData());
		message.wipeData();
	}

	@Override
	/**
	 * @param plugin The plugin sending the message; Putting 'null' here will automatically use the Fusebox instance
	 * @param key the key you would like to use for sending the data
	 * @param data the data you wish to send
	 */
	public void sendPluginMessage(Plugin plugin, String key, byte[] data) {
		if (plugin == null)
			plugin = FuseBox.getFuseBox();
		Collection<? extends Player> players = FuseBox.getFuseBox().getServer()
				.getOnlinePlayers();
		if (players.size() == 0) {
			QueuedMessage qm = new QueuedMessage(plugin, key, data);
			messages.add(qm);
		}
	}

	protected class QueuedMessage {
		private Plugin plugin;
		private String key;
		private byte[] data;

		protected QueuedMessage(Plugin plugin, String key, byte[] data) {
			this.plugin = plugin;
			this.key = key;
			this.data = data;
		}

		protected Plugin getPlugin() {
			return plugin;
		}

		protected String getKey() {
			return key;
		}

		protected byte[] getData() {
			return data;
		}

		// Run to prevent memory leaks
		public void wipeData() {
			plugin = null;
			key = null;
			data = null;
		}
	}
}
