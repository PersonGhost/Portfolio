package person.minigames.fantasy.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import person.minigames.fantasy.eventos.Geral;
import person.minigames.fantasy.utils.Manager;

public class Chat implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (!(sender.hasPermission("lobby.chat.admin"))) {
			sender.sendMessage(Manager.noPermission);
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("fnchat")) {
			
			if (args.length >= 0) {
				if (Geral.chatstatus == false) {
					Geral.chatstatus = true;
					Bukkit.broadcastMessage("§5§lFantasy §8➜ §cChat do servidor desativado.");
				} else if (Geral.chatstatus == true) {
					Geral.chatstatus = false;
					Bukkit.broadcastMessage("§5§lFantasy §8➜ §aChat do servidor ativado.");
				}
			}
			
			return true;
		}
		
		return false;
	}

}
