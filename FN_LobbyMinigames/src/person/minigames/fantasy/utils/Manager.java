package person.minigames.fantasy.utils;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.Calendar;

import org.bukkit.Bukkit;
import org.bukkit.Location;

public class Manager {
	public static String prefix ="§5§lFantasy§e§lNetwork §8➜ ";
	public static String noPermission = "§cVocê não tem permissão para executar este comando.";
	
	public static String serializeLocation(Location loc) {
		String toString = loc.getWorld().getName() + " : " + loc.getX() + " : " + loc.getY() + " : " + loc.getZ() + " : " + loc.getPitch() + " : " + loc.getYaw();
		return toString;
	}
	  
	  
	public static Location unserializeLocation(String path) {
		String[] sp = path.split(" : ");
		Location loc = new Location(Bukkit.getWorld(sp[0]), Double.parseDouble(sp[1]), Double.parseDouble(sp[2]), Double.parseDouble(sp[3]));
	    loc.setPitch(Float.parseFloat(sp[4]));
	    loc.setYaw(Float.parseFloat(sp[5]));
	    return loc;
	}

	public static String modificarCoins(Integer i) {
		String s = null;
		DecimalFormat df = new DecimalFormat("###,###,###,###,###,###,###,###,###,###,###,###,###,###,###,###,###,###,###");
		s= df.format(i).replace(".", ",");  	 	  
		return s;
	}
	
	public static String getTime(long ms) {
		 Calendar c = Calendar.getInstance();
		 c.setTime(new Date(ms));
		 String hour =String.valueOf((c.get(Calendar.HOUR_OF_DAY)  >12?(c.get(Calendar.HOUR_OF_DAY) - 12) :c.get(Calendar.HOUR_OF_DAY)));
	   
	  
		 String minute =(c.get(Calendar.HOUR_OF_DAY) > 12? c.get(Calendar.MINUTE) + " da tarde":c.get(Calendar.MINUTE) + " da manhã");
	
		 //String day_week = String.valueOf(c.get(Calendar.DAY_OF_WEEK));
		 //String day_offweek = day_week.replace("8", "domingo").replace("7", "sábado").replace("6", "sexta-feira").replace("5", "quinta-feira").replace("4", "quarta-feira").replace("3", "terça-feira").replace("2", "segunda-feira");
		 
		 
		 String day =String.valueOf(c.get(Calendar.DAY_OF_MONTH));
	
		 String month =String.valueOf(c.get(Calendar.MONTH) + 1).replace("12", "dezembro").replace("11", "novembro").replace("10","outubro").replace("9","setembro").replace("8","agosto").replace("7","julho").replace("6","junho").replace("5","maio").replace("4","abril").replace("3","março").replace("2","fevereiro").replace("1","janeiro");
		 String year =String.valueOf( c.get(Calendar.YEAR));
	     return day +" de " + month + " de " + year  +" ás " + (hour.length() == 1 ?"0"+hour:hour) +":" + (c.get(Calendar.MINUTE) < 10?"0" + minute:minute);
	 }  
	 
}
