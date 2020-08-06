package br.com.fantasynetwork.bukkit.apis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.google.gson.Gson;

import br.com.fantasynetwork.bukkit.API;
import br.com.fantasynetwork.bukkit.managers.Manager;
import br.com.fantasynetwork.mysql.Metodos;

public class PermissionsUser {
	
	///JOGADOR
	//public static HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();
	public static HashMap<UUID, List<String>> onPermission = new HashMap<>();
	public static HashMap<UUID, String> grupo = new HashMap<>();
	public static HashMap<UUID, List<String>> grupos = new HashMap<>();
	public static HashMap<UUID, String> prefixo = new HashMap<>();
	public static HashMap<UUID, String> sufixo = new HashMap<>();
	
	public static void addPerm(CommandSender sender, Player p, String perm) {
		if (onPermission.containsKey(p.getUniqueId())) {
			if (onPermission.get(p.getUniqueId()).contains(perm)) {
				sender.sendMessage(Manager.prefix+"§cO Jogador já contém esta permissão.");
				return;
			}
			
			List<String> per = new ArrayList<>();
			per.add(perm);
			for (String perms : onPermission.get(p.getUniqueId())) {
				per.add(perms);
			}
			
			onPermission.put(p.getUniqueId(), per);
			
			sender.sendMessage(Manager.prefix+"§aPermissão §f"+perm+" §aadicionada ao jogador §f"+p.getName());
			API.updatePlayerPermissions(p);
		}
	}

	public static void removePerm(CommandSender sender, Player p, String perm) {
		if (onPermission.containsKey(p.getUniqueId())) {
			if (!(onPermission.get(p.getUniqueId()).contains(perm))) {
				sender.sendMessage(Manager.prefix+"§cO Jogador não contém esta permissão.");
				return;
			}
			
			List<String> per = new ArrayList<>();
			for (String perms : onPermission.get(p.getUniqueId())) {
				per.add(perms);
			}
			per.remove(perm);
			onPermission.put(p.getUniqueId(), per);
			
			
			API.updatePlayerPermissions(p);
			sender.sendMessage(Manager.prefix+"§cPermissão §f"+perm+" §cremovida do jogador §f"+p.getName());
		}
	}
	
	public static void playerSetter(Player p, UUID uuid) {
        for (String perms : onPermission.get(p.getUniqueId())) {
        	PermissionManager.getPermissionManager().insertPermission(p, perms);
        }
	}
	
	public static void setPrefixo(CommandSender sender, Player p, String prefix) {
		
		prefixo.put(p.getUniqueId(), prefix);
		Metodos.setPrefix(p.getName(), prefix, p.getUniqueId().toString());
		sender.sendMessage(Manager.jogadorsetPREFIX+prefix.replace("&", "§"));
		
	}
	
	public static void setSufixo(CommandSender sender, Player p, String suffix) {
		
		sufixo.put(p.getUniqueId(), suffix);
		Metodos.setSuffix(p.getName(), suffix, p.getUniqueId().toString());
		sender.sendMessage(Manager.jogadorsetSUFFIX+suffix.replace("&", "§"));
		
	}
	
	public static void setGrupo(CommandSender sender, Player p, String group) {
		
		grupo.put(p.getUniqueId(), group);
		Metodos.setGrupo(p.getName(), group, p.getUniqueId().toString());
		PermissionsGroup.GroupSetter(p, p.getUniqueId());
		sender.sendMessage(Manager.jogadorADDGrupo+group);
		
	}
	
	public static void addGrupo(CommandSender sender, Player p, String grupo) {
		if (grupos.get(p.getUniqueId()).contains(grupo)) {
			sender.sendMessage(Manager.prefix+"§cGrupo §f"+grupo+" §cjá existente ao jogador §f"+p.getName());
			return;
		}
		
		List<String> gr = new ArrayList<>();
		for (String grupos : grupos.get(p.getUniqueId())) {
			gr.add(grupos);
		}
		gr.add(grupo);
		grupos.put(p.getUniqueId(), gr);
		
		API.updateGroupPermissions(p);
		sender.sendMessage(Manager.prefix+"§aGrupo §f"+grupo+" §aadicionado ao jogador §f"+p.getName());
	}
	
	public static void removeGrupo(CommandSender sender, Player p, String grupo) {
		if (!(grupos.get(p.getUniqueId()).contains(grupo))) {
			sender.sendMessage(Manager.prefix+"§cGrupo §f"+grupo+" §cnão encontrado no jogador §f"+p.getName());
			return;
		}
		
		List<String> gr = new ArrayList<>();
		for (String grupos : grupos.get(p.getUniqueId())) {
			gr.add(grupos);
		}
		gr.remove(grupo);
		grupos.put(p.getUniqueId(), gr);
		
		API.updateGroupPermissions(p);
		sender.sendMessage(Manager.prefix+"§cGrupo §f"+grupo+" §cremovido ao jogador §f"+p.getName());
	}
	
	public static String getPrefix(Player jogador) {
		return prefixo.get(jogador.getUniqueId()).replace("&", "§");
	}
	
	public static String getSuffix(Player jogador) {
		return sufixo.get(jogador.getUniqueId()).replace("&", "§");
	}
	
	public static String getGrupo(Player jogador) {
		return grupo.get(jogador.getUniqueId()).replace("&", "§");
	}
	
	@SuppressWarnings("unchecked")
	public static void loadPlayer(Player p, UUID uuid) {
		if (!(Metodos.contains(p.getName()))) {
			Metodos.setPlayer(p.getName(), uuid.toString(), "["+'"'+"permissao.padrao"+'"'+"]", "Default", "[]", "", "");
		}
		
		if (Metodos.getGrupo(p.getName(), uuid.toString()) == null) {
			grupo.put(uuid, "");
		} else {
			grupo.put(uuid, Metodos.getGrupo(p.getName(), uuid.toString()));
		}
		
        if (Metodos.getPrefix(p.getName(), p.getUniqueId().toString()) == null) { 
        	prefixo.put(uuid, "");
        } else {
        	prefixo.put(uuid, Metodos.getPrefix(p.getName(), p.getUniqueId().toString()));
        }
        
        if (Metodos.getSuffix(p.getName(), p.getUniqueId().toString()) == null) { 
        	sufixo.put(uuid, "");
        } else {
        	sufixo.put(uuid, Metodos.getSuffix(p.getName(), p.getUniqueId().toString()));	
        }

    	ArrayList<String> gruposs = new Gson().fromJson(Metodos.getGrupos(p.getName(), p.getUniqueId().toString()), ArrayList.class);
        grupos.put(p.getUniqueId(), gruposs);

        ArrayList<String> perms = new Gson().fromJson(Metodos.getPermissions(p.getName(), uuid.toString()), ArrayList.class);
        onPermission.put(p.getUniqueId(), perms);

        
        API.setupPermissions(p);
	}
	
	public static void unloadPlayer(Player p, UUID uuid) {
		Metodos.setGrupo(p.getName(), grupo.get(uuid), p.getUniqueId().toString());
        Metodos.setPrefix(p.getName(), prefixo.get(uuid), p.getUniqueId().toString());
        Metodos.setSuffix(p.getName(), sufixo.get(uuid), p.getUniqueId().toString());
        String jsondeperms = new Gson().toJson(onPermission.get(uuid) == null ? new ArrayList<>() : onPermission.get(uuid), ArrayList.class);
        Metodos.setPermission(p.getName(), jsondeperms, p.getUniqueId().toString());
        String jsondegrupos = new Gson().toJson(grupos.get(uuid) == null ? new ArrayList<>() : grupos.get(uuid), ArrayList.class);
        Metodos.addGrupos(p.getName(), jsondegrupos, uuid.toString());
        
        PermissionsGroup.playerGroupPermissions.get(p.getUniqueId()).remove();
        PermissionsGroup.PermissionsGroupsPlayer.get(p.getUniqueId()).remove();
        grupo.remove(uuid);
        grupos.remove(uuid);
        prefixo.remove(uuid);
        sufixo.remove(uuid);
        onPermission.remove(uuid);
	}
	
	public Boolean hasRegex(List<String> perms, String perm){
        Boolean has = null;
        for (String p : perms){

            String tocheck = p;

            boolean negate = false;

            if (p.startsWith("-")){
                negate = true;
                tocheck = p.substring(1);
            }

            tocheck = tocheck.replaceAll("\\.", "\\\\.").replaceAll("\\*", "\\.\\*").replaceAll("#", "\\.");
            boolean matches = perm.matches(tocheck);

            if (matches) {
                has = !negate;
            }
        }
        return has;
    }
	
}
