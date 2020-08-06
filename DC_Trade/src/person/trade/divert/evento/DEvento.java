package person.trade.divert.evento;

import java.util.HashMap;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import nuvemplugins.solaryeconomy.app.SolaryEconomy;
import person.trade.divert.Criar;
import person.trade.divert.Trade;
import person.trade.divert.comando.Comando;

public class DEvento extends Comando implements Listener{
	
	@EventHandler
	public void onComprar(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		
		if (e.getInventory().getTitle().contains("§8Trade")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			e.setCancelled(true);
			
			if (e.getSlot() == 20) {
				playerTrade.get(p).getInventory().addItem(playerTradeItem.get(playerTrade.get(p)));
				
				Trade.playerREMOVE(p);
				
				playerTrade.get(p).sendMessage("§a§lTRADE§f: O Comprador §ccancelou §fa compra.");
				p.sendMessage("§a§lTRADE§f: Você §ccancelou §fa compra.");
				p.closeInventory();
				return;
			}
			
			if (e.getSlot() == 24) {
				p.getInventory().addItem(playerTradeItem.get(playerTrade.get(p)));
				
				if (SolaryEconomy.economia.getMoney(p.getName()) < playerTradeValor.get(playerTrade.get(p))) {
					p.sendMessage("§a§lTRADE§f: Você não tem §cdinheiro §fsuficiente.");
					p.closeInventory();
					return;
				}
				
				SolaryEconomy.economia.takeMoney(p.getName(), playerTradeValor.get(playerTrade.get(p)));
				SolaryEconomy.economia.addMoney(playerTrade.get(p).getName(), playerTradeValor.get(playerTrade.get(p)));
				
				playerTrade.get(p).sendMessage("§a§lTRADE§f: Sua venda foi realizada com sucesso.");
				p.sendMessage("§a§lTRADE§f: Você realizou uma compra com sucesso.");

				Trade.playerREMOVE(p);
				
				p.closeInventory();
				return;
			}
		}
		
		
		
		if (e.getInventory().getTitle().contains("§8TROCA")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			if (e.getSlot() == 4 || e.getSlot() == 13 || e.getSlot() == 22 || e.getSlot() == 31 || e.getSlot() == 40 || e.getSlot() == 49 || e.getSlot() == 45 || e.getSlot() == 53) {
				e.setCancelled(true);
			}
			
			if (e.getSlot() == 0 && Trade.slot.get(p) == 0) {
				if (Trade.condicao.get(p).contains("§cAguardando...")) {
					e.getInventory().setItem(0, Criar.criar(Material.STAINED_GLASS_PANE, "§aAceito", 1, 5));
					Trade.condicao.put(p, "§aAceito");
				} else if (Trade.condicao.get(p).contains("§aAceito")) {
					e.getInventory().setItem(0, Criar.criar(Material.STAINED_GLASS_PANE, "§cNegado", 1, 14));
					Trade.condicao.put(p, "§cNegado");
				} else if (Trade.condicao.get(p).contains("§cNegado")) {
					e.getInventory().setItem(0, Criar.criar(Material.STAINED_GLASS_PANE, "§cAguardando...", 1, 7));
					Trade.condicao.put(p, "§cAguardando...");
				}
			}
			
			if (e.getSlot() == 8 && Trade.slot.get(p) == 8) {
				if (Trade.condicao.get(p).contains("§cAguardando...")) {
					e.getInventory().setItem(8, Criar.criar(Material.STAINED_GLASS_PANE, "§aAceito", 1, 5));
					Trade.condicao.put(p, "§aAceito");
				} else if (Trade.condicao.get(p).contains("§aAceito")) {
					e.getInventory().setItem(8, Criar.criar(Material.STAINED_GLASS_PANE, "§cNegado", 1, 14));
					Trade.condicao.put(p, "§cNegado");
				} else if (Trade.condicao.get(p).contains("§cNegado")) {
					e.getInventory().setItem(8, Criar.criar(Material.STAINED_GLASS_PANE, "§cAguardando...", 1, 7));
					Trade.condicao.put(p, "§cAguardando...");
				}
			}
			
		}
	}
	
	public static HashMap<Player, List<ItemStack>> itensTroca = new HashMap<>();

}
