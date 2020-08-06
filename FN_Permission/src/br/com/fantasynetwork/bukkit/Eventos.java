package br.com.fantasynetwork.bukkit;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import br.com.fantasynetwork.bukkit.apis.PermissionManager;
import br.com.fantasynetwork.bukkit.apis.PermissionsUser;

public class Eventos extends API implements Listener{
	
	@EventHandler
    public void join(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        
//        if (!(Metodos.contains(player.getName()))) {
//        	Metodos.setPlayer(player.getName(), player.getUniqueId().toString(), "["+'"'+"permissao.padrao"+'"'+"]", "Default", "[]", "", "");
//        	PermissionsUser.loadPlayer(player, player.getUniqueId());
//        } else {
//        	PermissionsUser.loadPlayer(player, player.getUniqueId());
//        }

        PermissionManager.getPermissionManager().reload(player);
	}

    @EventHandler
    public void leave(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        
        PermissionsUser.unloadPlayer(player, player.getUniqueId());
        PermissionManager.getPermissionManager().clear(player);
    }
    
    @EventHandler
    public void kick(PlayerKickEvent e) {
    	Player player = e.getPlayer();
    	
    	PermissionsUser.unloadPlayer(player, player.getUniqueId());
    	PermissionManager.getPermissionManager().clear(player);
    }

//    @EventHandler
//    public void chat(AsyncPlayerChatEvent e) {
//    	Player p = e.getPlayer();
//    	String groups = Metodos.getGrupo(p.getName(), p.getUniqueId().toString());
//		if (PCommand.permissaoConfig.getConfig().getConfigurationSection("Grupos").contains(groups)) {
//    		String prefix = PCommand.permissaoConfig.getString("Grupos." + groups + ".Prefix");
//			String suffix = PCommand.permissaoConfig.getString("Grupos." + groups + ".Suffix"); 
//			if (prefix == null) {
//				p.setDisplayName(PermissionsUser.prefixo.get(p.getUniqueId()).replace("&", "§")+p.getName()+PermissionsUser.sufixo.get(p.getUniqueId()).replace("&", "§")+suffix.replace("&", "§"));
//			} else if (suffix == null) {
//				p.setDisplayName(prefix.replace("&", "§")+PermissionsUser.prefixo.get(p.getUniqueId()).replace("&", "§")+p.getName()+PermissionsUser.sufixo.get(p.getUniqueId()).replace("&", "§"));
//			} else {
//				p.setDisplayName(prefix.replace("&", "§")+PermissionsUser.prefixo.get(p.getUniqueId()).replace("&", "§")+p.getName()+PermissionsUser.sufixo.get(p.getUniqueId()).replace("&", "§")+suffix.replace("&", "§"));
//			}
//    		
//        }
//    }

}
