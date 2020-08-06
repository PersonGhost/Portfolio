package person.minigames.fantasy.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import org.bukkit.Bukkit;

public class MySQL {

	public static Connection connection;

	private ResultSet rs = null;
	private Statement stm = null;
	private PreparedStatement ps = null;
	private Properties info = null;
	
	private String url;
	private String user;
	private String passowrd;

	public MySQL(String ip, Integer port, String database, String user, String password) throws MySQLException {
		this.url = "jdbc:mysql://" + ip + ":" + port + "/" + database;
		this.user = user;
		this.passowrd = password;
		info = new Properties();
		info.put("autoReconnect", "true");
		info.put("user", user);
		info.put("password", password);
		info.put("userUnicode", "true");
		info.put("characterEncoding", "utf8");
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, info);
			registerTable();
		} catch (ClassNotFoundException e) {
			throw new MySQLException("ClassNotFound", "MySQL Nao Conectado (ErroID: 01)");
		} catch (SQLException e) {
			throw new MySQLException("SQLException", "MySQL Nao Conectado (ErroID: 02)");
		}
	}
	
	public Statement getStatement() {
		try {
			return getConnection().createStatement();
		} catch (SQLException e) {
			return null;
		}
	}

	public void connect() throws MySQLException {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, passowrd);
		} catch (ClassNotFoundException e) {
			throw new MySQLException("ClassNotFound", "Erro mySQL nao conectado");
		} catch (SQLException e) {
			throw new MySQLException("SQLException", "Erro mySQL nao conectado");
		}
	}

	public static Connection getConnection() {
		return connection;
	}
	
	public void registerTable() throws SQLException {
		executeStatement("CREATE TABLE IF NOT EXISTS `fn_lobbyminigames`(`id` INT NOT NULL AUTO_INCREMENT, `uuid` VARCHAR(100), `name` VARCHAR(40), `vanish` VARCHAR(40), `admin_mode` VARCHAR(40), `login_info` VARCHAR(100), `config_perfil` VARCHAR(100), PRIMARY KEY(`id`))");
		//UUID, NOME, VANISH, ADMIN-MODE, PRIMEIRO/ULTIMO-LOGIN, VASIBILIADDE/VOO/MENSAGEM-PRIVADA
		/*
		 * VANISH
		 * ADMIN-MODE
		 * --- ABAIXO EM SPLIT ---
		 * PRIMEIRO-LOGIN
		 * ULTIMO-LOGIN
		 * --- ACIMA EM SPLIT ---
		 * 
		 * --- ABAIXO USAR EM SPLIT ---
		 * VISIBILIDADE
		 * VOO
		 * MENSAGEM-PRIVADA
		 * --- ACIMA USAR EM SPLIT ---
		 */
		Bukkit.getConsoleSender().sendMessage("[MySQL] Tabela criada/carregada com sucesso!");
	}
	/*
	public static void executeStatement(String query, Object... objects){
		try {
			PreparedStatement ps=connection.prepareStatement(query);
			for(int i=0;i<objects.length;i++)
				ps.setObject(i+1, objects[i]);
			queue(ps);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	*/
	 public static void queue(PreparedStatement ps){
			try{
				ps.execute();
				ps.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
    public static void execute(String query, boolean flag, Object... objects)
    {
      try
      {
        PreparedStatement ps =connection.prepareStatement(query);
        for (int i = 0; i < objects.length; i++) {
          ps.setObject(i + 1, objects[i]);
        }
        ps.execute();
        ps.close();
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }


	public void closeConnection() throws SQLException {
		if (!connection.isClosed())
			connection.close();
		if (!ps.isClosed())
			ps.close();
		if (!stm.isClosed())
			stm.close();
		if (!rs.isClosed())
			rs.close();
	}

	public void preparedStatement(String query) {
		try {
			if (getConnection().isClosed()) {
				try {
					connect();
				} catch (MySQLException e) {
					e.printStackTrace();
				}
			}

			ps = connection.prepareStatement(query);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void executeStatement(String query) {
		try {
			if (getConnection().isClosed()) {
				try {
					connect();
				} catch (MySQLException e) {
					e.printStackTrace();
				}
			}
			stm = connection.createStatement();
			stm.executeUpdate(query);
			stm.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public Object resultSet(String sql, String coluna) {
		try {
			if (getConnection().isClosed()) {
				try {
					connect();
				} catch (MySQLException e) {
					e.printStackTrace();
				}
			}
			rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				return rs.getObject(coluna);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public boolean resultSetBoolean(String sql) {
		try {
			if (getConnection().isClosed()) {
				try {
					connect();
				} catch (MySQLException e) {
					e.printStackTrace();
				}
			}

			rs = connection.createStatement().executeQuery(sql);
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
