package person.minigames.fantasy.comandos;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import person.minigames.fantasy.perfil.Jogador;
import person.minigames.fantasy.utils.Manager;

public class Tell implements CommandExecutor {

	private HashMap<Player, Player> responder = new HashMap<>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (!(sender instanceof Player)) return true;
		
		Player p = (Player) sender;
		
		if (!(p.hasPermission("lobby.tell"))) {
			p.sendMessage(Manager.noPermission);
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("tell")) {
			
			if (args.length <= 1) {
				p.sendMessage("§eComando incorreto, digite §f'tell <nick> <mensagem>'§e.");
				return true;
			}

			if (args.length >= 2) {
				Player jogador = Bukkit.getPlayer(args[0]);

				if (jogador == null) {
					p.sendMessage(Manager.prefix+"§cJogador não esta no servidor.");
					return true;
				}
				
				if (jogador == p) {
					p.sendMessage(Manager.prefix+"§cVocê não pode mandar mensagem para si mesmo.");
					return true;
				}
				
				String msgprivada = Jogador.get(p).getMsgPrivada();
				String pmsgprivada = Jogador.get(jogador).getMsgPrivada();
				
				if (pmsgprivada.equalsIgnoreCase("ativado")) {
					p.sendMessage(Manager.prefix+"§cJogador desativou o recebimento de mensagem.");
					return true;
				}
				
				if (msgprivada.equalsIgnoreCase("ativado")) {
					p.sendMessage(Manager.prefix+"§cVocê precisa ativar o recebimento de mensagem.");
					return true;
				}
				
				String msg = "";
				for (int i = 1; i < args.length; i++) {
					msg = msg + args[i] + " ";
				}
				
				p.sendMessage("§8Mensagem para "+jogador.getDisplayName()+"§8: §7"+msg.replace("&", "§"));
				jogador.sendMessage("§8Mensagem de "+p.getDisplayName()+"§8: §7"+msg.replace("&", "§"));
				
				responder.put(jogador, p);
				
			}
			
			return true;
		}
		
		if (cmd.getName().equalsIgnoreCase("r")) {
			
			if (!(responder.containsKey(p))) {
				p.sendMessage(Manager.prefix+"§cVocê não tem nenhuma mensagem para responder.");
				return true;
			}
 			
			if (args.length == 0) {
				p.sendMessage("§eComando incorreto, digite §f'/r <mensagem>'§e.");
				return true;
			}
			
			Player jogador = Bukkit.getPlayer(responder.get(p).getName());
			
			if (jogador == null) {
				p.sendMessage(Manager.prefix+"§cJogador não esta no servidor.");
				return true;
			}
			
			if (jogador == p) {
				p.sendMessage(Manager.prefix+"§cVocê não pode mandar mensagem para si mesmo.");
				return true;
			}
			
			String msgprivada = Jogador.get(p).getMsgPrivada();
			String pmsgprivada = Jogador.get(jogador).getMsgPrivada();
			
			if (pmsgprivada.equalsIgnoreCase("ativado")) {
				p.sendMessage(Manager.prefix+"§cJogador desativou o recebimento de mensagem.");
				return true;
			}
			
			if (msgprivada.equalsIgnoreCase("ativado")) {
				p.sendMessage(Manager.prefix+"§cVocê precisa ativar o recebimento de mensagem.");
				return true;
			}
			
			String msg = "";
			for (int i = 1; i < args.length; i++) {
				msg = msg + args[i] + " ";
			}
			
			p.sendMessage("§8Mensagem para "+jogador.getDisplayName()+"§8: §7"+msg.replace("&", "§"));
			jogador.sendMessage("§8Mensagem de "+p.getDisplayName()+"§8: §7"+msg.replace("&", "§"));
			responder.remove(p);
			responder.put(jogador, p);
			
			return true;
		}
		
		return false;
	}

}
