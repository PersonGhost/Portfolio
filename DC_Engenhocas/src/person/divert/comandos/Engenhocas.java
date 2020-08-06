package person.divert.comandos;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import person.divert.apis.Criar;

public class Engenhocas implements CommandExecutor  {
	
	public static void Comprar(Player p) {
		
		Inventory inv = Bukkit.createInventory(p, 3*9, "§8ENGENHOCAS - LOJA");
		
		inv.setItem(10, Criar.criarL(Material.CACTUS, "§aCactos §e(ENGENHOCA)", 1, 0, new String[] {"", "§eEngenhoca §f- §aInformação", "", "§7Construa facilmente e rápidamente uma farme de cacto. ", "", "§eClique para comprar!"}));
		inv.setItem(11, Criar.criarL(Material.FIREWORK, "§aFoguete §e(ENGENHOCA)", 1, 0, new String[] {"", "§eEngenhoca §f- §aInformação", "", "§7Consiga ser impulsionado para cima de uma forma rápida. ", "", "§eClique para Comprar!"}));
		inv.setItem(12, Criar.criarL(Material.TRIPWIRE_HOOK, "§aArmadilha §e(ENGENHOCA)", 1, 0, new String[] {"" , "§eEngenhoca §f- §aInformação", "§7Faça uma armadilha para prender seus inimigos rápidamente. ", "", "§eClique para Comprar!"}));
		inv.setItem(13, Criar.criarL(Material.ANVIL, "§aRetringir Efeitos §e(ENGENHOCA)", 1, 0, new String[] {"" , "§eEngenhoca §f- §aInformação", "§7Remove os efeitos dos seus itens facilmente. ", "", "§eClique para Comprar!"}));
		inv.setItem(14, Criar.criarL(Material.WOOD_AXE, "§aThor §e(ENGENHOCA)", 1, 0, new String[] {"" , "§eEngenhoca §f- §aInformação", "§7Solte um raio nos seus inimigos fazendo eles tomar dano. ", "", "§eClique para Comprar!"}));

		p.openInventory(inv);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (!(sender instanceof Player)) return true;
		
		Player p = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("engenhocas")) {
			if (args.length >= 0) {
				Comprar(p);
				return true;
			}
			return true;
		}
		
		return false;
	}

	
	
}
