package br.com.fantasynetwork.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import br.com.fantasynetwork.bukkit.Config;

public class loadPlayer {
	
	public static Logger log = Bukkit.getLogger();
	public static Config config = new Config("config.yml");
	public static Database dataBase;
	public static Database getDataBase() {
		return dataBase;
	}
	
	public static boolean insServer() {
		return server;
	}
	
	private static boolean server = false;
	public static boolean register() {
		try {
			Bukkit.getConsoleSender().sendMessage("§e[Database] Storage type: §fMySQL§e.");
			Data data = new MySQL(config.getString("MySQL.host"), config.getString("MySQL.user"), config.getString("MySQL.password"), config.getString("MySQL.database"));
			dataBase = new Database(data);
			registerTables();
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static Connection getConnection() {
		return dataBase.getConnection();
	}
	
	public static boolean registerTables() {
		try {
	      String query = "CREATE TABLE IF NOT EXISTS `Permissions`(`id` INT NOT NULL AUTO_INCREMENT, `jogador` VARCHAR(30) NULL, `uuid` VARCHAR(100) NULL, `permissions` VARCHAR(900), `grupo` VARCHAR(60), `grupos` VARCHAR(600), `prefix` VARCHAR(30), `suffix` VARCHAR(30), PRIMARY KEY(`id`))";
	      PreparedStatement ps = getConnection().prepareStatement(query);
	      ps.execute();
	      ps.close();
	      
	      return true;
	    } catch (Exception e) {
	      e.printStackTrace();
	    }
	    return false;
	}

}




































































//
//public static void loadPlayer(Player p) {
//	  new BukkitRunnable() {
//			
//		@Override
//		public void run() {
//			try
//		    
//		    {
//		      ResultSet rs =LoadPlayers.getDataBase().getConnection().createStatement().executeQuery("SELECT * FROM " +"boxes" +" WHERE name='" + p.getName() + "';");
//		      
//		      if (rs.next())
//		      {
//		    	
//		    	  String uuid = rs.getString("uuid");
//	         	  int frag = rs.getInt("frag");
//	           	  String balance = rs.getString("boxes");
//	           	 String name = rs.getString("name");
//	             Long delay = rs.getLong("delay");
//	             String caixa = rs.getString("caixa");
//	             List<String> bl = new ArrayList<String>();
//	           	 if (balance != null) {
//	              	if (balance.contains(",")) {
//	                	 for (String b : balance.split(",")) {
//	                		bl.add(b); 
//	                	 }   	
//	              	}else {
//	              		bl.add(balance)
//	;              	}
//	           	 }
//	        
//	           	 
//	       	  PlayerBox.datas.put(uuid, new PlayerBox(uuid, name, bl,frag));         
//		      Entregador.cooldown.put(p, delay);
//		      Entregador.BoxCooldown.put(p, caixa);
//		      }
//		
//		    }   catch (SQLException localException) {
//		   
//		      
//		    }
//			
//		}
//	}.runTaskAsynchronously(Main.getPlugin());
//}



