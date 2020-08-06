package person.minigames.fantasy.eventos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import person.minigames.fantasy.utils.BungeeAPI;
import person.minigames.fantasy.utils.Criar;
import person.minigames.fantasy.utils.Manager;

public class Interact implements Listener {

	@EventHandler
	public void click(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			ItemStack item = p.getItemInHand();

			if (item.hasItemMeta() && item.getItemMeta().hasDisplayName()) {
				
				if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§b➜ Minigames")) {
					Inventory inv = Bukkit.createInventory(null, 3*9, "§8Selecione um Minigame");
					
					inv.setItem(11, Criar.criarL(Material.GRASS, "§eSKYWARS §f- §e§lLANÇAMENTO", 1, 0, new String[] {"", "§7Descrição do modo de jogo", ""}));
					inv.setItem(15, Criar.criarL(Material.DIAMOND_SWORD, "§eTHE BRIDGE §f- §e§lLANÇAMENTO", 1, 0, new String[] {"", "§7Descrição do modo de jogo", ""}));
					
					p.openInventory(inv);
					return;
				}
				
				if (item.getItemMeta().getDisplayName().equalsIgnoreCase("§b➜ Lobbies")) {
					
					Inventory inv = Bukkit.createInventory(null, 3*9, "§8Selecione um Lobby");
					
					inv.setItem(10, Criar.criarL(Material.NETHER_STAR, "§aLobby - 01", 1, 0, new String[] {"", "§7Online: §a0/?", "", "§eClique para conectar! "}));
					
					p.openInventory(inv);
					
				}
				
			}
		}
		
	}
	
	@EventHandler
	public void clickInventory(InventoryClickEvent e) {
		
		Player p = (Player)e.getWhoClicked();
		
		if (e.getInventory().getTitle().equalsIgnoreCase("§8Selecione um Minigame")) {
			if (e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem() == null) return;
			e.setCancelled(true);
			if (e.getSlot() == 11) {
				p.closeInventory();
				BungeeAPI.teleToServer(p, Manager.prefix+"§aConectando ao servidor de SkyWars", "LobbySky");
				return;
			}
			
			if (e.getSlot() == 15) {
				p.closeInventory();
				BungeeAPI.teleToServer(p, Manager.prefix+"§aConectando ao servidor de The Bridge", "lobbytb");
				return;
			}
			
		}
		
		if (e.getInventory().getTitle().equalsIgnoreCase("§8Selecione um Lobby")) {
			if (e.getCurrentItem().getType() == Material.AIR || e.getCurrentItem() == null) return;
			e.setCancelled(true);
			if (e.getSlot() == 10) {
				p.closeInventory();
				//BungeeAPI.teleToServer(p, Manager.prefix+"§aConectando ao "+e.getCurrentItem().getItemMeta().getDisplayName(), "LobbySky");
				return;
			}
			
		}
		
	}
	
}
