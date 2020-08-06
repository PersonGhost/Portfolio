package person.minigames.fantasy.score;

import org.bukkit.scoreboard.*;

import org.bukkit.entity.*;

import java.util.ArrayList;

import org.bukkit.*;

public class Score {
	

	private String player;
	private API sb;

	public Score(final String player) {
		this.player = player;
		(this.sb = new API()).setSlot(DisplaySlot.SIDEBAR);
		this.sb.setName(" §5§lFANTASY §f- §e§lMINIGAMES ");
		this.sb.blankLine(11);
		this.sb.addLine(10, " §fCargo: ");
		this.sb.addLine(9, " §fNome: §a");
		this.sb.blankLine(8);
		this.sb.addLine(7, "  §c§lMODO VANISH");
		this.sb.blankLine(6);
		this.sb.addLine(5, "§a§lMinigames ➜ ");
		this.sb.addLine(4, " §fSkyWars: §a");
		this.sb.addLine(3, " §fTheBridge: §a");
		this.sb.blankLine(2);
		this.sb.addLine(1, "§eloja.fantasynetwork.com.br");			
	}
	
//	public static String getMoney(Player p){
//		DecimalFormat numberFormat = new DecimalFormat("#,##0.00");
//		String d = numberFormat.format(SkyWarsPlayer.get(p).getSaldo());
//		if (d.length() > 32){
//			d = d.substring(0, 32);
//		}
//		return d;
//	}
	
	public static ArrayList<Player> error = new ArrayList<>();
	
	public synchronized void updateScoreboard() {
        if (!this.sb.hasBoard(this.getPlayer())) {
            this.sb.setForPlayer(this.getPlayer());
        }
    	this.sb.updateLine(10, " §fCargo: "+getPlayer().getDisplayName().replace(getPlayer().getName(), ""));
    	this.sb.updateLine(9, " §fNome: §a"+getPlayer().getName());
    	
//    	if (Jogador.get(getPlayer()).getVanish().equalsIgnoreCase("desativado")) {
//    		if (this.sb.hasLine(7)) {
//        		this.sb.removeLine(7);
//        	}
//    	} else {
//    		if (!(this.sb.hasLine(7))) {
//    			try {
//    				this.sb.removeLine(7);
//    				this.sb.addLine(7, "  §c§lMODO VANISH");
//				} catch (Exception e) {
//					this.sb.removeLine(7);
//					if (!error.contains(getPlayer())) {
//						getPlayer().sendMessage(Manager.prefix+" §cRecarregando a score, um problema ocorreu.");
//						Setar.getPlayers().remove(getPlayer().getUniqueId());
//						Setar.getPlayers().put(getPlayer().getUniqueId(), new Score(getPlayer().getName()));
//						error.add(getPlayer());
//					}
//				}
//    		}
//    	}
    }

	public Player getPlayer() {
		return Bukkit.getPlayer(this.player);
	}
	
}
