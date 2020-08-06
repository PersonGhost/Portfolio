package person.hydra.blockcmd;

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
		
		Bukkit.getConsoleSender().sendMessage("§e[H_BLOQUEARCOMANDOS] §aIniciado corretamente.");
		saveDefaultConfig();
		
		Bukkit.getPluginManager().registerEvents(new Events(), this);
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§e[H_BLOQUEARCOMANDOS] §cDesligado corretamente.");
	}
	
}
