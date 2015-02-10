package mc.lightcraft.java.remote.LCDatabase;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class DatabaseManager {

	private String accessToken;
	private String host;

	/**
	 * Return the query from the server! You must use setAccessToken() and
	 * setHost() before you do this. Handle running yourself- async is
	 * recommended!
	 * 
	 * @param query
	 *            the query you would like to send.
	 * @return String server's reply
	 */
	public synchronized String query(String query) {
		try {

			URL url = new URL("http://" + host + "/" + accessToken + "!!"
					+ query.replace(" ", "%20"));
			URLConnection c = url.openConnection();

			c.setConnectTimeout(500);
			c.setReadTimeout(100);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					c.getInputStream()));

			String s = in.readLine();
			// TODO add more line reading, possibly in the future.

			in.close();

			return s;
		} catch (Exception ex) {
			ex.printStackTrace();
			return "FAILED: No response was sent.";
		}

	}

	/**
	 * Run the insert command on the server. You must use setAccessToken() and
	 * setHost() before you do this. Handle running yourself- async is
	 * recommended!
	 * 
	 * @param category
	 *            The category you would like to insert in.
	 * @param key
	 *            The key that you want to insert with
	 * @param value
	 *            The value you want to set.
	 * @return String success/failure.
	 */
	public synchronized String insert(String category, String key, String value) {
		return query("insert " + category + " " + key + " " + value);
	}

	/**
	 * Run the retrieve command on the server. You must use setAccessToken() and
	 * setHost() before you do this. Handle running yourself- async is
	 * recommended!
	 * 
	 * @param category
	 *            The category you would like to retrieve from.
	 * @param key
	 *            The key that you want to retrieve from
	 * @return String value
	 */
	public synchronized String retrieve(String category, String key) {
		return query("retrieve " + category + " " + key);
	}

	/**
	 * Stop the server. You must use setAccessToken() and setHost() before you
	 * do this. Handle running yourself- async is recommended!
	 * 
	 * @param reason
	 *            The reason you are stopping the server!
	 */
	public synchronized String stop(String reason) {
		return query("stop " + reason);
	}

	/**
	 * Stop the server. You must use setAccessToken() and setHost() before you
	 * do this. Handle running yourself- async is recommended!
	 * 
	 * @param reason
	 *            The reason you are stopping the server!
	 */
	public synchronized String reload() {
		return query("reload");
	}

	@Deprecated
	/**
	 * Run the help command on the server. You must use setAccessToken() and
	 * setHost() before you do this. Handle running yourself- async is
	 * recommended!
	 */
	public synchronized String help() {
		return query("help");
	}

	/**
	 * Run the list command on the server. You must use setAccessToken() and
	 * setHost() before you do this. Handle running yourself- async is
	 * recommended!
	 * 
	 * @param directory
	 *            The directory you want to list from
	 */
	public synchronized String list(String directory) {
		return query("list " + directory);
	}

	/**
	 * @return the defined Access Token.
	 */
	public String getAccessToken() {
		return accessToken;
	}

	/**
	 * Set the access token
	 * 
	 * @param accessToken
	 *            the access token
	 */
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	/**
	 * @return the host address defined.
	 */
	public String getHost() {
		return host;
	}

	/**
	 * Set the host address
	 * 
	 * @param the
	 *            host address
	 */
	public void setHost(String host) {
		this.host = host;
	}

}
