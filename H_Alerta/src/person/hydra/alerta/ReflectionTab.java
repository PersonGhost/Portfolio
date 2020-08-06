package person.hydra.alerta;

import java.lang.reflect.Field;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;

public class ReflectionTab {

	public static void sendTabColor(Player player, String cima	, String baixo){
		
	    CraftPlayer craftplayer = (CraftPlayer) player;
	    PlayerConnection connection = craftplayer.getHandle().playerConnection;
	    IChatBaseComponent headerJSON = ChatSerializer.a("{\"text\": \"" + cima +"\"}");
	    IChatBaseComponent footerJSON = ChatSerializer.a("{\"text\": \"" + baixo +"\"}");
	    PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();
	  
	    try {
	        Field headerField = packet.getClass().getDeclaredField("a");
	        headerField.setAccessible(true);
	        headerField.set(packet, headerJSON);
	        headerField.setAccessible(!headerField.isAccessible());
	      
	        Field footerField = packet.getClass().getDeclaredField("b");
	        footerField.setAccessible(true);
	        footerField.set(packet, footerJSON);
	        footerField.setAccessible(!footerField.isAccessible());
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    
	    connection.sendPacket(packet);
	   
		
	}
	
}
