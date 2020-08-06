package person.minigames.fantasy.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import person.minigames.fantasy.eventos.Geral;
import person.minigames.fantasy.utils.Manager;

public class Construir implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (!(sender instanceof Player)) return true;
		
		Player p = (Player)sender;
		
		if (!(p.hasPermission("lobby.construir"))) {
			p.sendMessage(Manager.noPermission);
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("construir")) {
			if (args.length >= 0) {
				if (Geral.construir.contains(p)) {
					Geral.construir.remove(p);
					p.sendMessage("§cVocê esta impedido de construir no mapa.");
				} else {
					Geral.construir.add(p);
					p.sendMessage("§aVocê foi liberado para construir no mapa.");
				}
				return true;
			}
			return true;
		}
		
		return false;
	}

}
