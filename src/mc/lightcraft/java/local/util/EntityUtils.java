package mc.lightcraft.java.local.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.craftbukkit.v1_8_R1.entity.CraftEntity;
import org.bukkit.entity.Entity;

public class EntityUtils {

	@Deprecated
	/**
	 * Get if the chosen entity is on the ground, deprecated do to Bukkit's Entity#isOnGround() method
	 * @param ent the entity you want to check
	 * @return if the entity is on the ground
	 */
	public static boolean isGrounded(Entity ent)
	{
		try {
		if ((ent instanceof CraftEntity)) {
			return ((CraftEntity)ent).getHandle().onGround;
		}
		}catch(Exception exc) {
		}
			return ent.isOnGround();
	}

	
	/**
	 * Toggle the AI on a giving entity
	 * @param ent entity you want to edit
	 * @param value is the AI to we marked as active?
	 */
	public static void setNoAI(Entity ent, boolean value){
		try{
			Object nmsent = NMSUtils.getHandle(ent);
			Class<?> entity = NMSUtils.getNMSClass("Entity");
			Field f = NMSUtils.getField(entity, "datawatcher");
			Class<?> datawatcher = NMSUtils.getNMSClass("DataWatcher");
			Object dataent = f.get(nmsent);
			Method m = NMSUtils.getMethod(datawatcher, "watch", int.class, Object.class);
			m.invoke(dataent, 15, Byte.valueOf((byte)(value ? 1 : 0)));
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
