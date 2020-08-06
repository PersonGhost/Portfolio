package br.com.fantasynetwork.bukkit;

import java.util.concurrent.ExecutionException;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.permissions.PermissibleBase;

import br.com.fantasynetwork.bukkit.apis.PermissionsGroup;
import br.com.fantasynetwork.bukkit.apis.PermissionsUser;

public class API {
	
	public static void setupPermissions(Player player) {
        PermissionAttachment attachment = player.addAttachment(Permission.getInstance());
        
        PermissionsGroup.PermissionsGroupsPlayer.put(player.getUniqueId(), attachment);
        PermissionsGroup.playerGroupPermissions.put(player.getUniqueId(), attachment);
        PermissionsGroup.GroupSetter(player, player.getUniqueId());
        
        PermissionsUser.playerSetter(player, player.getUniqueId());
    }
	
	public static void updateGroupPermissions(Player p) {
		PermissionAttachment attachment = p.addAttachment(Permission.getInstance());
		PermissionsGroup.playerGroupPermissions.get(p.getUniqueId()).remove();
		PermissionsGroup.PermissionsGroupsPlayer.get(p.getUniqueId()).remove();
		
		PermissionsGroup.playerGroupPermissions.put(p.getUniqueId(), attachment);
		PermissionsGroup.playerGroupPermissions.put(p.getUniqueId(), attachment);
        PermissionsGroup.GroupSetter(p, p.getUniqueId());
	}
	
	public static void updatePlayerPermissions(Player p) {
		//PermissionAttachment attachment = p.addAttachment(Permission.getInstance());
		
        PermissionsUser.playerSetter(p, p.getUniqueId());
	}
	
}
