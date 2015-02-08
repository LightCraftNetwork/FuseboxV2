package mc.lightcraft.java.local.util;

import net.minecraft.server.v1_8_R1.PacketPlayOutNamedSoundEffect;

import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class AudioUtils {
	private AudioUtils() {
	}

	/**
	 * Play the hurt sound for the specified entity type at provided location
	 * 
	 * @param location
	 *            The location you want the sound to play at
	 * @param mob
	 *            The entityType you want the hurt sound of. If the chosen
	 *            entity does not have a hurt sound, the standard hurt sound for
	 *            the player is played.
	 */
	public static void playDamageSound(Location location, EntityType mob) {
		Sound sound = Sound.HURT_FLESH;

		switch (mob) {
		case BAT:
			sound = Sound.BAT_HURT;
			break;
		case GIANT:
		case PIG_ZOMBIE:
		case ZOMBIE:
			sound = Sound.ZOMBIE_HURT;
			break;
		case BLAZE:
			sound = Sound.BLAZE_HIT;
			break;
		case SPIDER:
		case CAVE_SPIDER:
			sound = Sound.SPIDER_IDLE;
			break;
		case CHICKEN:
			sound = Sound.CHICKEN_HURT;
			break;
		case MUSHROOM_COW:
		case COW:
			sound = Sound.COW_HURT;
			break;
		case CREEPER:
			sound = Sound.CREEPER_HISS;
			break;
		case ENDER_DRAGON:
			sound = Sound.ENDERDRAGON_GROWL;
			break;
		case ENDERMAN:
			sound = Sound.ENDERMAN_HIT;
			break;
		case GHAST:
			sound = Sound.GHAST_SCREAM;
			break;
		case IRON_GOLEM:
			sound = Sound.IRONGOLEM_HIT;
			break;
		case MAGMA_CUBE:
			sound = Sound.MAGMACUBE_JUMP;
			break;
		case OCELOT:
			sound = Sound.CAT_MEOW;
			break;
		case PIG:
			sound = Sound.PIG_IDLE;
			break;
		case SHEEP:
			sound = Sound.SHEEP_IDLE;
			break;
		case SILVERFISH:
			sound = Sound.SILVERFISH_HIT;
			break;
		case SKELETON:
			sound = Sound.SKELETON_HURT;
			break;
		case SLIME:
			sound = Sound.SLIME_ATTACK;
			break;
		case SNOWMAN:
			sound = Sound.STEP_SNOW;
			break;
		case WOLF:
			sound = Sound.WOLF_HURT;
			break;
		default:
			sound = Sound.HURT_FLESH;
			break;
		}
		location.getWorld().playSound(location, sound, 1, 1);
	}

	/**
	 * Send a custom sound packet to a player
	 * 
	 * @param player
	 *            the player you want to send the packet to
	 * @param sound
	 *            the sound you want to packet to have
	 * @param vol
	 *            the volume you want the sound to play at
	 * @param pitch
	 *            the pitch that the sound will have
	 */
	public static void playCustomSound(Player player, String sound, float vol,
			float pitch) {
		PacketPlayOutNamedSoundEffect packet = new PacketPlayOutNamedSoundEffect(
				sound, player.getLocation().getBlockX(),
				player.getLocation().getBlockY(), player.getLocation().getBlockZ(), vol,
				pitch);
		NMSUtils.sendPacket(player, packet);
	}
}
