package person.minigames.fantasy.perfil;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import person.minigames.fantasy.utils.Criar;

public class Perfil implements Listener {

	@EventHandler
	public void open(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		ItemStack item = p.getItemInHand();
		
		if (item.hasItemMeta()) {
			if (item.getItemMeta().hasDisplayName()) {
				if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§b➜ Perfil")) {
					e.setCancelled(true);
					openInventory(p);
					return;
				}
				
				if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§f➜ Jogadores: §aAtivado")) {
					p.getInventory().setItem(7, Criar.criarL(Material.INK_SACK, "§f➜ Jogadores: §cDesativado", 1, 8, new String[] {}));
					Jogador.get(p).setVisibilidade("desativado");
					jogadoresOff(p);
				} else if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§f➜ Jogadores: §cDesativado")) {
					p.getInventory().setItem(7, Criar.criarL(Material.INK_SACK, "§f➜ Jogadores: §aAtivado", 1, 10, new String[] {}));
					Jogador.get(p).setVisibilidade("ativado");
					jogadoresOff(p);
				}
			}
		}
		
	}
	
	public void jogadoresOff(Player p) {
		String visibilidade = Jogador.get(p).getConfigPerfil().split(" : ")[0];
		
		if (visibilidade.equalsIgnoreCase("ativado")) {
			for (Player s : Bukkit.getOnlinePlayers()) {
				if (!(s.hasPermission("lobby.visibilidade"))) {
					p.hidePlayer(s);
				}
			}
		}
		
		for (Player s : Bukkit.getOnlinePlayers()) {
			if (visibilidade.equalsIgnoreCase("ativado")) {
				if (!(p.hasPermission("lobby.visibilidade"))) {
					s.hidePlayer(p);
				}
			}
		}
		
	}
	
	public void openInventory(Player p) {
		
		Inventory inv = Bukkit.createInventory(null, 3*9, "§8Seu Perfil");
		
		inv.setItem(11, Criar.criarL(Material.PAPER, "§bInformações Pessoais", 1, 0, new String[] {"§fNome: §7"+p.getName()+" ", "§fCargo: §7"+p.getDisplayName().replace(p.getName(), ""), "", "", "§fPrimeiro Login: §e"+Jogador.get(p).getLognInfo().split(" : ")[0]+" ", "§fÚltimo Login: §e"+Jogador.get(p).getLognInfo().split(" : ")[1]+" ", ""}));
		inv.setItem(13, Criar.criarL(Material.REDSTONE_COMPARATOR, "§bConfigurações em Jogo", 1, 0, new String[] {"§7Configure as preferências pessoas ", "§7disponibilizadas pela nossa rede. "}));
		inv.setItem(15, Criar.criarL(Material.DIAMOND, "§bAdquira seu VIP", 1, 0, new String[] {"", "§7Você ainda não é VIP em nossa rede ? ", "§7além de ajudar nosso servidor, você ", "§7receberá previlégios em nossos mingiames. ", "", "§bAcesse nossa loja: §eloja.fantasynetwork.com.br "}));
	
		p.openInventory(inv);
		
	}

	@EventHandler
	public void inventoryClick(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		
		if (e.getInventory().getName().equalsIgnoreCase("§8Seu Perfil")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			e.setCancelled(true);
			if (e.getSlot() == 11) {
				return;
			}
			
			if (e.getSlot() == 13) {
				p.closeInventory();
				InventoryOptions(p);
				return;
			}
			
			if (e.getSlot() == 15) {
				return;
			}
			
			return;
		}
		
	}

	
	@EventHandler
	public void join(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		jogadoresOff(p);
		
		if (Jogador.get(p).getConfigPerfil().split(" : ")[1].equalsIgnoreCase("ativado")) {
			p.setFlying(true);
			p.setAllowFlight(true);
		} else {
			p.setFlying(false);
			if (!(p.hasPermission("lobby.fly"))) {
				p.setAllowFlight(false);
			}
		}
		
	}
	
	public static void InventoryOptions(Player p) {
		
		Inventory inv = Bukkit.createInventory(p, 9*6,"§8Opções");
		
		String visibilidade = Jogador.get(p).getConfigPerfil().split(" : ")[0];
		String voo = Jogador.get(p).getConfigPerfil().split(" : ")[1];
		String msgprivada = Jogador.get(p).getConfigPerfil().split(" : ")[2];
		
		inv.setItem(4, Criar.criarL(Material.NETHER_STAR, "§b► Opções", 1, 0, new String[]{"","§7Escolha As Opções."}));
		
		inv.setItem(21, Criar.criarSkull("§b► Visibilidade", new String[] {"", "§7Ver outros jogadores nos lobbies."}, p.getName(), 1));
		if (visibilidade.equalsIgnoreCase("desativado")) {
			inv.setItem(30, Criar.criarL(Material.INK_SACK, "§c► Visibilidade - Desativado", 1, 8, new String[]{"","§7Clique Para Alterar."}));
		} else {
			inv.setItem(30, Criar.criarL(Material.INK_SACK, "§a► Visibilidade - Ativado", 1, 10, new String[]{"","§7Clique Para Alterar."}));
		}
		
		inv.setItem(22, Criar.criarL(Material.FEATHER, "§b► Voô", 1, 0, new String[] {"", "§7Habilitar automaticamente o voô nos lobbies."}));
		if (voo.equalsIgnoreCase("desativado")) {
			inv.setItem(31, Criar.criarL(Material.INK_SACK, "§c► Voô - Desativado", 1, 8, new String[]{"","§7Clique Para Alterar."}));
		} else {
			inv.setItem(31, Criar.criarL(Material.INK_SACK, "§a► Voô - Ativado", 1, 10, new String[]{"","§7Clique Para Alterar."}));
		}
		
		inv.setItem(23, Criar.criarL(Material.SIGN, "§b► Mensagens privadas", 1, 0, new String[] {"", "§7Receber mensagens privadas de outros pes."}));
		if (msgprivada.equalsIgnoreCase("desativado")) {
			inv.setItem(32, Criar.criarL(Material.INK_SACK, "§c► Mensagens privadas - Desativado", 1, 8, new String[]{"","§7Clique Para Alterar."}));
		} else {
			inv.setItem(32, Criar.criarL(Material.INK_SACK, "§a► Mensagens privadas - Ativado", 1, 10, new String[]{"","§7Clique Para Alterar."}));
		}
		
	 	p.openInventory(inv);	
		
	}
	
	@EventHandler
	public void onInventory(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		
		String visibilidade = Jogador.get(p).getConfigPerfil().split(" : ")[0];
		String voo = Jogador.get(p).getConfigPerfil().split(" : ")[1];
		String msgprivada = Jogador.get(p).getConfigPerfil().split(" : ")[2];
		
		if (e.getInventory().getTitle().equalsIgnoreCase("§8Opções")) {
			e.setCancelled(true);
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			
			if (e.getSlot() == 30 || e.getSlot() == 21) {
				p.playSound(p.getLocation(), Sound.CLICK, 2.0F, 2.0F);
				if (visibilidade.equalsIgnoreCase("ativado")) {
					Jogador.get(p).setVisibilidade("desativado");
					e.setCurrentItem(Criar.criarL(Material.INK_SACK, "§c► Visibilidade - Desativado", 1, 8, new String[]{"","§7Clique Para Alterar."}));
					p.getInventory().setItem(7, Criar.criarL(Material.INK_SACK, "§f➜ Jogadores: §cDesativado", 1, 8, new String[] {}));
					jogadoresOff(p);
					p.updateInventory();
				} else {
					Jogador.get(p).setVisibilidade("ativado");
					e.setCurrentItem(Criar.criarL(Material.INK_SACK, "§a► Visibilidade - Ativado", 1, 10, new String[]{"","§7Clique Para Alterar."}));
					p.getInventory().setItem(7, Criar.criarL(Material.INK_SACK, "§f➜ Jogadores: §aAtivado", 1, 10, new String[] {}));
					jogadoresOff(p);
					p.updateInventory();
				}
			}
			
			if (e.getSlot() == 31 || e.getSlot() == 22) {
				p.playSound(p.getLocation(), Sound.CLICK, 2.0F, 2.0F);
				if (voo.equalsIgnoreCase("ativado")) {
					Jogador.get(p).setVoo("desativado");
					e.setCurrentItem(Criar.criarL(Material.INK_SACK, "§c► Voô - Desativado", 1, 8, new String[]{"","§7Clique Para Alterar."}));
					p.updateInventory();
				} else {
					Jogador.get(p).setVoo("ativado");
					e.setCurrentItem(Criar.criarL(Material.INK_SACK, "§a► Voô - Ativado", 1, 10, new String[]{"","§7Clique Para Alterar."}));
					p.updateInventory();
				}
			}

			if (e.getSlot() == 32 || e.getSlot() == 23) {
				p.playSound(p.getLocation(), Sound.CLICK, 2.0F, 2.0F);
				if (msgprivada.equalsIgnoreCase("ativado")) {
					Jogador.get(p).setMsgPrivada("desativado");
					e.setCurrentItem(Criar.criarL(Material.INK_SACK, "§c► Mensagens privadas - Desativado", 1, 8, new String[]{"","§7Clique Para Alterar."}));
					p.updateInventory();
				} else {
					Jogador.get(p).setMsgPrivada("ativado");
					e.setCurrentItem(Criar.criarL(Material.INK_SACK, "§a► Mensagens privadas - Ativado", 1, 10, new String[]{"","§7Clique Para Alterar."}));
					p.updateInventory();
				}
			}
			
		}
	}
	
}
