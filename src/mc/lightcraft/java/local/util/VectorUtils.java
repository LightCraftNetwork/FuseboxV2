package mc.lightcraft.java.local.util;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

public class VectorUtils {

	private VectorUtils() {

	}
	/**
	 * Get the vector for 'bumping" an entity away from a location
	 * @param entity the entity you are wanting bumped
	 * @param from the place you want them bumbed away from
	 * @param power how hard you want them to be bumped
	 * @return the vector required to bump them away
	 */
	public static Vector getBumpVector(Entity entity, Location from,
			double power) {
		Vector bump = entity.getLocation().toVector().subtract(from.toVector()).normalize();
		bump.multiply(power);
		return bump;
	}

	/**
	 * Get the vector for pulling an entity toward a location
	 * @param entity the entity you want to move
	 * @param to the location you want to pull the to
	 * @param power the power with which you want to pull them
	 * @return the vector for pulling the entity towards the location with chosen power.
	 */
	public static Vector getPullVector(Entity entity, Location to, double power) {
		Vector pull = to.toVector().subtract(entity.getLocation().toVector())
				.normalize();
		pull.multiply(power);
		return pull;
	}

	/**
	 * Used to bump an entity away from a location
	 * @param entity entity you want to bump
	 * @param from the spot you are bumping them away from
	 * @param power how much force to bump them with
	 */
	public static void bumpEntity(Entity entity, Location from, double power) {
		entity.setVelocity(getBumpVector(entity, from, power));
	}
	
	/**
	 * 
	 * Used to bump an entity away from a location
	 * @param entity entity you want to bump
	 * @param from the spot you are bumping them away from
	 * @param power how much force to bump them with
	 * @param fixedY the Y-value you want the bump to have
	 */
	public static void bumpEntity(Entity entity, Location from, double power,
			double fixedY) {
		Vector vector = getBumpVector(entity, from, power);
		vector.setY(fixedY);
		entity.setVelocity(vector);
	}
	
	/**
	 * Used to pull an entity to  a location
	 * @param entity entity you want to move
	 * @param to the spot you are pulling them to
	 * @param power how much force to pull them with
	 */
	public static void pullEntity(Entity entity, Location to, double power) {
		entity.setVelocity(getPullVector(entity, to, power));
	}

	/**
	 * Used to pull an entity to  a location
	 * @param entity entity you want to move
	 * @param to the spot you are pulling them to
	 * @param power how much force to pull them with
	 * @param fixedYthe Y-value you want the pull to have
	 */
	public static void pullEntity(Entity entity, Location to, double power,
			double fixedY) {
		Vector vector = getPullVector(entity, to, power);
		vector.setY(fixedY);
		entity.setVelocity(vector);
	}
}
