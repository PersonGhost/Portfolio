package br.com.fantasynetwork.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Metodos extends loadPlayer {
	
	public static List<String> bPermissions;
	
	public static List<String> getPermissions() {
		return bPermissions;
	}
	
	public static boolean contains(String p){
		
		PreparedStatement stm = null;
		try {
			stm = getConnection().prepareStatement("SELECT * FROM `Permissions` WHERE `jogador` = ?");
			stm.setString(1, p);
			ResultSet rs = stm.executeQuery();
			while(rs.next()){
				return true;
			}
			return false;
		} catch (SQLException e) {
			return false;
		}
	}
	
	//Permissions jogador  uuid  permissions  grupo grupos prefix suffix
	
	public static void setPlayer(String p, String uuid, String permissions, String grupo, String grupos, String prefix, String suffix){
		
		PreparedStatement stm = null;
		
		try {
			stm = getConnection().prepareStatement("INSERT INTO `Permissions`(`jogador`, `uuid`, `permissions`, `grupo`, `grupos`, `prefix`, `suffix`) VALUES (?, ?, ?, ?, ?, ?, ?)");
			stm.setString(1, p);
			stm.setString(2, uuid);
			stm.setString(3, permissions);
			stm.setString(4, grupo);
			stm.setString(5, grupos);
			stm.setString(6, prefix);
			stm.setString(7, suffix);
			stm.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			log.info(" » Não foi possível registrar o jogador " + p + " no banco de dados.");
		}
	}
	
	public static void setPermission(String p, String permission, String uuid){
		
		if(contains(p)){
			PreparedStatement stm = null;
			
			try {
				stm = getConnection().prepareStatement("UPDATE `Permissions` SET `permissions` = ? WHERE `jogador` = ? AND `uuid` = ?");
				stm.setString(1, permission);
				stm.setString(2, p);
				stm.setString(3, uuid);
				stm.executeUpdate();
				
			} catch (SQLException e) {
				log.info(" » Não foi possível alterar a Permissao do jogador " + p);
			}
		} else {
			setPlayer(p, uuid, "["+'"'+"permissao.padrao"+'"'+"]", "Default", "[]", "", "");
		}
		
	}
	
	public static String getPermissions(String p, String uuid){
		
		if(contains(p)){
			PreparedStatement stm = null;
			
			try {
				stm = getConnection().prepareStatement("SELECT * FROM `Permissions` WHERE `jogador` = ? AND `uuid` = ?");
				stm.setString(1, p);
				stm.setString(2, uuid);
				ResultSet rs = stm.executeQuery();
				
				while(rs.next()){
					return rs.getString("permissions");
				}
				return "Sem Permissão";
			} catch (SQLException e) {
				return "Sem Permissão";
			}
		} else {
			return "Sem Permissão";
		}
	}
	
	public static void setGrupo(String p, String grupo, String uuid){
		
		if(contains(p)){
			PreparedStatement stm = null;
			
			try {
				stm = getConnection().prepareStatement("UPDATE `Permissions` SET `grupo` = ? WHERE `jogador` = ? AND `uuid` = ?");
				stm.setString(1, grupo);
				stm.setString(2, p);
				stm.setString(3, uuid);
				stm.executeUpdate();
				
			} catch (SQLException e) {
				log.info(" » Não foi possível alterar a Grupo do jogador " + p);
			}
		} else {
			setPlayer(p, uuid, "["+'"'+"permissao.padrao"+'"'+"]", "Default", "[]", "", "");
		}
		
	}
	
	public static String getGrupo(String p, String uuid){
		
		if(contains(p)){
			PreparedStatement stm = null;
			
			try {
				stm = getConnection().prepareStatement("SELECT * FROM `Permissions` WHERE `jogador` = ? AND `uuid` = ?");
				stm.setString(1, p);
				stm.setString(2, uuid);
				ResultSet rs = stm.executeQuery();
				
				while(rs.next()){
					return rs.getString("grupo");
				}
				return "Sem Grupo";
			} catch (SQLException e) {
				return "Sem Grupo";
			}
		} else {
			return "Sem Grupo";
		}
	}
	
	public static void addGrupos(String p, String grupo, String uuid){
		
		if(contains(p)){
			PreparedStatement stm = null;
			
			try {
				stm = getConnection().prepareStatement("UPDATE `Permissions` SET `grupos` = ? WHERE `jogador` = ? AND `uuid` = ?");
				stm.setString(1, grupo);
				stm.setString(2, p);
				stm.setString(3, uuid);
				stm.executeUpdate();
				
			} catch (SQLException e) {
				log.info(" » Não foi possível alterar a Grupo do jogador " + p);
			}
		} else {
			setPlayer(p, uuid, "["+'"'+"permissao.padrao"+'"'+"]", "Default", "[]", "", "");
		}
		
	}
	
	public static String getGrupos(String p, String uuid){
		
		if(contains(p)){
			PreparedStatement stm = null;
			
			try {
				stm = getConnection().prepareStatement("SELECT * FROM `Permissions` WHERE `jogador` = ? AND `uuid` = ?");
				stm.setString(1, p);
				stm.setString(2, uuid);
				ResultSet rs = stm.executeQuery();
				
				while(rs.next()){
					return rs.getString("grupos");
				}
				return "Sem Grupo";
			} catch (SQLException e) {
				return "Sem Grupo";
			}
		} else {
			return "Sem Grupo";
		}
	}
	
	public static void setPrefix(String p, String prefix, String uuid){
		
		if(contains(p)){
			PreparedStatement stm = null;
			
			try {
				stm = getConnection().prepareStatement("UPDATE `Permissions` SET `prefix` = ? WHERE `jogador` = ? AND `uuid` = ?");
				stm.setString(1, prefix);
				stm.setString(2, p);
				stm.setString(3, uuid);
				stm.executeUpdate();
				
			} catch (SQLException e) {
				log.info(" » Não foi possível alterar a Prefix do jogador " + p);
			}
		} else {
			setPlayer(p, uuid, "["+'"'+"permissao.padrao"+'"'+"]", "Default", "[]", "", "");
		}
		
	}
	
	public static String getPrefix(String p, String uuid){
		
		if(contains(p)){
			PreparedStatement stm = null;
			
			try {
				stm = getConnection().prepareStatement("SELECT * FROM `Permissions` WHERE `jogador` = ? AND `uuid` = ?");
				stm.setString(1, p);
				stm.setString(2, uuid);
				ResultSet rs = stm.executeQuery();
				
				while(rs.next()){
					return rs.getString("prefix");
				}
				return "Sem Grupo";
			} catch (SQLException e) {
				return "Sem Grupo";
			}
		} else {
			return "Sem Grupo";
		}
	}
	
	public static void setSuffix(String p, String suffix, String uuid){
		
		if(contains(p)){
			PreparedStatement stm = null;
			
			try {
				stm = getConnection().prepareStatement("UPDATE `Permissions` SET `suffix` = ? WHERE `jogador` = ? AND `uuid` = ?");
				stm.setString(1, suffix);
				stm.setString(2, p);
				stm.setString(3, uuid);
				stm.executeUpdate();
				
			} catch (SQLException e) {
				log.info(" » Não foi possível alterar a Prefix do jogador " + p);
			}
		} else {
			setPlayer(p, uuid, "["+'"'+"permissao.padrao"+'"'+"]", "Default", "[]", "", "");
		}
		
	}
	
	public static String getSuffix(String p, String uuid){
		
		if(contains(p)){
			PreparedStatement stm = null;
			
			try {
				stm = getConnection().prepareStatement("SELECT * FROM `Permissions` WHERE `jogador` = ? AND `uuid` = ?");
				stm.setString(1, p);
				stm.setString(2, uuid);
				ResultSet rs = stm.executeQuery();
				
				while(rs.next()){
					return rs.getString("suffix");
				}
				return "Sem Grupo";
			} catch (SQLException e) {
				return "Sem Grupo";
			}
		} else {
			return "Sem Grupo";
		}
	}
	
	public static void unregisterPlayer(String p){
		
		PreparedStatement stm = null;
		
		try {
			stm = getConnection().prepareStatement("DELETE FROM `Permissions` WHERE `player` = ?");
			stm.setString(1, p);
			stm.executeUpdate();
			
		} catch (SQLException e) {
			log.warning(" » Erro ao verificar se o PIN existe.");
		}
		
		
	}
}
