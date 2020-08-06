package person.hydra.alerta;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Comando implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (!(sender instanceof Player)) return true;
		
		Player p = (Player)sender;
		
		if (!(p.hasPermission("comando.alerta"))) {
			p.sendMessage("§7§o[§6§l§oHYDRA§f§l§oMC§7§o] §cSem permissão para executar este comando.");
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("alerta")) {
			
			if (args.length == 0) {
				p.sendMessage("§e[H_Alerta] §f'/alerta <mensagem>'.");
				return true;
			}
			
			if (args.length > 0) {
				String msg = "";
				for (int i = 0; i < args.length; i++) {
					if (i == args.length - 1) {
						msg = msg + args[i];
					} else {
						msg = msg + args[i] + " ";
					}
				}
				
				String subtitle = msg.replace("&", "§");
				
				for (Player s : Bukkit.getOnlinePlayers()) {
					ReflectionTitle.sendFullTitle(s, "§c§lALERTA", subtitle, 40, 20, 40);
					ReflectionActionBar.sendAction(s, subtitle);
				}
				return true;
			}
		}
		
		return false;
	}

}
