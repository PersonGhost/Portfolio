package person.minigames.fantasy.utils;



import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageListener;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

import person.minigames.fantasy.Main;

public class BungeeAPI  implements PluginMessageListener {
	
	public static Map<String, BungeeServerInfo> trackedServers = new ConcurrentHashMap<>();
    public static HashMap<String, Integer> ServerValue = new HashMap<String, Integer>();
	public static int Lobby1=0;
    public static int Lobby2=0;
    public static int ALL=0;   
    public static int SkyWars=0;
    public static int SkyWarsII=0;
    public static int SkyWarsIII=0;
    public static int FullPvP=0;
    public static int KitPvP=0;
    public static int Rankup=0;
    public static int BedWars=0;
   
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
    	if (channel.equals("WDL|INIT")) {
			player.kickPlayer("§5§lFANTASY §e§lNETWORK\n\n§cVocê está usando um mod que não é\n§cPermitido na nossa rede de servidores.\n \n§cModificação detectada: §7World Downloader");
		} else if (channel.equals("BungeeCord")) {
   		 	DataInputStream in = new DataInputStream(new ByteArrayInputStream(message));
		    try {
		      String subChannel = in.readUTF();
		      if (subChannel.equals("PlayerCount")) {
		    	  String server = in.readUTF();
		       
		          int online = in.readInt();

		          BungeeServerInfo serverInfo = trackedServers.get(server);
		          serverInfo.setOnlinePlayers(online);
		      }
		    } catch (EOFException localEOFException) {}catch (IOException e) {
		    	e.printStackTrace();
		    }
		} else {
			return;
		}
    }
   
    public static void askPlayerCount(String server) {
	    try {
	        ByteArrayDataOutput out = ByteStreams.newDataOutput();
	        out.writeUTF("PlayerCount");
	        out.writeUTF(server);
	    	Bukkit.getServer().sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());    	   
	    } catch (Exception e) {
		   
	    }
    }
    
	@SuppressWarnings("unchecked")
	public static void writeCount(String server) {
		Iterator<Player> itr = (Iterator<Player>) Bukkit.getOnlinePlayers().iterator();
	    if (!itr.hasNext()) {
	      return;
	    }

	    Player fake = itr.next();
	    if (fake == null) {
	      return;
	    }

	    ByteArrayDataOutput out = ByteStreams.newDataOutput();
	    out.writeUTF("PlayerCount");
	    out.writeUTF(server);
	    fake.sendPluginMessage(Main.getInstance(), "BungeeCord", out.toByteArray());
	}
    
    public static void teleToServer(Player player, String msg, String server) {
    	player.sendMessage(msg);
    	try {
   	    	Messenger messenger = Bukkit.getMessenger();
   	    	if (!messenger.isOutgoingChannelRegistered(Main.getInstance(), "BungeeCord")) {
   	    		messenger.registerOutgoingPluginChannel(Main.getInstance(), "BungeeCord");
   	    	}
   	 
   	    	ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
   	      	DataOutputStream out = new DataOutputStream(byteArray);
   	      
   	      	out.writeUTF("Connect");
   	   
			out.writeUTF(server);
   	      
   	      	player.sendPluginMessage(Main.getInstance(), "BungeeCord", byteArray.toByteArray());
		} catch (IOException e) {
			e.printStackTrace();
		}	
    }

	public static String numberFormat(int coins){
		DecimalFormat numberFormat = new DecimalFormat("#,##0");
		String d = numberFormat.format(coins);
		if (d.length() > 32){
			d = d.substring(0, 32);
		}
		return d;
	}
    
    

}