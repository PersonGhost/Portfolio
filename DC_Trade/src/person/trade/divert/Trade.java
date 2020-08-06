package person.trade.divert;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import person.trade.divert.comando.Comando;

public class Trade extends Comando {
	
	public static void playerPUT(Player p, Player jogador, int valor, ItemStack item) {
		playerTrade.put(jogador, p);
		playerTradeValor.put(p, valor);
		playerTradeItem.put(p, item);
		playerTradeCom.put(p, jogador);
		playerEstaEmTrade.add(p);
		playerEstaEmTrade.add(jogador);
		playerTradeVendedor.add(p);
	}
	
	public static void playerREMOVE(Player p) {
		playerEstaEmTrade.remove(p);
		playerEstaEmTrade.remove(playerTrade.get(p));
		playerTradeItem.remove(playerTrade.get(p));
		playerTradeCom.remove(p);
		playerTradeValor.remove(playerTrade.get(p));
		playerTradeVendedor.remove(playerTrade.get(p));
		playerTrade.remove(p);
	}
	
	public static void playerRemoveVenda(Player p) {
		if (playerTrade.containsValue(p)) {
			playerEstaEmTrade.remove(p);
			playerEstaEmTrade.remove(playerTrade.get(p));
			playerTradeItem.remove(playerTrade.get(p));
			playerTradeCom.remove(p);
			playerTradeValor.remove(playerTrade.get(p));
			playerTradeVendedor.remove(playerTrade.get(p));
			playerTrade.remove(p);
		}
	}
	
	public static HashMap<Player, String> condicao = new HashMap<>();
	public static HashMap<Player, Integer> slot = new HashMap<>();
	
	public static void onInventoryTroca(Player p, Player jogador) {
		condicao.put(p, "§cAguardando...");
		condicao.put(jogador, "§cAguardando...");
		Inventory inv = Bukkit.createInventory(p, 6*9, "§8TROCA");
		if (condicao.get(p).contains("§cAguardando...")) {
			inv.setItem(0, Criar.criar(Material.STAINED_GLASS_PANE, "§cAguardando...", 1, 7));
		} else if (condicao.get(p).contains("§aAceito")) {
			inv.setItem(0, Criar.criar(Material.STAINED_GLASS_PANE, "§aAceito", 1, 5));
		} else if (condicao.get(p).contains("§cNegado")) {
			inv.setItem(0, Criar.criar(Material.STAINED_GLASS_PANE, "§cNegado", 1, 14));
		}
		
		slot.put(p, 0);
		inv.setItem(45, Criar.skullL(p.getName(), "§aInformações", 1, new String[] {"", "§eInformações do Jogador ", "", "§eNick §7» §f"+p.getName()+" ", "§eDinheiro §7» §f0 ", "§eCondição §7» "+condicao.get(p)+" ", ""}));
		
		if (condicao.get(jogador).contains("§cAguardando...")) {
			inv.setItem(8, Criar.criar(Material.STAINED_GLASS_PANE, "§cAguardando...", 1, 7));
		} else if (condicao.get(jogador).contains("§aAceito")) {
			inv.setItem(8, Criar.criar(Material.STAINED_GLASS_PANE, "§aAceito", 1, 5));
		} else if (condicao.get(jogador).contains("§cNegado")) {
			inv.setItem(8, Criar.criar(Material.STAINED_GLASS_PANE, "§cNegado", 1, 14));
		}
		
		slot.put(jogador, 8);
		inv.setItem(53, Criar.skullL(jogador.getName(), "§aInformações", 1, new String[] {"", "§eInformações do Jogador ", "", "§eNick §7» §f"+jogador.getName()+" ", "§eDinheiro §7» §f0 ", "§eCondição §7» "+condicao.get(jogador)+" ", ""}));
		
		inv.setItem(4, Criar.criar(Material.STAINED_GLASS_PANE, "", 1, 5));
		inv.setItem(13, Criar.criar(Material.STAINED_GLASS_PANE, "", 1, 5));
		inv.setItem(22, Criar.criar(Material.STAINED_GLASS_PANE, "", 1, 5));
		inv.setItem(31, Criar.criar(Material.STAINED_GLASS_PANE, "", 1, 5));
		inv.setItem(40, Criar.criar(Material.STAINED_GLASS_PANE, "", 1, 5));
		inv.setItem(49, Criar.criar(Material.STAINED_GLASS_PANE, "", 1, 5));
		
		p.openInventory(inv);
		jogador.openInventory(inv);
	}

}
