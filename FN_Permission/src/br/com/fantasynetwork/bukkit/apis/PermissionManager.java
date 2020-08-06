package br.com.fantasynetwork.bukkit.apis;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import br.com.fantasynetwork.bukkit.Permission;
import br.com.fantasynetwork.mysql.Metodos;

public class PermissionManager {
	
	private static PermissionManager permissionManager = new PermissionManager();
	private PermissionManager() {}
	public static PermissionManager getPermissionManager() {return permissionManager; }

	private Map<UUID, PermissionAttachment> permissionsDataMap = new HashMap<>();
	
	public PermissionAttachment getPermissionData(Player p) {
		UUID uuid = p.getUniqueId();
		if (permissionsDataMap.containsKey(uuid)) {
			return permissionsDataMap.get(uuid);
		} else {
			PermissionAttachment permissionAttachment = p.addAttachment(Permission.getInstance());
			permissionsDataMap.put(uuid, permissionAttachment);
			return permissionsDataMap.get(uuid);
		}
	}
	
	public void insertPermission(Player p, String perm) {
		getPermissionData(p).setPermission(perm, true);
	}
	
	public void clear(Player p) {
		permissionsDataMap.get(p.getUniqueId()).remove();
	}
	
	public void reload(Player p) {
		List<String> permissions;
		
		if (Metodos.contains(p.getName())) {
			PermissionsUser.loadPlayer(p, p.getUniqueId());
			permissions = PermissionsUser.onPermission.get(p.getUniqueId());
		} else {
			PermissionsUser.loadPlayer(p, p.getUniqueId());
			return;
		}
		
		for (int x= 0; x < permissions.size(); x++) {
			insertPermission(p, permissions.get(x));
		}
	}
}
