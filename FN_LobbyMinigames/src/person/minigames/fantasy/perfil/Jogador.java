package person.minigames.fantasy.perfil;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import person.minigames.fantasy.mysql.MySQL;
import person.minigames.fantasy.utils.Manager;

public class Jogador {

	private Player player;
	public Player getPlayer() {
		return player;
	}
	
	private boolean updatevanish = false;
	private boolean updateadminmode = false;
	private boolean updatelogininfo = false;
	private boolean	updateconfigperfil = false;
	private boolean modified;
	
	public HashMap<Player, String> vanish = new HashMap<>();
	public HashMap<Player, String> adminmode = new HashMap<>();
	public HashMap<Player, String> firstlogin = new HashMap<>();
	public HashMap<Player, String> lastlogin = new HashMap<>();
	public HashMap<Player, String> visibilidade = new HashMap<>();
	public HashMap<Player, String> voo = new HashMap<>();
	public HashMap<Player, String> msgprivada = new HashMap<>();
	
	public Jogador(Player p) {
		player = p;
		
		vanish.put(p, "desativado");
		adminmode.put(p, "desativado");
		firstlogin.put(p, ""+Manager.getTime(p.getFirstPlayed()));
		lastlogin.put(p, ""+Manager.getTime(p.getLastPlayed()));
		visibilidade.put(p, "ativado");
		voo.put(p, "desativado");
		msgprivada.put(p, "ativado");
	}
	
	public String getVanish() {
		return vanish.get(player);
	}
	
	public void setVanish(String mode) {
		vanish.put(player, mode);
		modified = true;
	}
	
	public String getAdmin() {
		return adminmode.get(player);
	}
	
	public void setAdmin(String mode) {
		adminmode.put(player, mode);
		modified = true;
	}
	
	public String getLognInfo() {
		String logininfo = firstlogin.get(player)+" : "+lastlogin.get(player);
		return logininfo;
	}
	
	public void setFisrtLoginInfo(String mode) {
		firstlogin.put(player, mode);
		modified = true;
	}
	
	public void setLastLoginInfo(String mode) {
		lastlogin.put(player, mode);
		modified = true;
	}
	
	public String getConfigPerfil() {
		String perfilInfo = visibilidade.get(player)+" : "+voo.get(player)+" : "+msgprivada.get(player);
		return perfilInfo;
	}
	
	public void setVisibilidade(String mode) {
		visibilidade.put(player, mode);
		modified = true;
	}
	
	public String getVoo() {
		return voo.get(player);
	}
	
	public void setVoo(String mode) {
		voo.put(player, mode);
		modified = true;
	}
	
	public String getMsgPrivada() {
		return msgprivada.get(player);
	}
	
	public void setMsgPrivada(String mode) {
		msgprivada.put(player, mode);
		modified = true;
	}
	
	public void loadPlayer() {
		try {
			ResultSet rs = MySQL.getConnection().createStatement().executeQuery("SELECT * FROM fn_lobbyminigames WHERE uuid='"+player.getUniqueId().toString()+"';");
		
			if (rs.next()) {
				vanish.put(player, rs.getString("vanish"));
				adminmode.put(player, rs.getString("admin_mode"));
				firstlogin.put(player, rs.getString("login_info").split(" : ")[0]);
				lastlogin.put(player, rs.getString("login_info").split(" : ")[1]);
				visibilidade.put(player, rs.getString("config_perfil").split(" : ")[0]);
				voo.put(player, rs.getString("config_perfil").split(" : ")[1]);
				msgprivada.put(player, rs.getString("config_perfil").split(" : ")[2]);
				
				rs.close();
			} else {
				MySQL.execute("INSERT INTO fn_lobbyminigames (uuid, name, vanish, admin_mode, login_info, config_perfil) VALUES (?, ?, ?, ?, ?, ?)", false, player.getUniqueId().toString(), player.getName(), "desativado", "desativado", player.getFirstPlayed()+" : "+player.getLastPlayed(), "ativado : desativado : ativado");
			
				vanish.put(player, "desativado");
				adminmode.put(player, "desativado");
				firstlogin.put(player, ""+player.getFirstPlayed());
				lastlogin.put(player, ""+player.getLastPlayed());
				visibilidade.put(player, "ativado");
				voo.put(player, "desativado");
				msgprivada.put(player, "ativado");
				
				rs.close();
			}
		
		} catch (Exception e) {
			e.printStackTrace();
			
			vanish.put(player, "desativado");
			adminmode.put(player, "desativado");
			firstlogin.put(player, ""+Manager.getTime(player.getFirstPlayed()));
			lastlogin.put(player, ""+Manager.getTime(player.getLastPlayed()));
			visibilidade.put(player, "ativado");
			voo.put(player, "desativado");
			msgprivada.put(player, "ativado");
		}
	}
	
	public void save() {
		
		if (isModified()) {
			
			List<String> update = new ArrayList<>();
			List<Object> object = new ArrayList<>();
			
			if (updatevanish) {
				update.add("vanish=?");
				object.add(getVanish());
			}
			
			if (updateadminmode) {
				update.add("admin_mode=?");
				object.add(getAdmin());
			}
			
			if (updatelogininfo) {
				update.add("login_info=?");
				object.add(getLognInfo());
			}
			
			if (updateconfigperfil) {
				update.add("config_perfil=?");
				object.add(getConfigPerfil());
			}
			
			object.add(player.getUniqueId().toString());
			MySQL.execute("UPDATE fn_lobbyminigames SET "+update.toString().replace("[", "").replace("]", "").replace(" ", "") + "WHERE uuid=?", false, object.toArray(new Object[0]));
			
		}
		
	}
	
	public boolean isModified() {
		return modified;
	}
	
	public void updateInventory() {
		this.player.updateInventory();
	}
	
	public String getDisplayName() {
		return this.player.getDisplayName();
	}
	
	public PlayerInventory getInventory() {
		return this.player.getInventory();
	}
	
	public ItemStack getItemInHand() {
		return this.player.getItemInHand();
	}
	
	public Location getLocation() {
		return this.player.getLocation();
	}
	
	public World getWorld() {
		return this.player.getWorld();
	}
	
	public GameMode getGameMode() {
		return this.player.getGameMode();
	}
	
	public boolean teleport(Location location) {
		return this.player.teleport(location);
	}
	
	public boolean hasPermission(String permission) {
		return this.player.hasPermission(permission);
	}
	
	private static Map<Player, Jogador> datas = new HashMap<>();
	public static Jogador create(Player p) {
		if (datas.containsKey(p)) {
			return get(p);
		}
		
		datas.put(p, new Jogador(p));
		return get(p);
	}
	
	public static Jogador get(Player p) {
		if (datas.containsKey(p)) {
			return datas.get(p);
		} else {
			return null;
		}
	}

	public static void remove(Player p) {
		if (datas.containsKey(p)) {
			datas.remove(p);
		}
	}
	
	public static List<Jogador> getDatas() {
		return new ArrayList<Jogador>(datas.values());
	}
}
