package person.trade.divert;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import person.trade.divert.comando.Comando;
import person.trade.divert.evento.DEvento;

public class Divert extends JavaPlugin {

	public static Divert instance;
	public static Divert getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		getCommand("trade").setExecutor(new Comando());
		Bukkit.getPluginManager().registerEvents(new DEvento(), this);
		Bukkit.getConsoleSender().sendMessage("§e[DC_TRADE] §aPlugin desenvolvido por PersonGhost.");
		Bukkit.getConsoleSender().sendMessage("§e[DC_TRADE] §aPlugin iniciado corretamente.");
	}
	
	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("§e[DC_TRADE] §aPlugin desenvolvido por PersonGhost.");
		Bukkit.getConsoleSender().sendMessage("§e[DC_TRADE] §aPlugin desativado corretamente.");
	}

}
