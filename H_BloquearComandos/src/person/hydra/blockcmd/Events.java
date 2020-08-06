package person.hydra.blockcmd;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class Events implements Listener {
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onCommand(PlayerCommandPreprocessEvent e) {
		for (String cmd : Main.getInstance().getConfig().getStringList("Comandos")) {
			if (e.getMessage().toLowerCase().startsWith("/"+cmd.replace("- ", "")) || e.getMessage().equalsIgnoreCase("/"+cmd.replace("- ", ""))) {
				e.setCancelled(true);
				e.getPlayer().sendMessage("§7§o[§6§l§oHYDRA§f§l§oMC§7§o] §cComando desejado encontra-se bloqueado.");
			}
		}
	}

}
