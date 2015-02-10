package mc.lightcraft.java.common.rank.tree;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.OfflinePlayer;

public class RankTree {

	private static RankTree tree;
	private HashMap<UUID, ServerRank> ram = new HashMap<UUID, ServerRank>();

	/**
	 * RankTree constructor is private; we only want one instance.
	 */
	private RankTree() {
	}

	/**
	 * Get the instance of the rank tree.
	 */
	public static RankTree getTree() {
		if (tree == null) {
			tree = new RankTree();
		}
		return tree;
	}

	/**
	 * Simple method to get the tree list in order. Use this instead of .values,
	 * because we can change it later.
	 */
	public ServerRank[] getTreeList() {
		return ServerRank.values();
	}

	/**
	 * Check if a player has permission for a rank in the tree.
	 * 
	 * @param player
	 *            The rank the player/checking object is
	 * @param r
	 *            The permission's rank/checking against
	 */
	public boolean hasRank(ServerRank player, ServerRank r) {
		if (player.toString().equalsIgnoreCase(r.toString()))
			return true;
		for (ServerRank r5 : ServerRank.values()) {
			if (r5.toString().equalsIgnoreCase(player.toString()))
				return false;
			if (r5.toString().equalsIgnoreCase(r.toString()))
				return true;
		}
		return false;
	}

	/**
	 * Set a player's rank in RAM, NOT on the database.
	 * 
	 * @param p
	 *            The player's rank you would like to load into RAM.
	 * @param r
	 *            The rank you would like to load into RAM.
	 */
	public void setRank(OfflinePlayer p, ServerRank r) {
		if (ram.containsKey(p.getUniqueId())) {
			ram.remove(p.getUniqueId());
		}
		ram.put(p.getUniqueId(), r);
	}

	/**
	 * Get a player's rank in RAM, NOT on the database.
	 * 
	 * @param p
	 *            The player you would like to read from RAM.
	 */
	public ServerRank getRank(OfflinePlayer p) {
		if (ram.containsKey(p.getUniqueId())) {
			return ram.get(p.getUniqueId());
		}
		return ServerRank.Default;
	}

	/**
	 * Unload a player's rank in RAM, NOT on the database.
	 * 
	 * @param p
	 *            The player you would like to unload from RAM.
	 */
	public void unloadRank(OfflinePlayer p) {
		if (ram.containsKey(p.getUniqueId()))
			ram.remove(p.getUniqueId());
	}

	/**
	 * Get the colour of a rank
	 * 
	 * @param r
	 *            The rank you want the colour of
	 */
	public String getColor(ServerRank r) {
		try {
			switch (r) {
			case Default:
				return "§7";
			case VIP:
				return "§b";
			case Blaze:
				return "§6";
			case Aurora:
				return "§e";
			case Helper:
				return "§d";
			case Mod:
				return "§a";
			case Admin:
				return "§c";
			case Developer:
				return "§c";
			case BuildTeam:
				return "§c";
			case Owner:
				return "§3";
			case CoOwner:
				return "§3";
			default:
				return "§7";
			}
		} catch (Exception ex) {
			return "§7";
		}
	}

}
