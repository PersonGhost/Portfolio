package person.minigames.fantasy.eventos;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.ExplosionPrimeEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.entity.CreatureSpawnEvent.SpawnReason;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerAchievementAwardedEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

import person.minigames.fantasy.Main;
import person.minigames.fantasy.comandos.Spawn;

public class Geral implements Listener {
	
	@EventHandler
	public void chover(WeatherChangeEvent e) {
		if (e.toWeatherState()) {
			e.setCancelled(true);
		}
		e.setCancelled(true);
		e.getWorld().setWeatherDuration(0);
	}
	
	@EventHandler
	public void semdano(EntityDamageEvent e) {
		if (e.getEntity() instanceof Player) {
			if (e.getCause() == DamageCause.VOID) {
				e.setCancelled(true);
				e.setDamage(0);
				Player p = (Player)e.getEntity();
				Spawn.teleportar(p);
				return;
			}
			
			e.setCancelled(true);
			e.setDamage(0);
			return;
		}
		
		e.setCancelled(true);
		e.setDamage(0);
	}
	
	@EventHandler
	public void noPickupArmor(PlayerInteractEntityEvent e) {
		if (e.getRightClicked().getType() == EntityType.ARMOR_STAND) {
			if (e.getRightClicked().getCustomName().contains("§T§O§P§")) {
				e.setCancelled(true);
			}
		}
	}
	
	@EventHandler
	public void semfome(FoodLevelChangeEvent e) {
		e.setCancelled(true);
		e.setFoodLevel(20);
	}
	
	@EventHandler
	public void semfogo(BlockSpreadEvent e) {
		if (e.getNewState().getType() == Material.FIRE) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void morrer(PlayerDeathEvent e) {
		Player vitima = e.getEntity();
		
		e.setDeathMessage(null);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				vitima.spigot().respawn();
			}
		}, 1L);
		
		e.getDrops().clear();
	}
	
	public static ArrayList<Player> cooldown = new ArrayList<>(); 
	public static boolean chatstatus = false;
	
	@EventHandler
	public void chat(AsyncPlayerChatEvent e) {
		Player p = e.getPlayer();
		
		if (!(p.hasPermission("fantasy.chat.admin")) && chatstatus == true) {
			e.setCancelled(true);
			p.sendMessage("§cVocê não tem permissão para falar com o chat desativado.");
			return;
		}
		
		if (cooldown.contains(p) && !(p.hasPermission("fantasy.chat.cooldown"))) {
			e.setCancelled(true);
			p.sendMessage("Aguarde para utilizar o chat novamente.");
			return;
		}
		
		e.setCancelled(true);
		
		if (e.getMessage().contains("%")) {
			e.setMessage("%2$s".replace("%2$s", e.getMessage().replace("%", "porcento")));
		}
		
		if (p.hasPermission("fantasy.chat.color")) {
			e.setFormat("§7❪ §8" + p.getDisplayName() + " §7❫ §f" + e.getMessage().replace("&", "§"));
		} else {
			e.setFormat("§7❪ §8" + p.getDisplayName() + " §7❫ " + e.getMessage());
		}
		
		for (Player s : Bukkit.getOnlinePlayers()) {
			s.sendMessage(e.getFormat());
		}
		
		cooldown.add(p);
		Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				cooldown.remove(p);
			}
		}, 20*3);
	}

	@EventHandler
	public void naodropar(PlayerDropItemEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void semconquista(PlayerAchievementAwardedEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void noexplodir(ExplosionPrimeEvent e) {
		e.setCancelled(true);
	}
	
	@EventHandler
	public void spawnmobs(CreatureSpawnEvent e) {
		if (e.getSpawnReason() != SpawnReason.CUSTOM) {
			e.setCancelled(true);
		} else if (e.getSpawnReason() == SpawnReason.CUSTOM) {
			e.setCancelled(false);
		}
	}
	
	public static ArrayList<Player> construir = new ArrayList<>();
	
	@EventHandler
	public void naoQubrar(BlockBreakEvent e) {
		if (!(construir.contains(e.getPlayer()))) {
			e.setCancelled(true);
		}
	}
	
	@EventHandler
	public void naoColocar(BlockPlaceEvent e) {
		if (!(construir.contains(e.getPlayer()))) {
			e.setCancelled(true);
		}
	}
	
	
	@EventHandler(ignoreCancelled = true, priority = EventPriority.NORMAL)
	public void onPreProcessCommand(PlayerCommandPreprocessEvent event) {
		if (event.getMessage().toLowerCase().startsWith("/me ")) {
			event.getPlayer().sendMessage(
					" §cDesculpe, Mas Este Comando Não Foi Encontrado No Banco De Dados Do Servidor.");
			event.setCancelled(true);

		}
		if (event.getMessage().toLowerCase().equalsIgnoreCase("/me")) {

			event.getPlayer().sendMessage(
					" §cDesculpe, Mas Este Comando Não Foi Encontrado No Banco De Dados Do Servidor.");
			event.setCancelled(true);
		}
		if (event.getMessage().toLowerCase().startsWith("/ver ")) {
			event.getPlayer().sendMessage(
					" §cDesculpe, Mas Este Comando Não Foi Encontrado No Banco De Dados Do Servidor.");
			event.setCancelled(true);
		}
		if (event.getMessage().toLowerCase().equalsIgnoreCase("/ver")) {
			event.getPlayer().sendMessage(
					" §cDesculpe, Mas Este Comando Não Foi Encontrado No Banco De Dados Do Servidor.");
			event.setCancelled(true);
		}
		if (event.getMessage().toLowerCase().startsWith("/help")) {
			event.getPlayer().sendMessage(
					" §cDesculpe, Mas Este Comando Não Foi Encontrado No Banco De Dados Do Servidor.");
			event.setCancelled(true);
		}
		if (event.getMessage().toLowerCase().startsWith("/help ")) {
			event.getPlayer().sendMessage(
					" §cDesculpe, Mas Este Comando Não Foi Encontrado No Banco De Dados Do Servidor.");
			event.setCancelled(true);
		}
		if (event.getMessage().toLowerCase().startsWith("/plugins")
				&& !event.getMessage().toLowerCase().startsWith("/pl")
				&& (!event.getMessage().toLowerCase().startsWith("/plot"))) {
			event.getPlayer().sendMessage("");

			event.getPlayer().sendMessage("§e§l➜ §7Plugin FantasyNetwork §f- §aLobby");
			event.setCancelled(true);
		}
		if (event.getMessage().toLowerCase().startsWith("/pl")
				&& (!event.getMessage().toLowerCase().startsWith("/plot"))) {
			event.getPlayer().sendMessage("");

			event.getPlayer().sendMessage("§e§l➜ §7Plugin FantasyNetwork §f- §aLobby");
			event.setCancelled(true);
		}
		if (event.getMessage().toLowerCase().startsWith("/server ")) {
			event.getPlayer().sendMessage(
					" §cDesculpe, Mas Este Comando Não Foi Encontrado No Banco De Dados Do Servidor.");
			event.setCancelled(true);
		}
		if (event.getMessage().split(" ")[0].contains(":")) {
			event.getPlayer().sendMessage(
					" §cDesculpe, Mas Este Comando Não Foi Encontrado No Banco De Dados Do Servidor.");
			event.setCancelled(true);
		}
		if (event.getMessage().split(" ")[0].contains("?")) {
			event.getPlayer().sendMessage(
					" §cDesculpe, Mas Este Comando Não Foi Encontrado No Banco De Dados Do Servidor.");
			event.setCancelled(true);
		}
	}
	
}
