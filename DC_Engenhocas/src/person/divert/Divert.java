package person.divert;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import person.divert.comandos.Engenhocas;
import person.divert.eventos.Armadilha;
import person.divert.eventos.Cactus;
import person.divert.eventos.Foguete;
import person.divert.eventos.RestringirEfeitos;
import person.divert.eventos.Thor;

public class Divert extends JavaPlugin {
	
	public static Divert instance;
	public static Divert getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		Bukkit.getConsoleSender().sendMessage("§a§l[ENGENHOCAS] §e§lPlugin iniciado corretamente.");
		
		RegistrarComando();
		Registrarevento();
	}
	
	public void RegistrarComando() {
		getCommand("engenhocas").setExecutor(new Engenhocas());;
	}
	
	public void Registrarevento() {
		Bukkit.getPluginManager().registerEvents(new Cactus(), this);
		Bukkit.getPluginManager().registerEvents(new Foguete(), this);
		Bukkit.getPluginManager().registerEvents(new Thor(), this);
		Bukkit.getPluginManager().registerEvents(new Armadilha(), this);
		Bukkit.getPluginManager().registerEvents(new RestringirEfeitos(), this);
	}

}
