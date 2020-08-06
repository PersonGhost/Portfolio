package person.divert.eventos;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import person.divert.apis.Cooldown;
import person.divert.apis.Criar;

public class Cactus implements Listener {
	
	@EventHandler
	public void onClickEngenhoca(PlayerInteractEvent e) {
		Player p = e.getPlayer();
		
		if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if (e.getClickedBlock().getType() == Material.GRASS || e.getClickedBlock().getType() == Material.DIRT || e.getClickedBlock().getType() == Material.BEDROCK) {
				if (p.getItemInHand().getType() == Material.CACTUS && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§aCactos §e(ENGENHOCA)")) {
					e.setCancelled(true);
					Block b = e.getClickedBlock();
					
					if (Cooldown.Cacto.containsKey(p)) {
						p.sendMessage("§e§lENGENHOCA§f: §fVocê ainda não pode utilizar sua §aengenhoca§f.");
						return;
					}
					
					for (int i = 1; i < 4; i++) {
						if (b.getLocation().add(1, i, 0).getBlock().getType() != Material.AIR || b.getLocation().add(-1, i, 0).getBlock().getType() != Material.AIR ||
								b.getLocation().add(0, i, 1).getBlock().getType() != Material.AIR || b.getLocation().add(0, i, -1).getBlock().getType() != Material.AIR ||
								
								b.getLocation().add(1, i, -1).getBlock().getType() != Material.AIR || b.getLocation().add(-1, i, 1).getBlock().getType() != Material.AIR ||
								b.getLocation().add(-1, i, 1).getBlock().getType() != Material.AIR || b.getLocation().add(-1, i, -1).getBlock().getType() != Material.AIR) {
							p.sendMessage("§e§lENGENHOCA§f: §fVocê precisa de uma área de §c3x4§f para construir.");
							return;
						}
					}
					
					Location loc = b.getLocation();
					
					for (@SuppressWarnings("unused") ItemStack i : p.getInventory().getContents()) {
						if (!(p.getInventory().contains(Material.COBBLESTONE))) {
							p.sendMessage("§e§lENGENHOCA§f: Você precisa ter §c4 §fou mais Pedregulhos.");
							return;
						}
						
						if (!(p.getInventory().contains(Material.SAND))) {
							p.sendMessage("§e§lENGENHOCA§f: Você precisa ter §c4 §fou mais Areias.");
							return;
						}
						
						if (!(p.getInventory().contains(Material.CACTUS))) {
							p.sendMessage("§e§lENGENHOCA§f: Você precisa ter §c4 §fou mais Cactos.");
							return;
						}
						
						if (!(p.getInventory().contains(Material.FENCE))) {
							p.sendMessage("§e§lENGENHOCA§f: Você precisa ter §c1 §fou mais Cercas.");
							return;
						}
					}
					
					if (e.getClickedBlock().getType() == Material.BEDROCK) {
						loc.add(0, 5, 0).getBlock().setType(Material.FENCE);
						loc = b.getLocation();
						
						//FRENTE COSTA
						loc.add(1, 2, 0).getBlock().setType(Material.COBBLESTONE);
						loc.add(0, 1, 0).getBlock().setType(Material.SAND);
						loc.add(0, 1, 0).getBlock().setType(Material.CACTUS);
						loc = b.getLocation();
						
						loc.add(-1, 2, 0).getBlock().setType(Material.COBBLESTONE);
						loc.add(0, 1, 0).getBlock().setType(Material.SAND);
						loc.add(0, 1, 0).getBlock().setType(Material.CACTUS);
						loc = b.getLocation();
						
						//ESQUERDA DIREITA
						loc.add(0, 2, 1).getBlock().setType(Material.COBBLESTONE);
						loc.add(0, 1, 0).getBlock().setType(Material.SAND);
						loc.add(0, 1, 0).getBlock().setType(Material.CACTUS);
						loc = b.getLocation();
						
						loc.add(0, 2, -1).getBlock().setType(Material.COBBLESTONE);
						loc.add(0, 1, 0).getBlock().setType(Material.SAND);
						loc.add(0, 1, 0).getBlock().setType(Material.CACTUS);
						loc = b.getLocation();
						
						for (ItemStack i : p.getInventory()) {
							if (p.getInventory().contains(Material.COBBLESTONE) && i.getAmount() >= 4) {
								i.setAmount(i.getAmount() - 4);
							}
							
							if (i.getType() == Material.SAND && i.getAmount() >= 4) {
								i.setAmount(i.getAmount() - 4);
							}
							
							if (i.getType() == Material.CACTUS && i.getAmount() >= 4) {
								i.setAmount(i.getAmount() - 4);
							}
							
							if (i.getType() == Material.FENCE && i.getAmount() >= 4) {
								i.setAmount(i.getAmount() - 1);
							}
						}
					} else {
						loc.add(0, 4, 0).getBlock().setType(Material.FENCE);
						loc = b.getLocation();
						
						//FRENTE COSTA
						loc.add(1, 1, 0).getBlock().setType(Material.COBBLESTONE);
						loc.add(0, 1, 0).getBlock().setType(Material.SAND);
						loc.add(0, 1, 0).getBlock().setType(Material.CACTUS);
						loc = b.getLocation();
						
						loc.add(-1, 1, 0).getBlock().setType(Material.COBBLESTONE);
						loc.add(0, 1, 0).getBlock().setType(Material.SAND);
						loc.add(0, 1, 0).getBlock().setType(Material.CACTUS);
						loc = b.getLocation();
						
						//ESQUERDA DIREITA
						loc.add(0, 1, 1).getBlock().setType(Material.COBBLESTONE);
						loc.add(0, 1, 0).getBlock().setType(Material.SAND);
						loc.add(0, 1, 0).getBlock().setType(Material.CACTUS);
						loc = b.getLocation();
						
						loc.add(0, 1, -1).getBlock().setType(Material.COBBLESTONE);
						loc.add(0, 1, 0).getBlock().setType(Material.SAND);
						loc.add(0, 1, 0).getBlock().setType(Material.CACTUS);
						loc = b.getLocation();
						
						
						for (ItemStack i : p.getInventory()) {
							if (i.getType() == Material.COBBLESTONE && i.getAmount() >= 4) {
								i.setAmount(i.getAmount() - 4);
							}
							
							if (i.getType() == Material.SAND && i.getAmount() >= 4) {
								i.setAmount(i.getAmount() - 4);
							}
							
							if (i.getType() == Material.CACTUS && i.getAmount() >= 4) {
								i.setAmount(i.getAmount() - 4);
							}
							
							if (i.getType() == Material.FENCE && i.getAmount() >= 4) {
								i.setAmount(i.getAmount() - 1);
							}
						}
					}
					
					p.sendMessage("§e§lENGENHOCA§f: §cVocê acabou de utilizar a engenhoca de §aCacto§f.");
					Cooldown.Cacto.put(p, "Cacto");
					Cooldown.Delay(p, "Cacto", 15);
				}
			}
		}
	}
	
	@EventHandler
	public void onClickInventory(InventoryClickEvent e) {
		Player p = (Player)e.getWhoClicked();
		
		if (e.getInventory().getName().equalsIgnoreCase("§8ENGENHOCAS - LOJA")) {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType() == Material.AIR) return;
			e.setCancelled(true);
			if (e.getSlot() == 10) {
				p.closeInventory();
				p.getInventory().addItem(Criar.criar(Material.CACTUS, "§aCactos §e(ENGENHOCA)", 1, 0));
				return;
			}
		}
	}

}
