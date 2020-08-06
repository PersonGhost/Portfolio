package person.divert.eventos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import person.divert.Divert;
import person.divert.apis.Cooldown;
import person.divert.apis.Criar;

public class RestringirEfeitos implements Listener{
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getItemInHand().getType() == Material.ANVIL && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§aRestringir Efeitos §e(ENGENHOCA)")) {
				e.setCancelled(true);
				
				if (Cooldown.Restringir.containsKey(p)) {
					p.sendMessage("§e§lENGENHOCA§f: §fVocê ainda não pode utilizar sua §aengenhoca§f.");
					return;
				}
				
				if (restringir.contains(p)) {
					p.sendMessage("§e§lENGENHOCA§f: §cVocê já contem um cancelamento de efeitos em ação.");
					return;
				}
				
				for (int i = 0; i < 5; i++) {
					p.sendMessage("");
				}
				
				p.sendMessage("§e§lENGENHOCA§f: §aDrope o §fitem §aque você deseja retirar os efeitos. \n§e§lENGENHOCA§f: Você tem §c10 Segundos para fazer isto. ");
				restringir.add(p);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Divert.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						if (restringir.contains(p)) {
							restringir.remove(p);
							p.sendMessage("§e§lENGENHOCA§f: A Remoção de efeitos foi cancelada por demora.");
						}
					}
				}, 10*20);
			}
		}
	}
	
	@EventHandler
	public void onClickInventory(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		
		if (e.getInventory().getName().equalsIgnoreCase("§8ENGENHOCAS - LOJA")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			e.setCancelled(true);
			if (e.getSlot() == 13) {
				p.closeInventory();
				p.getInventory().addItem(Criar.criar(Material.ANVIL, "§aRestringir Efeitos §e(ENGENHOCA)", 1, 0));
				return;
			}
		}
	}
	
	private ArrayList<Player> restringir = new ArrayList<>();
	
	@EventHandler
	public void onDropar(PlayerDropItemEvent e) {
		Player p = e.getPlayer();
		
		if (restringir.contains(p)) {
			Material item = e.getItemDrop().getItemStack().getType();
			p.getInventory().addItem(Criar.criar(item, e.getItemDrop().getItemStack().getItemMeta().getDisplayName(), e.getItemDrop().getItemStack().getAmount(), 0));
			p.sendMessage("");
			p.sendMessage("§e§lENGENHOCA§f: §aTodos §fefeitos §aforam removidos do seu item.");
			p.sendMessage("");
			e.getItemDrop().remove();
			restringir.remove(p);
			Cooldown.Restringir.put(p, "Restringir");
			p.sendMessage("§e§lENGENHOCA§f: §cVocê acabou de utilizar a engenhoca de §aRestringir Efeitos§f.");
			Cooldown.Restringir.put(p, "Restringir");
			Cooldown.Delay(p, "Restringir", 15);
		}
	}

}
