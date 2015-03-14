package mc.lightcraft.java.remote.util;

import org.bukkit.OfflinePlayer;

public class UtilQuery {

	private UtilQuery() {
	}

	/**
	 * Prepared query for getting a player's coins
	 * 
	 * @param p
	 *            The player you want to act on
	 * @return Formatted query
	 */
	public static String getCoinsQuery(OfflinePlayer p) {
		return "retrieve players/coins " + p.getUniqueId().toString();
	}

	/**
	 * Prepared query for setting a player's coins
	 * 
	 * @param p
	 *            The player you want to act on
	 * @param amount
	 *            The amount you want to set the value to.
	 * @return Formatted query
	 */
	public static String setCoinsQuery(OfflinePlayer p, long amount) {
		return "insert players/coins " + p.getUniqueId().toString() + " "
				+ amount;
	}

	/**
	 * Prepared query for getting a player's tokens
	 * 
	 * @param p
	 *            The player you want to act on
	 * @return Formatted query
	 */
	public static String getTokensQuery(OfflinePlayer p) {
		return "retrieve players/tokens " + p.getUniqueId().toString();
	}

	/**
	 * Prepared query for setting a player's rank
	 * 
	 * @param p
	 *            The player you want to act on
	 * @param amount
	 *            The amount you want to set the value to.
	 * @return Formatted query
	 */
	public static String setTokensQuery(OfflinePlayer p, long amount) {
		return "insert players/tokens " + p.getUniqueId().toString() + " "
				+ amount;
	}

	/**
	 * Prepared query for getting a player's rank
	 * 
	 * @param p
	 *            The player you want to act on
	 * @return Formatted query
	 */
	public static String getRankQuery(OfflinePlayer p) {
		return "retrieve players/rank " + p.getUniqueId().toString();
	}
	
	   /**
     * Prepared query for getting a player's rank
     * 
     * @param p
     *            The player you want to act on
     * @return Formatted query
     */
    public static String getRankQuery(String p) {
        return "retrieve players/rank " + p;
    }


	/**
	 * Prepared query for setting a player's rank
	 * 
	 * @param p
	 *            The player you want to act on
	 * @param rank
	 *            The rank you want to set the value to.
	 * @return Formatted query
	 */
	public static String setRankQuery(OfflinePlayer p, String rank) {
		return "insert players/rank " + p.getUniqueId().toString() + " " + rank;
	}
	
	   /**
     * Prepared query for setting a player's rank
     * 
     * @param p
     *            The player you want to act on
     * @param rank
     *            The rank you want to set the value to.
     * @return Formatted query
     */
    public static String setRankQuery(String p, String rank) {
        return "insert players/rank " + p + " " + rank;
    }

	/**
	 * Prepared query for getting a player's ban status
	 * 
	 * @param p
	 *            The player you want to act on
	 * @return Formatted query
	 */
	public static String getBannedQuery(OfflinePlayer p) {
		return "retrieve players/isBanned " + p.getUniqueId().toString();
	}

	/**
	 * Prepared query for setting a player's ban status
	 * 
	 * @param p
	 *            The player you want to act on
	 * @param value
	 *            The value you want to set the value to.
	 * @return Formatted query
	 */
	public static String setBannedQuery(OfflinePlayer p, String value) {
		return "insert players/isBanned " + p.getUniqueId().toString() + " "
				+ value;
	}
}
