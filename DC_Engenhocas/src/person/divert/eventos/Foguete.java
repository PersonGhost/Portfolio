package person.divert.eventos;

import org.bukkit.Material;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import person.divert.apis.Cooldown;
import person.divert.apis.Criar;

public class Foguete implements Listener {
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		
		
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getItemInHand().getType() == Material.FIREWORK && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§aFoguete §e(ENGENHOCA)")) { 
				e.setCancelled(true);
				if (Cooldown.Foguete.containsKey(p)) {
					p.sendMessage("§e§lENGENHOCA§f: §fVocê ainda não pode utilizar sua §aengenhoca§f.");
					return;
				}
				
				Firework fire = p.getWorld().spawn(p.getLocation().add(0, 3 , 0), Firework.class);
				fire.setPassenger(p);
				
				p.sendMessage("§e§lENGENHOCA§f: §cVocê acabou de utilizar a engenhoca de §aFoguete§f.");
				Cooldown.Foguete.put(p, "Foguete");
				Cooldown.Delay(p, "Foguete", 15);
			}
		}
	}
	
	@EventHandler
	public void onClickInventory(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		
		if (e.getInventory().getName().equalsIgnoreCase("§8ENGENHOCAS - LOJA")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			e.setCancelled(true);
			if (e.getSlot() == 11) {
				p.closeInventory();
				p.getInventory().addItem(Criar.criar(Material.FIREWORK, "§aFoguete §e(ENGENHOCA)", 1, 0));
				return;
			}
		}
	}

}
