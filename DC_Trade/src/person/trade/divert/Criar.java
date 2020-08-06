package person.trade.divert;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class Criar {
	
	public static ItemStack criar(Material m, String nome, int q, int data) {
		
		ItemStack item = new ItemStack(m,q,(short)data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		item.setItemMeta(meta);
		
		return item;
	}
	
	public static ItemStack criarL(Material m, String nome, int q, int data, String[] lore) {
		
		ItemStack item = new ItemStack(m,q,(short)data);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(nome);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		
		return item;
	}

	public static ItemStack skullL(String dono, String nome, int q, String[] lore) {
		
		ItemStack item = new ItemStack(Material.SKULL_ITEM, q, (short)0);
		SkullMeta meta = (SkullMeta)item.getItemMeta();
		meta.setDisplayName(nome);
		meta.setOwner(dono);
		meta.setLore(Arrays.asList(lore));
		item.setItemMeta(meta);
		
		return item;
	}
	

}
