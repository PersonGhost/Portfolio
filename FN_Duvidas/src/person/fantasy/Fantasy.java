package person.fantasy;

import org.bukkit.plugin.java.JavaPlugin;

import person.fantasy.Comandos.TComandos;

public class Fantasy extends JavaPlugin {
	
	public static Fantasy instance;
	public static Fantasy getInstance() {
		return instance;
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		
	}

	public void RegistrarComandos() {
		TComandos tCommand = new TComandos("tCommand", "tCommand2", "Comando de Almas");
	    tCommand.register();
		
	}
	
}
