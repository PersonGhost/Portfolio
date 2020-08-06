package br.com.fantasynetwork.bukkit;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import br.com.fantasynetwork.bukkit.apis.PermissionsGroup;
import br.com.fantasynetwork.bukkit.apis.PermissionsUser;
import br.com.fantasynetwork.bukkit.managers.Manager;

public class PCommand implements CommandExecutor {

	public static Config permissaoConfig = new Config("Grupos.yml");
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg2, String[] args) {
		
		if (sender instanceof Player && (!(sender.isOp() || sender.hasPermission("*")))) { 
			sender.sendMessage(""); 
			sender.sendMessage("§f[§5FantasyPermissions§f] Version §9["+Permission.getInstance().getDescription().getVersion()+"]");
			sender.sendMessage("");
			return true; 
		}  
		
		String gcmd = "§e/"+arg2+" grupo ";
		String jcmd = "§e/"+arg2+" jogador ";
		
		if (cmd.getName().equalsIgnoreCase("FPermission")) {
			
			if (args.length == 0 || args.length >= 6) {
				sender.sendMessage("");
				sender.sendMessage("§eComandos de ajuda com o comando §f'"+arg2+"' - §eGrupos.");
				sender.sendMessage("");
				sender.sendMessage(gcmd+"criar (nome do grupo)");
				sender.sendMessage(gcmd+"deletar (nome do grupo)");
				sender.sendMessage(gcmd+"set (nome do grupo) prefix (prefix)");
				sender.sendMessage(gcmd+"set (nome do grupo) suffix (suffix)");
				sender.sendMessage(gcmd+"list (nome do grupo)");
				sender.sendMessage(gcmd+"add (nome do grupo) (permissião)");
				sender.sendMessage(gcmd+"remove (nome do grupo) (permissao)");
				sender.sendMessage("");
				sender.sendMessage("§eComandos de ajuda com o comando §f'"+arg2+"' - §eJogador.");
				sender.sendMessage("");
				sender.sendMessage(jcmd+"set grupo (jogador) (grupo)");
				sender.sendMessage(jcmd+"set prefix (jogador) (prefix)");
				sender.sendMessage(jcmd+"set suffix (jogador) (suffix)");
				sender.sendMessage(jcmd+"addG (jogador) (grupo)");
				sender.sendMessage(jcmd+"removeG (jogador) (grupo)");
				sender.sendMessage(jcmd+"add (jogador) (permissão)");
				sender.sendMessage(jcmd+"remove (jogador) (permissão)");
				sender.sendMessage("");
				return true;
			}
			
			if (args.length > 0) {
				
				if (args[0].equalsIgnoreCase("grupo")) {
					if (args.length < 3) {
						sender.sendMessage("");
						sender.sendMessage("§eComandos de ajuda com o comando §f'"+arg2+"' - §eGrupos.");
						sender.sendMessage("");
						sender.sendMessage(gcmd+"criar (nome do grupo)");
						sender.sendMessage(gcmd+"deletar (nome do grupo)");
						sender.sendMessage(gcmd+"set (nome do grupo) prefix (prefix)");
						sender.sendMessage(gcmd+"set (nome do grupo) suffix (suffix)");
						sender.sendMessage(gcmd+"list (nome do grupo)");
						sender.sendMessage(gcmd+"add (nome do grupo) (permissião)");
						sender.sendMessage(gcmd+"remove (nome do grupo) (permissao)");
						sender.sendMessage("");
						return true;
					}
					
					String grupo = args[2];
					if (args[1].equalsIgnoreCase("criar") && args.length == 3) {
						if (permissaoConfig.contains("Grupos."+grupo)) {
							sender.sendMessage("§eGrupo §7"+grupo+" §ejá existente.");
							return true;
						}
						
						
						PermissionsGroup.criarGrupo(sender, grupo);
						return true;
					} else if (args[1].equalsIgnoreCase("list") && args.length == 3) {
						if (!(permissaoConfig.contains("Grupos."+grupo))) {
							sender.sendMessage("§cGrupo não existente.");
							return true;
						}
						
						sender.sendMessage("\n§ePermissões: \n§eGrupo: §a"+grupo);
						sender.sendMessage("");
						for (String grupos : permissaoConfig.getConfig().getConfigurationSection("Grupos").getKeys(false)) {
							if (grupo.equalsIgnoreCase(grupos)) {
								List<String> perms = new ArrayList<>();
								perms.add(" "+permissaoConfig.getStringList("Grupos."+grupo+".Permissions").toString().replace("[", "").replace("]", ""));
								sender.sendMessage(perms.toString().replace("[", "").replace("]", "").split(","));
							}
						}
						sender.sendMessage("");
						return true;
					} else if (args[1].equalsIgnoreCase("deletar") && args.length == 3) {
						if (!(permissaoConfig.contains("Grupos."+grupo))) {
							sender.sendMessage("§eGrupo não existente.");
							return true;
						}
						
						PermissionsGroup.deletarGrupo(sender, grupo);
						return true;
					} else if (args[1].equalsIgnoreCase("set") && args.length == 5) {
						if (args[3].equalsIgnoreCase("prefix")) {
							String prefix = args[4];
							
							if (!(permissaoConfig.contains("Grupos."+grupo))) {
								sender.sendMessage("§aGrupo não foi encontrado.");
								return true;
							}
							
							PermissionsGroup.setPrefix(sender, grupo, prefix);
							return true;
						} else if (args[3].equalsIgnoreCase("suffix")) {
							String suffix = args[4];
							
							if (!(permissaoConfig.contains("Grupos."+grupo))) {
								sender.sendMessage("§aGrupo não foi encontrado.");
								return true;
							}
							
							PermissionsGroup.setSuffix(sender, grupo, suffix);
							return true;
						}
						
						return true;
					} else if (args[1].equalsIgnoreCase("add") && args.length == 4) {
						String perm = args[3];
						if (!(permissaoConfig.contains("Grupos."+grupo))) {
							sender.sendMessage("§cGrupo inexistente.");
							return true;
						}
						
						PermissionsGroup.addPerm(sender, grupo, perm);
						return true;
					} else if (args[1].equalsIgnoreCase("remove") && args.length == 4) {
						String perm = args[3];
						if (!(permissaoConfig.contains("Grupos."+grupo))) {
							sender.sendMessage("§cGrupo inexistente.");
							return true;
						}
						
						PermissionsGroup.removePerm(sender, grupo, perm);
						return true;
					} else {
						sender.sendMessage("");
						sender.sendMessage("§eComandos de ajuda com o comando §f'"+arg2+"' - §eGrupos.");
						sender.sendMessage("");
						sender.sendMessage(gcmd+"criar (nome do grupo)");
						sender.sendMessage(gcmd+"deletar (nome do grupo)");
						sender.sendMessage(gcmd+"set (nome do grupo) prefix (prefix)");
						sender.sendMessage(gcmd+"set (nome do grupo) suffix (suffix)");
						sender.sendMessage(gcmd+"list (nome do grupo)");
						sender.sendMessage(gcmd+"add (nome do grupo) (permissião)");
						sender.sendMessage(gcmd+"remove (nome do grupo) (permissao)");
						sender.sendMessage("");
					}
					
					return true;
				} else if (args[0].equalsIgnoreCase("jogador")) {
					
					if (args.length < 4) {
						sender.sendMessage("");
						sender.sendMessage("§eComandos de ajuda com o comando §f'"+arg2+"' - §eJogador.");
						sender.sendMessage("");
						sender.sendMessage(jcmd+"set grupo (jogador) (grupo)");
						sender.sendMessage(jcmd+"set prefix (jogador) (prefix)");
						sender.sendMessage(jcmd+"set suffix (jogador) (suffix)");
						sender.sendMessage(jcmd+"addG (jogador) (grupo)");
						sender.sendMessage(jcmd+"removeG (jogador) (grupo)");
						sender.sendMessage(jcmd+"add (jogador) (permissão)");
						sender.sendMessage(jcmd+"remove (jogador) (permissão)");
						sender.sendMessage("");
						return true;
					}
					
					if (args[1].equalsIgnoreCase("set") && args.length == 5) {
						Player jogador = Bukkit.getPlayer(args[3]);
						if (jogador == null) {
							sender.sendMessage(Manager.jogadorOffline);
							return true;
						}
						if (args[2].equalsIgnoreCase("grupo")) {
							String grupo = args[4]; 
							if (!(permissaoConfig.contains("Grupos."+grupo))) {
								sender.sendMessage(Manager.grupoInexistente);
								return true;
							}
							
							PermissionsUser.setGrupo(sender, jogador, grupo);
							return true;
						}
						
						if (args[2].equalsIgnoreCase("prefix")) {
							String prefix = args[4]; 
							PermissionsUser.setPrefixo(sender, jogador, prefix);
							return true;
						}
						
						if (args[2].equalsIgnoreCase("suffix")) {
							String suffix = args[4];
							PermissionsUser.setSufixo(sender, jogador, suffix);
							return true;
						}
						
						return true;
					}
					
					Player jogador = Bukkit.getPlayer(args[2]);
					if (jogador == null) {
						sender.sendMessage(Manager.jogadorOffline);
						return true;
					}
					if (args[1].equalsIgnoreCase("add") && args.length == 4) {
						String perm = args[3];
						
						
						PermissionsUser.addPerm(sender, jogador, perm);
						return true;
					} else if (args[1].equalsIgnoreCase("remove") && args.length == 4) {
						String perm = args[3];
						
						
						PermissionsUser.removePerm(sender, jogador, perm);
						return true;
					} else if (args[1].equalsIgnoreCase("addG") && args.length == 4) {
						String grupo = args[3];
						
						PermissionsUser.addGrupo(sender, jogador, grupo);
						return true;
					} else if (args[1].equalsIgnoreCase("removeG") && args.length == 4) {
						String grupo = args[3];
						
						PermissionsUser.removeGrupo(sender, jogador, grupo);
						return true;
					}
					
					return true;
				} else if (!(args[0].equalsIgnoreCase("jogador") || args[0].equalsIgnoreCase("grupo"))){
					sender.sendMessage("§eNão foi possível encontrar o tipo de ajuda pedido §c"+args[0]);
				}
				
			}
			
			return true;
		}
		
		return false;
	}

}
