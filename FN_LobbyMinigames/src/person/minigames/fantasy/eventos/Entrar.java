package person.minigames.fantasy.eventos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import person.minigames.fantasy.Config;
import person.minigames.fantasy.Main;
import person.minigames.fantasy.comandos.Spawn;
import person.minigames.fantasy.comandos.Vanish;
import person.minigames.fantasy.perfil.Jogador;
import person.minigames.fantasy.utils.Criar;
import person.minigames.fantasy.utils.Manager;
import person.minigames.fantasy.utils.ReflectionTab;

public class Entrar implements Listener {

	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		e.setJoinMessage(null);
		
		String cima = "\n§5§lFANTASY §f- §e§lSKYWARS\n§ejogar.fantasynetwork.com.br\n\n§fDivirta-se com seus amigos neste vasto mundo de fantasias!\n";
		String baixo = "\n\n§eSite: §fwww.fantasynetwork.com.br\n§eTwitter: §f@FantasyServer_\n§eDiscord: §ffantasynetwork.com.br/discord \n\n §aAdquira vantagens VIP em nosso site: §floja.fantasynetwork.com.br ";
		ReflectionTab.sendTabColor(p, cima, baixo);
		
		Spawn.teleportar(p);
		Jogador.create(p);
		ItemReciver(p);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				Jogador.get(p).loadPlayer();
			}
		}, 15L);
		
		Vanish.checkVanishVisibility(p);
		Jogador.get(p).setLastLoginInfo(Manager.getTime(p.getLastPlayed()));

		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				if (p.isOnline()) {
					p.setLevel(-100);
					p.setFoodLevel(20);
					p.setMaxHealth(20);
					p.setHealth(20);
				}
			}
		}, 20 * 1);	
		
	}
	
	public static void ItemReciver(Player p) {
		Config config = new Config("config.yml");
		p.getInventory().clear();
		if (config.getString("Game.Mode").equalsIgnoreCase("lobby")) {
			
			p.getInventory().setItem(0, Criar.criar(Material.COMPASS, "§b➜ Minigames", 1, 0));
			p.getInventory().setItem(1, Criar.criarSkull("§b➜ Perfil", new String[] {}, p.getName(), 1));
			if (Jogador.get(p).getConfigPerfil().split(" : ")[0].equalsIgnoreCase("ativado")) {
				p.getInventory().setItem(7, Criar.criarL(Material.INK_SACK, "§f➜ Jogadores: §aAtivado", 1, 10, new String[] {}));
			} else {
				p.getInventory().setItem(7, Criar.criarL(Material.INK_SACK, "§f➜ Jogadores: §cDesativado", 1, 8, new String[] {}));
			}
			p.getInventory().setItem(8, Criar.criar(Material.NETHER_STAR, "§b➜ Lobbies", 1, 0));
			
			p.updateInventory();
		} else if (config.getString("Game.Mode").equalsIgnoreCase("minigame")) {

			p.getInventory().setItem(0, Criar.criarSkull("§b➜ Perfil", new String[] {}, p.getName(), 1));
			if (Jogador.get(p).getConfigPerfil().split(" : ")[0].equalsIgnoreCase("ativado")) {
				p.getInventory().setItem(7, Criar.criarL(Material.INK_SACK, "§f➜ Jogadores: §aAtivado", 1, 10, new String[] {}));
			} else {
				p.getInventory().setItem(7, Criar.criarL(Material.INK_SACK, "§f➜ Jogadores: §cDesativado", 1, 8, new String[] {}));
			}
			p.getInventory().setItem(8, Criar.criar(Material.NETHER_STAR, "§b➜ Lobbies", 1, 0));
			
			p.updateInventory();
		}
		
		
	}
	
}
