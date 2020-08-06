package person.trade.divert.comando;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import person.trade.divert.ChatInterativo;
import person.trade.divert.Criar;
import person.trade.divert.Trade;

public class Comando implements CommandExecutor {

	public static HashMap<Player, Player> playerTrade = new HashMap<>();
	public static HashMap<Player, Integer> playerTradeValor = new HashMap<>();
	public static HashMap<Player, ItemStack> playerTradeItem = new HashMap<>();
	public static HashMap<Player, Player> playerTradeCom = new HashMap<>();
	public static ArrayList<Player> playerEstaEmTrade = new ArrayList<>();
	public static ArrayList<Player> playerTradeVendedor = new ArrayList<>();
	
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) return true;
		
		Player p = (Player)sender;
		
		if (cmd.getName().equalsIgnoreCase("trade")) {
			
			if (!(p.hasPermission("trade.usar"))) {
				p.sendMessage("§cVocê precisa da permissão §ftrade.usar §cpara executar esta comando.");
				return true;
			}
			
			if (args.length == 0 || args.length > 3) {
				p.sendMessage("");
				p.sendMessage(
						"§a/trade vender (jogador) (preço) §7- Vender item a um jogador.\n" + 
						"§a/trade comprar (vendedor) §7- Comprar um item de um jogador.\n" + 
						"§a/trade info §7- Obtenha informações sobre sua venda.\n" + 
					  //"§a/trade (jogador) §7- Trocar um item com o jogador.\n" + 
						"§a/trade cancelar §7- Cancelar venda de um item.");
				p.sendMessage("");
				return true;
			}
			
			if (args.length > 0) {
				if (args.length == 3) {
					if (args[0].equalsIgnoreCase("vender")) {
						Player jogador = Bukkit.getPlayer(args[1]);
						int preco = Integer.valueOf(args[2]);
						
						if (playerEstaEmTrade.contains(p)) {
							p.sendMessage("§a§lTRADE§f: §cVocê já esta realizando a venda/compra de um item.");
							return true;
						}
						
						if (jogador == null) {
							p.sendMessage("§a§lTRADE§f: §cVocê só pode realizar vendas com jogadores online. ");
							return true;
						}
						
						if (playerEstaEmTrade.contains(jogador)) {
							p.sendMessage("§a§lTRADE§f: §cO Jogador já esta realizando a venda/compra de um item.");
							return true;
						}
						
						if (jogador == p) {
							p.sendMessage("§a§lTRADE§f: §cVocê não pode realizar vendas consigo mesmo.");
							return true;
						}
						
						if (preco < 0) {
							p.sendMessage("§a§lTRADE§f: §cVocê precisa inserar um valor maior que §70§c.");
							return true;
						}
						
						Trade.playerPUT(p, jogador, preco, p.getItemInHand());
						
						p.getInventory().setItem(p.getInventory().getHeldItemSlot(), new ItemStack(Material.AIR));
						
						p.sendMessage("§a§lTRADE§f: Você esta vendendo um §7"+playerTradeItem.get(p).getType()+" §fpara §7"+jogador.getName()+"§f.");
						
						jogador.sendMessage("");
						jogador.sendMessage("§a§lTRADE§f: §7"+p.getName()+" §fDeseja lhe vender um §7"+playerTradeItem.get(p).getItemMeta().getDisplayName()+" §fpelo preço de §7"+playerTradeValor.get(p));
						ChatInterativo.Comando(jogador.getName(), "§a§lTRADE§f: Clique aqui para mais informações. ", "/trade comprar "+playerTrade.get(jogador).getName(), "§aAo Clicar você irá comprar o item.");
						jogador.sendMessage("");
						return true;
					} else {
						p.sendMessage("§a/trade vender (jogador) (preço) §7- Vender item a um jogador.");
					}
					return true;
				}
				
				if (args.length == 2) {
					Player jogador = Bukkit.getPlayer(args[1]);
					if (args[0].equalsIgnoreCase("comprar")) {
						if (!(playerEstaEmTrade.contains(p))) {
							p.sendMessage("§a§lTRADE§f: §cVocê não contém nenhuma compra ocorrendo.");
							return true;
						}
						
						if (!(playerTrade.containsKey(p))) {
							p.sendMessage("§a§lTRADE§f: §cVocê não contém nenhuma compra ocorrendo.");
							return true;
						}
						
						if (jogador == null) {
							p.sendMessage("§a§lTRADE§f: §cO Seu vendedor não se encontra no servidor.");
							return true;
						}
						
						Inventory inv = Bukkit.createInventory(p, 5*9, "§8Trade - "+jogador.getName());
						
						inv.setItem(4, Criar.criarL(Material.PAPER, "§aInformações da Compra", 1, 0, new String[] {"", "§fItem: §7"+playerTradeItem.get(playerTrade.get(p)).getType(), "§fQuantidade: §7"+playerTradeItem.get(playerTrade.get(p)).getAmount(), "§fPreço: §7"+playerTradeValor.get(playerTrade.get(p)), "§7Ao aceitar a compra você irá estar ", "§7comprando o item pelo preço acima. "}));
						
						inv.setItem(20, Criar.criarL(Material.STAINED_GLASS_PANE, "§cNEGAR", 1, 14, new String[] {"§7Ao Clicar você irá cancelar o trade com o jogador. "}));
						inv.setItem(22, playerTradeItem.get(playerTrade.get(p)));
						inv.setItem(24, Criar.criarL(Material.STAINED_GLASS_PANE, "§aACEITAR", 1, 5, new String[] {"§7Ao Clicar você irá aceitar o trade com o jogador. "}));
						
						p.openInventory(inv);
						
						return true;
					} else {
						p.sendMessage("§a/trade comprar (jogador) §7- Comprar um item de um jogador.");
					}
					
					return true;
				}
				
				if (args.length == 1) { 
					
//					for (Player s : Bukkit.getOnlinePlayers()) {
//						if (args[0].equals(s.getName()) && s != p) {
//							Player jogador = Bukkit.getPlayer(args[0]);
//							Trade.onInventoryTroca(p, jogador);
//							return true;
//						}
//					}
					
					if (args[0].equalsIgnoreCase("info")) {
						
						if (!(playerTradeVendedor.contains(p))) {
							p.sendMessage("§a§lTRADE§f: Você não esta vendendo nenhum item.");
							return true;
						}
						
						
						p.sendMessage("");
						p.sendMessage("§a§lTRADE§f - Informações da venda.");
						p.sendMessage("");
						p.sendMessage(" §e* §aProduto§f: §7"+playerTradeItem.get(p).getType());
						p.sendMessage(" §e* §aQuantidade§f: §7"+playerTradeItem.get(p).getAmount());
						p.sendMessage(" §e* §aValor§f: §7"+playerTradeValor.get(p));
						p.sendMessage(" §e* §aComprador§f: §7"+playerTradeCom.get(p).getName());
						p.sendMessage("");
						return true;
					}
					
					if (args[0].equalsIgnoreCase("cancelar")) {
						
						if (!(playerTradeVendedor.contains(p))) {
							p.sendMessage("§a§lTRADE§f: Você não esta vendendo nenhum item.");
							return true;
						}
						
						playerTradeCom.get(p).sendMessage("§a§lTRADE§f: O Vendedor §ccancelou§f a venda.");
						p.getInventory().addItem(playerTradeItem.get(p));
						p.sendMessage("§a§lTRADE§f: Você §ccancelou §fsua venda.");
						Trade.playerRemoveVenda(p);
						return true;
					}
					
					return true;
				}
				
				return true;
			}
			
			return true;
		}
		
		return false;
	}

}
