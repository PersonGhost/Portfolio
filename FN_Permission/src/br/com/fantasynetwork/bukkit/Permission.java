package br.com.fantasynetwork.bukkit;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import br.com.fantasynetwork.bukkit.apis.PermissionManager;
import br.com.fantasynetwork.mysql.loadPlayer;

public class Permission extends JavaPlugin {
	
	public static Permission instance;
	public static Permission getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		saveDefaultConfig();
		
		onRegisterCommand();
		onRegisterEvents();
		
		if (!(PCommand.permissaoConfig.existeConfig())) {
			List<String> permissions = new ArrayList<>();
			permissions.add("permissao.padrao");
			PCommand.permissaoConfig.set("Grupos.Default.Prefix", "&7[Default]");
			PCommand.permissaoConfig.set("Grupos.Default.Suffix", "");
			PCommand.permissaoConfig.set("Grupos.Default.Permissions", permissions);
			PCommand.permissaoConfig.saveConfig();
		}
		Config logs = new Config("Crash/");
		if (logs.existeConfig()) {
			for (File file : logs.getFile().listFiles()) {
				System.out.println("[FN_PERMISSION] "+file.length()+" LOGS(CRASH) ENCONTRADAS");
			}
		}
		for (Player s : Bukkit.getOnlinePlayers()) {
			PermissionManager.getPermissionManager().reload(s);
		}
		
		loadPlayer.register();
	}
	
	@Override
	public void onDisable() {
		for (Player s : Bukkit.getOnlinePlayers()) {
			PermissionManager.getPermissionManager().clear(s);
		}
	}
	
	public void onRegisterCommand() {
		getCommand("FPermission").setExecutor(new PCommand());
	}
	
	public void onRegisterEvents() {
		Bukkit.getPluginManager().registerEvents(new Eventos(), this);
	}
}
