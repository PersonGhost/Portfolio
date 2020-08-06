package br.com.fantasynetwork.bukkit.apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

import br.com.fantasynetwork.bukkit.API;
import br.com.fantasynetwork.bukkit.Config;
import br.com.fantasynetwork.bukkit.managers.Manager;
import br.com.fantasynetwork.mysql.Metodos;

public class PermissionsGroup {

	public static HashMap<UUID, PermissionAttachment> playerGroupPermissions = new HashMap<>();
	public static HashMap<UUID, PermissionAttachment> PermissionsGroupsPlayer = new HashMap<>();
	public static Config permissaoConfig = new Config("Grupos.yml");
	public static HashMap<String, List<String>> permissions = new HashMap<>();
	public static List<String> recarregar = new ArrayList<>();
	
	public static void criarGrupo(CommandSender sender, String grupo) {
		List<String> perms = new ArrayList<>();
		perms.add("permissao.padrao");
		permissions.put(grupo, perms);
		
		permissaoConfig.set("Grupos."+grupo+".Prefix", "");
		permissaoConfig.set("Grupos."+grupo+".Suffix", "");
		permissaoConfig.set("Grupos."+grupo+".Permissions", permissions.get(grupo));
		permissaoConfig.saveConfig();
		permissaoConfig.reloadConfig();
		
		sender.sendMessage(Manager.grupoCRIADO);
	}
	
	public static void deletarGrupo(CommandSender sender, String grupo) {
		permissaoConfig.set("Grupos."+grupo, null);
		permissaoConfig.saveConfig();
		permissaoConfig.reloadConfig();
		
		sender.sendMessage(Manager.grupoDELETADO);
	}
	
	public static void addPerm(CommandSender sender, String grupo, String perm) {
		if (permissaoConfig.getStringList("Grupos."+grupo+".Permissions").contains(perm)) {
			sender.sendMessage(Manager.prefix+"§cO Grupo já contém esta permissão.");
			return;
		}
		List<String> per = new ArrayList<>();
		per.add(perm);
		for (String perms : permissaoConfig.getStringList("Grupos."+grupo+".Permissions")) {
			per.add(perms);
		}
		
		permissions.put(grupo, per);
		permissaoConfig.set("Grupos."+grupo+".Permissions", permissions.get(grupo));
		permissaoConfig.saveConfig();
		permissaoConfig.reloadConfig();
		System.out.print(per);
		
		
		if (Bukkit.getOnlinePlayers().size() >= 1) {
			for (Player s : Bukkit.getOnlinePlayers()) {
				if (PermissionsUser.grupo.get(s.getUniqueId()).equalsIgnoreCase(grupo)) {
					API.updateGroupPermissions(s);
				}
			}
		}
		sender.sendMessage(Manager.grupopermADD);
	}
	
	public static void removePerm(CommandSender sender, String grupo, String perm) {
		if (!(permissaoConfig.getStringList("Grupos."+grupo+".Permissions").contains(perm))) {
			sender.sendMessage(Manager.prefix+"§cO Grupo não contém esta permissão.");
			return;
		}
		List<String> per = new ArrayList<>();
		for (String perms : permissaoConfig.getStringList("Grupos."+grupo+".Permissions")) {
			per.add(perms);
		}
		
		per.remove(perm);
		permissions.put(grupo, per);
		permissaoConfig.set("Grupos."+grupo+".Permissions", permissions.get(grupo));
		permissaoConfig.saveConfig();
		permissaoConfig.reloadConfig();
		
		if (Bukkit.getOnlinePlayers().size() >= 1) {
			for (Player s : Bukkit.getOnlinePlayers()) {
				if (PermissionsUser.grupo.get(s.getUniqueId()).equalsIgnoreCase(grupo)) {
					API.updateGroupPermissions(s);
				}
			}
		}
		
		sender.sendMessage(Manager.grupopermREMOVE);
	}
	
	public static void setPrefix(CommandSender sender, String grupo, String prefix) {
		permissaoConfig.set("Grupos."+grupo+".Prefix", prefix.replace("&", "§"));
		permissaoConfig.saveConfig();
		permissaoConfig.reloadConfig();
		
		sender.sendMessage(Manager.gruposetPREFIX+prefix.replace("&", "§"));
	}
	
	public static void setSuffix(CommandSender sender, String grupo, String suffix) {
		permissaoConfig.set("Grupos."+grupo+".Suffix", suffix.replace("&", "§"));
		permissaoConfig.saveConfig();
		permissaoConfig.reloadConfig();
		
		sender.sendMessage(Manager.gruposetSUFFIX+suffix.replace("&", "§"));
	}

	public static void GroupSetter(Player p, UUID uuid) {
    	PermissionAttachment attachment = playerGroupPermissions.get(uuid);
    	for (String groups : permissaoConfig.getConfig().getConfigurationSection("Grupos").getKeys(false)) {
        	if (PermissionsUser.grupos.get(uuid).contains(groups) || Metodos.getGrupo(p.getName(), p.getUniqueId().toString()).equals(groups)) {
        		for (String permissions : permissaoConfig.getStringList("Grupos." + groups + ".Permissions")) {
                    attachment.setPermission(permissions, true);
                }
            }
        }
    }
	
}
