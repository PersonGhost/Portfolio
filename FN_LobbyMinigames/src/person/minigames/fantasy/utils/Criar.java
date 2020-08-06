package person.minigames.fantasy.utils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.UUID;

import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;

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
	
	public static ItemStack criarSkull(String nome, String[] descricao, String owner, int size) {
		ItemStack item = new ItemStack(Material.SKULL_ITEM);
		
		SkullMeta itemMeta = (SkullMeta)item.getItemMeta();
		item.setDurability((short)3);
		itemMeta.setDisplayName(nome);
		  
		itemMeta.setOwner(owner);
		item.setAmount(size);
		
		
		ArrayList<String> Descricao = new ArrayList<>();
	    for (String descri : descricao) {	    	 
	    	 Descricao.add(descri);      	
	    }
	 
	    itemMeta.setLore(Descricao);	
	    item.setItemMeta(itemMeta);
	    return item;
	}
	
	public static ItemStack buildHead(String url,String nome,String[] lore,int amount) {
	      
		url = "http://textures.minecraft.net/texture/" + url;
		ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
		if ((url == null) || (url.isEmpty())) {
			return skull;
		}
		SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
		skullMeta.setDisplayName(nome);
		skull.setAmount(amount);
		ArrayList<String> Descricao = new ArrayList<>();
		for (String descri : lore) {	    	 
			Descricao.add(descri);      	
	      
		}
		skullMeta.setLore(Descricao);	
		GameProfile profile = new GameProfile(UUID.randomUUID(), null);
	    byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", new Object[] { url }).getBytes());
	      	
	     
	    profile.getProperties().put("textures", new Property("textures", new String(encodedData)));
	    Field profileField = null;
	    try {
	    	profileField = skullMeta.getClass().getDeclaredField("profile");
	    } catch (NoSuchFieldException|SecurityException e) {
	    	e.printStackTrace();
	    } 
	    assert (profileField != null);
	    profileField.setAccessible(true);
	    try {
	    	profileField.set(skullMeta, profile);
	    } catch (IllegalArgumentException|IllegalAccessException e) {
	    	e.printStackTrace();
	    }
	    skull.setItemMeta(skullMeta);
	    return skull;
    }
}
