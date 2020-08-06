package person.divert.eventos;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import person.divert.Divert;
import person.divert.apis.Cooldown;
import person.divert.apis.Criar;

public class Armadilha implements Listener {
	
	private List<Location> allBlocks = new ArrayList<Location>();
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (p.getItemInHand().getType() == Material.TRIPWIRE_HOOK && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§aArmadilha §e(ENGENHOCA)")) {
				e.setCancelled(true);
				Block b = e.getClickedBlock();
				Location loc = b.getLocation();
				
				if (Cooldown.Armadilha.containsKey(p)) {
					p.sendMessage("§e§lENGENHOCA§f: §fVocê ainda não pode utilizar sua §aengenhoca§f.");
				}
				
				if (b.getLocation().add(1, 1, 0).getBlock().getType() != Material.AIR || b.getLocation().add(-1, 1, 0).getBlock().getType() != Material.AIR ||
						b.getLocation().add(0, 1, 1).getBlock().getType() != Material.AIR || b.getLocation().add(0, 1, -1).getBlock().getType() != Material.AIR ||
						
						b.getLocation().add(1, 1, -1).getBlock().getType() != Material.AIR || b.getLocation().add(-1, 1, 1).getBlock().getType() != Material.AIR ||
						b.getLocation().add(-1, 1, 1).getBlock().getType() != Material.AIR || b.getLocation().add(-1, 1, -1).getBlock().getType() != Material.AIR) {
					p.sendMessage("§e§lENGENHOCA§f: §fVocê precisa de uma área de §c3x3§f para a armadilha.");
					return;
				}
				
				
				if (loc.add(1, 1, 0).getBlock().getType() == Material.AIR) {
					loc = b.getLocation();
					loc.add(1, 1, 0).getBlock().setType(Material.WEB); allBlocks.add(loc); loc = b.getLocation();
					
				}
				
				if (loc.add(2, 1, 0).getBlock().getType() == Material.AIR) {
					loc = b.getLocation();
					loc.add(2, 1, 0).getBlock().setType(Material.WEB); allBlocks.add(loc); loc = b.getLocation();
				}
				
				if (loc.add(0, 1, 0).getBlock().getType() == Material.AIR) {
					loc = b.getLocation();
					loc.add(0, 1, 0).getBlock().setType(Material.WEB); allBlocks.add(loc); loc = b.getLocation();
				}
				
				if (loc.add(0, 1, 1).getBlock().getType() == Material.AIR) {
					loc = b.getLocation();
					loc.add(0, 1, 1).getBlock().setType(Material.WEB); allBlocks.add(loc); loc = b.getLocation();
				}
				
				if (loc.add(0, 1, 2).getBlock().getType() == Material.AIR) {
					loc = b.getLocation();
					loc.add(0, 1, 2).getBlock().setType(Material.WEB); allBlocks.add(loc); loc = b.getLocation();
				}
				
				if (loc.add(1, 1, 1).getBlock().getType() == Material.AIR) {
					loc = b.getLocation();
					loc.add(1, 1, 1).getBlock().setType(Material.WEB); allBlocks.add(loc); loc = b.getLocation();
				}

				if (loc.add(1, 1, 2).getBlock().getType() == Material.AIR) {
					loc = b.getLocation();
					loc.add(1, 1, 2).getBlock().setType(Material.WEB); allBlocks.add(loc); loc = b.getLocation();
				}
				
				if (loc.add(2, 1, 1).getBlock().getType() == Material.AIR) {
					loc = b.getLocation();
					loc.add(2, 1, 1).getBlock().setType(Material.WEB); allBlocks.add(loc); loc = b.getLocation();
				}
				
				if (loc.add(2, 1, 2).getBlock().getType() == Material.AIR) {
					loc = b.getLocation();
					loc.add(2, 1, 2).getBlock().setType(Material.WEB); allBlocks.add(loc); loc = b.getLocation();
				}
				
				p.sendMessage("§e§lENGENHOCA§f: §cVocê acabou de utilizar a engenhoca de §aArmadilha§f.");
				Cooldown.Armadilha.put(p, "Armadilha");
				Cooldown.Delay(p, "Armadilha", 15);
				
				Bukkit.getScheduler().scheduleSyncDelayedTask(Divert.getInstance(), new Runnable() {
					
					@Override
					public void run() {
						
						removerStrings(p, b);
						p.sendMessage("§e§lENGENHOCA§f: §fSua §aArmadilha§f foi removida.");
					}
				}, 5*20);
			}
		}
	}
	
	@EventHandler
	public void onClickInventory(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		
		if (e.getInventory().getName().equalsIgnoreCase("§8ENGENHOCAS - LOJA")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			e.setCancelled(true);
			if (e.getSlot() == 12) {
				p.closeInventory();
				p.getInventory().addItem(Criar.criar(Material.TRIPWIRE_HOOK, "§aArmadilha §e(ENGENHOCA)", 1, 0));
				return;
			}
		}
	}
	
	public void removerStrings(Player p, Block b) {
		Location locs = b.getLocation();
			
		if (allBlocks.contains(locs) && locs.getBlock().getType() == Material.WEB) {
			locs.add(1, 1, 0).getBlock().setType(Material.AIR);
			allBlocks.remove(locs);
			locs = b.getLocation();
			p.sendMessage("§e§lENGENHOCA§f: §fSua §aArmadilha§f foi removida.");
		}
		if (allBlocks.contains(locs) && locs.getBlock().getType() == Material.WEB) {
			locs.add(2, 1, 0).getBlock().setType(Material.AIR); 
			allBlocks.remove(locs);
			locs = b.getLocation();
			p.sendMessage("§e§lENGENHOCA§f: §fSua §aArmadilha§f foi removida.");
		}
		if (allBlocks.contains(locs) && locs.getBlock().getType() == Material.WEB) {
			locs.add(0, 1, 0).getBlock().setType(Material.AIR); 
			allBlocks.remove(locs);
			locs = b.getLocation();
			p.sendMessage("§e§lENGENHOCA§f: §fSua §aArmadilha§f foi removida.");
		}
		if (allBlocks.contains(locs) && locs.getBlock().getType() == Material.WEB) {
			locs.add(0, 1, 1).getBlock().setType(Material.AIR); 
			allBlocks.remove(locs);
			locs = b.getLocation();
			p.sendMessage("§e§lENGENHOCA§f: §fSua §aArmadilha§f foi removida.");
		}
		if (allBlocks.contains(locs) && locs.getBlock().getType() == Material.WEB) {
			locs.add(0, 1, 2).getBlock().setType(Material.AIR); 
			allBlocks.remove(locs);
			locs = b.getLocation();
			p.sendMessage("§e§lENGENHOCA§f: §fSua §aArmadilha§f foi removida.");
		}
		if (allBlocks.contains(locs) && locs.getBlock().getType() == Material.WEB) {
			locs.add(1, 1, 1).getBlock().setType(Material.AIR); 
			allBlocks.remove(locs);
			locs = b.getLocation();
			p.sendMessage("§e§lENGENHOCA§f: §fSua §aArmadilha§f foi removida.");
		}
		if (allBlocks.contains(locs) && locs.getBlock().getType() == Material.WEB) {
			locs.add(1, 1, 2).getBlock().setType(Material.AIR); 
			allBlocks.remove(locs);
			locs = b.getLocation();
			p.sendMessage("§e§lENGENHOCA§f: §fSua §aArmadilha§f foi removida.");
		}
		if (allBlocks.contains(locs) && locs.getBlock().getType() == Material.WEB) {
			locs.add(2, 1, 1).getBlock().setType(Material.AIR); 
			allBlocks.remove(locs);
			locs = b.getLocation();
			p.sendMessage("§e§lENGENHOCA§f: §fSua §aArmadilha§f foi removida.");
		}
		if (allBlocks.contains(locs) && locs.getBlock().getType() == Material.WEB) {
			locs.add(2, 1, 2).getBlock().setType(Material.AIR); 
			allBlocks.remove(locs);
			locs = b.getLocation();
			p.sendMessage("§e§lENGENHOCA§f: §fSua §aArmadilha§f foi removida.");
		}
			
	}

}
