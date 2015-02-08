package mc.lightcraft.java.local.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import org.bukkit.Bukkit;

public class NMSUtils {

	private static String version = getVersion();

	/**
	 * Get the current Bukkit version
	 * @return the current version
	 */
	public static String getVersion() {
		if(version!=null)return version;
		String name = Bukkit.getServer().getClass().getPackage().getName();
		String version = name.substring(name.lastIndexOf('.') + 1) + ".";
		return version;
	}

	/**
	 * Get the net.minecraft.server class
	 * @param className the name of the class you want
	 * @return the chosen class or null if the class does not exist
	 */
	public static Class<?> getNMSClass(String className) {
		String fullName = "net.minecraft.server." + getVersion() + className;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(fullName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}
	
	/**
	 * Get the org.bukkit.craftbukkit class of chosen name
	 * @param className name of the class you want
	 * @return  the chosen class or null if the class does not exist
	 */
	public static Class<?> getOBCClass(String className) {
		String fullName = "org.bukkit.craftbukkit." + getVersion() + className;
		Class<?> clazz = null;
		try {
			clazz = Class.forName(fullName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return clazz;
	}

	/**
	 * Returns the NMS/OBC handle of chosen object
	 * @param obj the object you want the handle of
	 * @return the handle of the object, already invoked
	 */
	public static Object getHandle(Object obj) {
		try {
			return getMethod(obj.getClass(), "getHandle").invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	


	/**
	 * Returns the NMS/OBC handle of chosen block object
	 * @param obj the block object you want the handle of
	 * @return the handle of the object, already invoked
	 */
	public static Object getBlockHandle(Object obj) {
		try {
			Class<?> c = getOBCClass("block.CraftBlock");
			Method m = c.getDeclaredMethod("getNMSBlock");
			m.setAccessible(true);
			return m.invoke(obj);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 
	 * @param clazz Class you want to check
	 * @param name Name of the field
	 * @return The field, if available
	 * @throws Exception Can throw an exception if the field is not found or this class does not have permission to check
	 */
	public static Field getField(Class<?> clazz, String name) throws Exception{
		Field field = clazz.getDeclaredField(name);
		field.setAccessible(true);
		Field modifiersField = Field.class.getDeclaredField("modifiers");
		modifiersField.setAccessible(true);
		int modifiers = modifiersField.getInt(field);
		modifiers &= ~Modifier.FINAL;
		modifiersField.setInt(field, modifiers);
		return field;
	}

	/**
	 * Request a method from a chosen class file
	 * @param clazz the class you want to check
	 * @param name the name of the method you desire
	 * @param args Arguments needed for method invocation (eg integer.class, String.class)
	 * @return the method with the specified name and argument classes, if available
	 */
	public static Method getMethod(Class<?> clazz, String name,
			Class<?>... args) {
		for (Method m : clazz.getMethods())
			if (m.getName().equals(name) && (args.length == 0 || ClassListEqual(args, m.getParameterTypes()))) {
				m.setAccessible(true);
				return m;
			}
		for (Method m : clazz.getDeclaredMethods())
			if (m.getName().equals(name) && (args.length == 0 || ClassListEqual(args, m.getParameterTypes()))) {
				m.setAccessible(true);
				return m;
			}
		return null;
	}

	/**
	 * Compare two class arrays to make sure they match
	 * @param l1 Forsu class array
	 * @param l2 second class array
	 * @return true if the arrays match, otherwise false
	 */
	public static boolean ClassListEqual(Class<?>[] l1, Class<?>[] l2) {
		boolean equal = true;
		if (l1.length != l2.length)
			return false;
		for (int i = 0; i < l1.length; i++)
			if (l1[i] != l2[i]) {
				equal = false;
				break;
			}
		return equal;
	}

}
