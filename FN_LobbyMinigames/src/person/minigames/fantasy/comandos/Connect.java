package person.minigames.fantasy.comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import person.minigames.fantasy.utils.BungeeAPI;

public class Connect implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (!(sender instanceof Player))return true;
		
		Player p = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("connect")) {
			
			if (args.length == 0) {
				p.sendMessage("§5§lFANTASY §e§l➜ §cInforme o servidor desejado.");
				return true;
			}
			
			if (args.length > 0) {
				BungeeAPI.teleToServer(p, "§5§lFANTASY §e§l➜ §aConectando ao servidor §f"+args[0], args[0]);
				return true;
			}
			
			return true;
		}
		
		return false;
	}

}
