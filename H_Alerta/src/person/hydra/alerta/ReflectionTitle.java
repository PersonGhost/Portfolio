package person.hydra.alerta;

import java.lang.reflect.Constructor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class ReflectionTitle {

	public static void sendFullTitle(Player p, String title, String subtitle, int fadeIn, int stay, int fadeOut){
		try {
			title = ChatColor.translateAlternateColorCodes('&', title);
			
			Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
			Object chat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
			
			Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], 
					getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
			Object packet = titleConstructor.newInstance(enumTitle, chat, fadeIn, stay, fadeOut);
			
			sendPacket(p, packet);
			
			// SubTitle
			subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
			
			Object enumSub = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
			Object chatSub = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + subtitle + "\"}");
			
			Constructor<?> subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], 
					getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
			Object packetSub = subtitleConstructor.newInstance(enumSub, chatSub, fadeIn, stay, fadeOut);
			
			sendPacket(p, packetSub);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void sendTitle(Player p, String title, int fadeIn, int stay, int fadeOut){
		
		try {
			Object enumTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
			Object chat = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
			
			Constructor<?> titleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], 
					getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
			Object packet = titleConstructor.newInstance(enumTitle, chat, fadeIn, stay, fadeOut);
			
			sendPacket(p, packet);
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void sendSubtitle(Player p, String subtitle, int fadeIn, int stay, int fadeOut){
		
		try {
			
			Object enumSub = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
			Object chatSub = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\": \"" + subtitle + "\"}");
			
			Constructor<?> subtitleConstructor = getNMSClass("PacketPlayOutTitle").getConstructor(getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], 
					getNMSClass("IChatBaseComponent"), int.class, int.class, int.class);
			Object packetSub = subtitleConstructor.newInstance(enumSub, chatSub, fadeIn, stay, fadeOut);
			
			sendPacket(p, packetSub);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void sendPacket(Player p, Object packet){
		try {
			Object handle = p.getClass().getMethod("getHandle").invoke(p);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Class<?> getNMSClass(String name){
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
