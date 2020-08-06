package person.minigames.fantasy.eventos;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import person.minigames.fantasy.perfil.Jogador;

public class Sair implements Listener {

	@EventHandler
	public void sair(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		
		e.setQuitMessage(null);
		
		Jogador j = Jogador.get(p);
		if (j != null) {
			j.save();
		}
		
		Jogador.remove(e.getPlayer());
	}
	
}
