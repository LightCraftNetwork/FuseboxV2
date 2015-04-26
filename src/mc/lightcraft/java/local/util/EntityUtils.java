package mc.lightcraft.java.local.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import net.minecraft.server.v1_8_R2.AttributeInstance;
import net.minecraft.server.v1_8_R2.EntityInsentient;
import net.minecraft.server.v1_8_R2.GenericAttributes;
import net.minecraft.server.v1_8_R2.PathEntity;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

public class EntityUtils {

	private EntityUtils() {
	}

	@Deprecated
	/**
	 * Get if the chosen entity is on the ground, deprecated do to Bukkit's Entity#isOnGround() method
	 * @param ent the entity you want to check
	 * @return if the entity is on the ground
	 */
	public static boolean isGrounded(Entity ent) {
		try {
			if ((ent instanceof CraftEntity)) {
				return ((CraftEntity) ent).getHandle().onGround;
			}
		} catch (Exception exc) {
		}
		return ent.isOnGround();
	}

	/**
	 * Toggle the AI on a giving entity
	 * 
	 * @param ent
	 *            entity you want to edit
	 * @param value
	 *            is the AI to we marked as active?
	 */
	public static void setNoAI(Entity ent, boolean value) {
		try {
			Object nmsent = NMSUtils.getHandle(ent);
			Class<?> entity = NMSUtils.getNMSClass("Entity");
			Field f = NMSUtils.getField(entity, "datawatcher");
			Class<?> datawatcher = NMSUtils.getNMSClass("DataWatcher");
			Object dataent = f.get(nmsent);
			Method m = NMSUtils.getMethod(datawatcher, "watch", int.class,
					Object.class);
			m.invoke(dataent, 15, Byte.valueOf((byte) (value ? 1 : 0)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets an array off all entities near a location
	 * 
	 * @param l
	 *            The location you want the nearby entities of
	 * @param radius
	 *            how far away the entity can be before it is no longer counted
	 * @return an array containing all the nearby entities
	 */
	public static Entity[] getNearbyEntities(Location l, int radius) {
		World w = l.getWorld();
		if (w.getEntities().isEmpty())
			return new Entity[0];
		ArrayList<Entity> entities = new ArrayList<Entity>();
		for (Entity e : w.getEntities()) {
			if (e.getLocation().distance(l) <= radius)
				entities.add(e);
		}
		return entities.toArray(new Entity[entities.size()]);
	}

	/**
	 * Force an entity to follow a player much like a pet wolf
	 * 
	 * @param plugin
	 *            the plugin forcing the entity to follow
	 * @param player
	 *            the player that the 'pet' belongs to
	 * @param pet
	 *            the entity that will act as a pet
	 * @param speed
	 *            the speed that the entity will path at
	 */
	public void setPetFollow(final Plugin plugin, final Player player,
			final LivingEntity pet, final double speed) {
		new BukkitRunnable() {
			public void run() {
				if ((!pet.isValid() || (!player.isOnline()))) {
					this.cancel();
				}
				net.minecraft.server.v1_8_R2.Entity pett = ((CraftEntity) pet)
						.getHandle();
				((EntityInsentient) pett).getNavigation().a(2);
				Object petf = ((CraftEntity) pet).getHandle();
				Location targetLocation = player.getLocation();
				PathEntity path;
				path = ((EntityInsentient) petf).getNavigation().a(
						targetLocation.getX() + 1, targetLocation.getY(),
						targetLocation.getZ() + 1);
				if (path != null) {
					((EntityInsentient) petf).getNavigation().a(path, 1.0D);
					((EntityInsentient) petf).getNavigation().a(2.0D);
				}
				int distance = (int) Bukkit.getPlayer(player.getName())
						.getLocation().distance(pet.getLocation());
				if (distance > 10 && !pet.isDead()) {
					pet.teleport(player.getLocation());
				}
				AttributeInstance attributes = ((EntityInsentient) ((CraftEntity) pet)
						.getHandle()).getAttributeInstance(GenericAttributes.d);
				attributes.setValue(speed);
			}
		}.runTaskTimer(plugin, 0L, 20L);
	}
}
