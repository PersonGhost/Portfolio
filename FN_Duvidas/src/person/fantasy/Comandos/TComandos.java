package person.fantasy.Comandos;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.dnx.fantasycore.mysql.utils.CommandUtil;

public class TComandos extends CommandUtil {

	public TComandos(String command, String usage, String description) {
		super(command, usage, description);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if (!(sender instanceof Player)) { sender.sendMessage("§c§lDesculpe, somente jogadores."); return true; }
		
		if (args.length == 0) {
			
			return true;
		}
		
		return false;
	}

}
