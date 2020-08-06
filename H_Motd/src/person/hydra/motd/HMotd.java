package person.hydra.motd;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class HMotd extends JavaPlugin {

	public static HMotd instance;
	public static HMotd getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
				
		saveDefaultConfig();
		Bukkit.getConsoleSender().sendMessage("§e[H_Motd] §aIniciado corretamnete.");
		getCommand("hmotd").setExecutor(new ComandoEvento());
		Bukkit.getPluginManager().registerEvents(new ComandoEvento(), this);
	}
	
	@Override
	public void onDisable() {
		
		Bukkit.getConsoleSender().sendMessage("§e[H_Motd] §cDesligado corretamente.");
	}
}
