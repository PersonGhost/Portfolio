package person.minigames.fantasy;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import person.minigames.fantasy.comandos.Chat;
import person.minigames.fantasy.comandos.Connect;
import person.minigames.fantasy.comandos.Construir;
import person.minigames.fantasy.comandos.Spawn;
import person.minigames.fantasy.comandos.Tell;
import person.minigames.fantasy.comandos.Vanish;
import person.minigames.fantasy.eventos.Entrar;
import person.minigames.fantasy.eventos.Geral;
import person.minigames.fantasy.eventos.Interact;
import person.minigames.fantasy.eventos.Sair;
import person.minigames.fantasy.mysql.MySQL;
import person.minigames.fantasy.perfil.Jogador;
import person.minigames.fantasy.perfil.Perfil;
import person.minigames.fantasy.score.EventosScore;
import person.minigames.fantasy.score.Setar;
import person.minigames.fantasy.utils.BungeeAPI;
import person.minigames.fantasy.utils.BungeeServerInfo;

public class Main extends JavaPlugin {

	public static Main instance;
	public static Main getInstance() {
		return instance;
	}
	
	public void onLoad() {
		getServer().getConsoleSender().sendMessage("");
		getServer().getConsoleSender().sendMessage("FantasyMinigames - Carregando Arquivos...");
		getServer().getConsoleSender().sendMessage("FantasyMinigames - Criado Por PersonGhost");
		getServer().getConsoleSender().sendMessage("");

		getServer().getConsoleSender().sendMessage("");
		getServer().getConsoleSender().sendMessage("FantasyMinigames - Carregando Plugin...");
		getServer().getConsoleSender().sendMessage("FantasyMinigames - Criado Por PersonGhost ");
		getServer().getConsoleSender().sendMessage("");
	}
	
	@Override
	public void onEnable() {
		instance = this;
		
		saveDefaultConfig();
		iniciateBungee();
		
		getServer().getConsoleSender().sendMessage("");
		getServer().getConsoleSender().sendMessage("FantasyMinigames - Servidor Iniciando...");
		getServer().getConsoleSender().sendMessage("FantasyMinigames - Criado Por PersonGhost");
		getServer().getConsoleSender().sendMessage("");
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
			
			@Override
			public void run() {
				BungeeAPI.trackedServers.put("LobbySky", new BungeeServerInfo());
				BungeeAPI.trackedServers.put("lobbytb", new BungeeServerInfo());
			}
		}, 0, 20);
		
		registerEvents();
		registerCommands();
		iniciateMySQL();
	}
	
	public void startConfig() {
		Config config = new Config("config.yml");
		
		if (!(config.existeConfig() || config.getString("MySQL") == null)) {
			config.set("MySQL.host", "");
			config.set("MySQL.database", "");
			config.set("MySQL.user", "");
			config.set("MySQL.password", "");
			
			config.set("#Escreva o modo do servidor Lobby ou Minigame", "");
			config.set("Game.Mode", "");
			config.set("Game.Lobby", "Lobby01");
			config.set("", "");
			
			config.saveConfig();
		}
	}
	
	public void registerEvents() {
		PluginManager pm = Bukkit.getPluginManager();
		
		pm.registerEvents(new Entrar(), this);
		pm.registerEvents(new Geral(), this);
		pm.registerEvents(new Sair(), this);
		if (getInstance().getConfig().getString("Game.Mode").equalsIgnoreCase("Lobby")) {
			pm.registerEvents(new EventosScore(), this);
		}
		pm.registerEvents(new Perfil(), this);
		pm.registerEvents(new Interact(), this);
	}

	public void registerCommands() {
		getCommand("fnchat").setExecutor(new Chat());
		getCommand("connect").setExecutor(new Connect());
		getCommand("construir").setExecutor(new Construir());
		getCommand("fnspawn").setExecutor(new Spawn());
		getCommand("tell").setExecutor(new Tell());
		getCommand("vanish").setExecutor(new Vanish());
		
	}
	
	public static MySQL mysql;
	public static void iniciateMySQL() {
		try {
			String host = getInstance().getConfig().getString("MySQL.host");
			String database = getInstance().getConfig().getString("MySQL.database");
			String user = getInstance().getConfig().getString("MySQL.user");
			String password = getInstance().getConfig().getString("MySQL.password");
			
			mysql = new MySQL(host, 3306, database, user, password);
			System.out.println("[MySQL] Conexao foi aberta!");
			if (getInstance().getConfig().getString("Game.Mode").equalsIgnoreCase("Lobby")) {
				Setar.onEnable();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("[MySQL] Conexao mal suscedida.");
			Bukkit.getPluginManager().disablePlugin(Main.getInstance());
		}
	}
	
	public void iniciateBungee() {
		getServer().getMessenger().registerOutgoingPluginChannel(this, "BungeeCord");
		getServer().getMessenger().registerIncomingPluginChannel(this, "BungeeCord", new BungeeAPI());
		/**
	     * IREI BLOQUEAR O USO DO WORLD DOWNLOADER. PARA PROTEGER O SEU LOBBY :)
	     */
	    getServer().getMessenger().registerOutgoingPluginChannel(this, "WDL|CONTROL");
	    getServer().getMessenger().registerIncomingPluginChannel(this, "WDL|INIT", new BungeeAPI());
	    
	}
	
	@Override
	public void onDisable() {
		
		CommandSender sender = Bukkit.getConsoleSender();
		boolean save = Jogador.getDatas().size() > 0;
		if (save) {
			sender.sendMessage("FANTASY SKYWARS - SAVES");
		}
		List<String> saveds = new ArrayList<>();
		saveds.add("§eJogadores Salvados:");
		saveds.add("");
		for (Jogador pd : Jogador.getDatas()) {
			pd.save();
			saveds.add("§a" + pd.getPlayer().getName());
		}
		
		if (save) {
			saveds.add(" ");
			for (String string : saveds) {
				sender.sendMessage(string);
			}
		}
		
		try {
			if (!(MySQL.getConnection().isClosed())) {
				MySQL.getConnection().close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for (Player s : Bukkit.getOnlinePlayers()) {
			BungeeAPI.teleToServer(s, "§5§lFantasy§e§lNetwork §8➜ §cO Servidor em que você estava esta reiniciado.", "sv1");
		}
		
		getServer().getConsoleSender().sendMessage("");
		getServer().getConsoleSender().sendMessage("FantasyMinigames - Desativado");
		getServer().getConsoleSender().sendMessage("FantasyMinigames - Criado Por PersonGhost");
		getServer().getConsoleSender().sendMessage("");		
	}
	
}
