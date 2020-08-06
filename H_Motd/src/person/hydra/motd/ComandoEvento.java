package person.hydra.motd;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class ComandoEvento implements CommandExecutor, Listener {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (cmd.getName().equalsIgnoreCase("hmotd")) {
			
			if (args.length == 0) {
				sender.sendMessage("§e[H_Motd] §f'/hmotd reload'§e.");
				sender.sendMessage("§e[H_Motd] §f'/hmotd info'§e.");
				return true;
			}
		
			if (args.length > 0) {
				
				String i = args[0];
				
				if (i.equalsIgnoreCase("reload")) {
					HMotd.getInstance().reloadConfig();
					sender.sendMessage("§e[H_Motd] §aConfiguração recarregada, motd atualizada.");
					return true;
				}
				
				if (i.equalsIgnoreCase("info")) {
					sender.sendMessage(HMotd.getInstance().getConfig().getString("Motd").replace("%l", "\n").replace("&", "§"));
					return true;
				}
				
			}
		}
		
		return false;
	}
	
	@EventHandler
	public void motd(ServerListPingEvent e) {
		e.setMotd(HMotd.getInstance().getConfig().getString("Motd").replace("%l", "\n").replace("&", "§"));
	}

}
