package mc.lightcraft.java.common.utils;

import java.util.Arrays;
import java.util.List;

import net.minecraft.server.v1_8_R1.NBTTagCompound;
import net.minecraft.server.v1_8_R1.NBTTagList;

import org.bukkit.craftbukkit.v1_8_R1.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {

	private ItemUtils() {
	}

	/**
	 * Set an ItemStack's name and one line of lore.
	 * 
	 * @param is
	 *            The itemStack you want to rename
	 * @param name
	 *            The name you want to give the ItemStack
	 * @param applyLore
	 *            String list of lore you want to give the ItemStack
	 */
	public static ItemStack setName(ItemStack is, String applyName,
			String[] applyLore) {
		ItemMeta im = is.getItemMeta();
		if (applyName != null) {
			im.setDisplayName(applyName);
		}
		List<String> lore = Arrays.asList(applyLore);
		if (lore != null) {
			im.setLore(lore);
		}
		is.setItemMeta(im);
		return is;
	}

	/**
	 * Set an ItemStack's name and one line of lore.
	 * 
	 * @param is
	 *            The itemStack you want to rename
	 * @param name
	 *            The name you want to give the ItemStack
	 * @param applyLore
	 *            One line of lore you want to give the ItemStack
	 */
	public static ItemStack setName(ItemStack is, String applyName,
			String applyLore) {
		String[] lore2 = { applyLore };
		ItemMeta im = is.getItemMeta();
		if (applyName != null) {
			im.setDisplayName(applyName);
		}
		List<String> lore = Arrays.asList(lore2);
		if (lore != null) {
			im.setLore(lore);
		}
		is.setItemMeta(im);
		return is;
	}

	/**
	 * Set an ItemStack's name.
	 * 
	 * @param is
	 *            The itemStack you want to rename
	 * @param name
	 *            The name you want to give the ItemStack
	 * @return
	 */
	public static ItemStack setName(ItemStack is, String name) {
		ItemMeta im = is.getItemMeta();
		if (name != null) {
			im.setDisplayName(name);
		}
		is.setItemMeta(im);
		return is;
	}

	/**
	 * Add enchantment glow. Needs to be converted to version-independent
	 * sometime soon. Known cavet: Picking the item up in the inventory removes
	 * the glow.
	 * 
	 * @param item
	 * @return ItemStack with enchantment glow
	 */
	public static ItemStack addEnchantmentGlow(ItemStack item) {
		net.minecraft.server.v1_8_R1.ItemStack nmsStack = CraftItemStack
				.asNMSCopy(item);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()) {
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}
		if (tag == null)
			tag = nmsStack.getTag();
		NBTTagList ench = new NBTTagList();
		tag.set("ench", ench);
		nmsStack.setTag(tag);
		return CraftItemStack.asCraftMirror(nmsStack);
	}
}
