package person.divert.apis;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import person.divert.Divert;

public class Cooldown {
	
	public static HashMap<Player, String> Cacto = new HashMap<>();
	public static HashMap<Player, String> Foguete = new HashMap<>();
	public static HashMap<Player, String> Armadilha = new HashMap<>();
	public static HashMap<Player, String> Restringir = new HashMap<>();
	public static HashMap<Player, String> Thor = new HashMap<>();
	
	public static void Delay(Player p, String engenhoca, int time) {
		
		Bukkit.getScheduler().scheduleSyncDelayedTask(Divert.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				if (engenhoca.equalsIgnoreCase("cacto") && Cacto.containsKey(p)) {
					Cacto.remove(p);
					p.sendMessage("§e§lENGENHOCA§f: Você já pode utilizar sua engenhoca de §aCacto§f.");
				} else if (engenhoca.equalsIgnoreCase("foguete") && Foguete.containsKey(p)) {
					Foguete.remove(p);
					p.sendMessage("§e§lENGENHOCA§f: Você já pode utilizar sua engenhoca de §aFoguete§f.");
				} else if (engenhoca.equalsIgnoreCase("armadilha") && Armadilha.containsKey(p)) {
					Armadilha.remove(p);
					p.sendMessage("§e§lENGENHOCA§f: Você já pode utilizar sua engenhoca de §aArmadilha§f.");
				} else if (engenhoca.equalsIgnoreCase("restringir") && Restringir.containsKey(p)) {
					Restringir.remove(p);
					p.sendMessage("§e§lENGENHOCA§f: Você já pode utilizar sua engenhoca de §aRestringir§f.");
				} else if (engenhoca.equalsIgnoreCase("thor") && Thor.containsKey(p)) {
					Thor.remove(p);
					p.sendMessage("§e§lENGENHOCA§f: Você já pode utilizar sua engenhoca de §aThor§f.");
				}
				
			}
		}, time*20);
		
	}

}
