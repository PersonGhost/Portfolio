package person.divert.eventos;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.entity.LightningStrike;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import person.divert.apis.Cooldown;
import person.divert.apis.Criar;

public class Thor implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getItemInHand().getType() == Material.WOOD_AXE && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§aThor §e(ENGENHOCA)")) {
				e.setCancelled(true);
				
				if (Cooldown.Thor.containsKey(p)) {
					p.sendMessage("§e§lENGENHOCA§f: §fVocê ainda não pode utilizar sua §aengenhoca§f.");
					return;
				}
				
				p.getWorld().spawn(p.getTargetBlock((Set<Material>)null, 10).getLocation(), LightningStrike.class);
				
				p.sendMessage("§e§lENGENHOCA§f: §cVocê acabou de utilizar a engenhoca §aThor§f.");
				Cooldown.Thor.put(p, "Thor");
				Cooldown.Delay(p, "Thor", 15);
			}
		}
	}
	
	@EventHandler
	public void onClickInventory(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		
		if (e.getInventory().getName().equalsIgnoreCase("§8ENGENHOCAS - LOJA")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			e.setCancelled(true);
			if (e.getSlot() == 14) {
				p.closeInventory();
				p.getInventory().addItem(Criar.criar(Material.WOOD_AXE, "§aThor §e(ENGENHOCA)", 1, 0));
				return;
			}
		}
	}

}
