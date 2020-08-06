package person.minigames.fantasy.comandos;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import person.minigames.fantasy.Config;
import person.minigames.fantasy.utils.Manager;

public class Spawn implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (!(sender instanceof Player)) return true;
		
		Player p = (Player)sender;
		
		if (!(p.hasPermission("lobby.fnspawn"))) {
			p.sendMessage(Manager.noPermission);
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("fnspawn")) {
			
			if (args.length >= 0) {
				p.sendMessage("§e[SPAWN] §aLocalização do spawn setada com sucesso.");
				Config locations = new Config("Locations.yml");
				
				if (!(locations.existeConfig())) {
					locations.saveConfig();
				}
				
				locations.set("Spawn", Manager.serializeLocation(p.getLocation()));
				locations.saveConfig();
				
			}
			
		}
		
		return false;
	}

	public static void teleportar(Player p) {
		Config locations = new Config("Locations.yml");
		if (!(locations.existeConfig() || locations.getString("Spawn") == null)) {
			locations.saveConfig();
			return;
		}
		Location loc = Manager.unserializeLocation(locations.getString("Spawn"));
		p.teleport(loc);
	}
	
}
