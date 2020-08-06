package person.divert.apis;

import java.util.Arrays;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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

}
