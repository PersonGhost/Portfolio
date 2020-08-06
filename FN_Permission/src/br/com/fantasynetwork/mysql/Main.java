package br.com.fantasynetwork.mysql;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	public static Main instance;
	public static Main getInstance() {
		return instance;
	}
	
	
	@Override
	public void onEnable() {
		instance = this;
		
		Bukkit.getConsoleSender().sendMessage("§e[FN_PERMISSIONS] §aPlugin inicializando...");
		loadPlayer.register();
	}

}
