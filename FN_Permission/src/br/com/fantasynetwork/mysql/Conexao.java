package br.com.fantasynetwork.mysql;

import java.sql.Connection;
import java.util.logging.Logger;

import org.bukkit.Bukkit;

import br.com.fantasynetwork.bukkit.Config;

public class Conexao {
	
	public static Config config = new Config("config.yml");
	protected static Connection con = null;
	public static Logger log = Bukkit.getLogger();
	
//	public static void open(){
//		
//		String host = config.getString("MySQL.host"); 
//		String database = config.getString("MySQL.database");
//		String user = config.getString("MySQL.user");
//		String password = config.getString("MySQL.password");
//		
//		String url = "jdbc:mysql://" + host + ":3306/"+ database;
//		
//		try {
//			con = DriverManager.getConnection(url, user, password);
//			log.info(" � Conex�o MySQL aberta com sucesso!");
//			createTable();
//		} catch (SQLException e) {
//			log.info(" � N�o foi poss�vel estabelecer a conex�o MySQL. Desativando plugin.");
//			Permission.getInstance().getPluginLoader().disablePlugin(Permission.getInstance());
//		}
//		
//	}
//	
//	public static void close(){
//		if(con != null){
//			try {
//				con.close();
//				log.info(" � Conex�o MySQL fechada com sucesso!");
//				
//			} catch (SQLException e) {
//				log.info(" � N�o foi poss�vel fechar a conex�o!");
//			}
//		}
//	}
//	
//	public static void createTable(){
//		if(con != null){
//			
//			PreparedStatement stm = null;
//			//Permissions jogador  uuid  permissions  grupo
//			try {
//				stm = con.prepareStatement("CREATE TABLE IF NOT EXISTS `Permissions`(`id` INT NOT NULL AUTO_INCREMENT, `jogador` VARCHAR(30) NULL, `uuid` VARCHAR(100) NULL, `permissions` VARCHAR(900), `grupo` VARCHAR(30), `prefix` VARCHAR(30), `suffix` VARCHAR(30), PRIMARY KEY(`id`))");
//				stm.executeUpdate();
//				log.info(" � Tabela criada/carregada com sucesso!");
//			} catch (SQLException e) {
//				log.info(" � N�o foi poss�vel criar a tabela.");
//			}
//			
//		}
//	}
	
	public static void chekConection() {
		if (loadPlayer.getConnection() == null) {
			loadPlayer.register();
		}
	}
	
}
