package person.minigames.fantasy.score;

import org.bukkit.event.*;

import java.util.ArrayList;

import org.bukkit.*;
import org.bukkit.event.player.*;

import person.minigames.fantasy.Main;

public class EventosScore implements Listener
{
	public static ArrayList<String> aaa = new ArrayList<>();
	
    @EventHandler(priority = EventPriority.HIGH)
    public void onJoin(final PlayerJoinEvent e) {
    	aaa.add(e.getPlayer().getName());
    	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				if (aaa.contains(e.getPlayer().getName())) {
					Setar.getPlayers().put(e.getPlayer().getUniqueId(), new Score(e.getPlayer().getName()));
				}
			}
			
    	}, 60);
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerChangeWorld(final PlayerChangedWorldEvent e) {
    	Bukkit.getScheduler().scheduleSyncDelayedTask(Main.getInstance(), new Runnable() {
			
			@Override
			public void run() {
				if (aaa.contains(e.getPlayer().getName())) {
					e.getPlayer().setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
				}
			}
		}, 60);
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onKick(PlayerKickEvent e) {
    	if (aaa.contains(e.getPlayer().getName())) {
    		aaa.remove(e.getPlayer().getName());
    	}
		Setar.getPlayers().remove(e.getPlayer().getUniqueId());
		if (Score.error.contains(e.getPlayer())) {
			Score.error.remove(e.getPlayer());
		}
    }
    
    @EventHandler(priority = EventPriority.HIGH)
    public void onQuit(PlayerQuitEvent e) {
    	if (aaa.contains(e.getPlayer().getName())) {
    		aaa.remove(e.getPlayer().getName());
    	}
		Setar.getPlayers().remove(e.getPlayer().getUniqueId());
		if (Score.error.contains(e.getPlayer())) {
			Score.error.remove(e.getPlayer());
		}
    }
}
