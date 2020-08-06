package person.minigames.fantasy.score;

import java.util.concurrent.*;
import org.bukkit.*;
import org.bukkit.entity.*;

import person.minigames.fantasy.Main;

import java.util.*;

public class Setar
{
    private static ConcurrentHashMap<UUID, Score> players;
    
    static {
        Setar.players = new ConcurrentHashMap<UUID, Score>();
    }
    
    public static ConcurrentHashMap<UUID, Score> getPlayers() {
        return Setar.players;
    }
    
    public static void onEnable() {
        for (final Player player : Bukkit.getOnlinePlayers()) {
            Setar.players.put(player.getUniqueId(), new Score(player.getName()));
            player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        }
        Bukkit.getScheduler().runTaskTimerAsynchronously(Main.getInstance(), (Runnable)new Runnable() {
            @Override
            public synchronized void run() {
                for (Score p : Setar.players.values()) {
                    p.updateScoreboard();
                }
            }
        }, 10L, 10L);
    }
}
