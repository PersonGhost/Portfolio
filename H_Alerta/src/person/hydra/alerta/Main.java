package person.hydra.alerta;

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
		getCommand("alerta").setExecutor(new Comando());
		Bukkit.getConsoleSender().sendMessage("§e[H_Alerta] §aIniciado corretamente.");
	}
	
	@Override
	public void onDisable() {
		
		Bukkit.getConsoleSender().sendMessage("§e[H_Alerta] §cDesligado corretamente.");
		
	}
	
}