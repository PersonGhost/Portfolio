package person.minigames.fantasy.comandos;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import person.minigames.fantasy.perfil.Jogador;
import person.minigames.fantasy.utils.Manager;

public class Vanish implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (!(sender instanceof Player)) return true;
		
		Player p = (Player)sender;
		
		if (!(p.hasPermission("lobby.vanish"))) {
			p.sendMessage(Manager.noPermission);
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("vanish")) {
			
			if (args.length >= 0) {
				if (!(Jogador.get(p).getVanish().equalsIgnoreCase("desativado"))) {
					checkVanishVisibility(p);
					p.sendMessage(Manager.prefix+"§aVocê ativou o modo vanish.");
				} else if (Jogador.get(p).getVanish().equalsIgnoreCase("ativado")) {
					checkVanishVisibility(p);
					p.sendMessage(Manager.prefix+"§aVocê desativou o modo vanish.");
				}
			}
			
			return true;
		}
		
		return false;
	}
	
	public static void checkVanishVisibility(Player p) {
		String vanish = Jogador.get(p).getVanish();
		
		if (vanish.equalsIgnoreCase("ativado")) {
			for (Player s : Bukkit.getOnlinePlayers()) {
				if (!(s.hasPermission("lobby.vanish") || p == s)) {
					s.hidePlayer(p);
				}
			}
		} else {
			for (Player s : Bukkit.getOnlinePlayers()) {
				s.showPlayer(p);
			}
		}
		
		for (Player s : Bukkit.getOnlinePlayers()) {
			String vanishother = Jogador.get(s).getVanish();
			
			if (vanishother.equalsIgnoreCase("ativado")) {
				if (!(p.hasPermission("lobby.vanish") || s == p)) {
					p.hidePlayer(s);
				}
			} else {
				p.showPlayer(s);
			}
		}
		
	}
	
	
}
