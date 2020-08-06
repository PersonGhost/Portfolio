package person.trade.divert;

import net.minecraft.server.v1_8_R3.ChatClickable;
import net.minecraft.server.v1_8_R3.ChatClickable.EnumClickAction;
import net.minecraft.server.v1_8_R3.ChatHoverable;
import net.minecraft.server.v1_8_R3.ChatHoverable.EnumHoverAction;
import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.ChatModifier;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.MinecraftServer;
import net.minecraft.server.v1_8_R3.PlayerList;

public class ChatInterativo {
	
  public static void Comando(String p, String MensagemNoChat, String ComandoAoClicar, String MouseEncima) {
    IChatBaseComponent base = new ChatMessage(MensagemNoChat, new Object[0]);
    base.setChatModifier(new ChatModifier());
    base.getChatModifier().setChatClickable(new ChatClickable(EnumClickAction.RUN_COMMAND, ComandoAoClicar));
    base.getChatModifier().setChatHoverable(new ChatHoverable(EnumHoverAction.SHOW_TEXT, new ChatMessage(MouseEncima, new Object[0])));
    PlayerList list = MinecraftServer.getServer().getPlayerList();
    list.getPlayer(p).sendMessage(base);
  }
  
  public static void Link(String p, String Mensagem, String LinkAoClicar, String MouseEncima) {
    IChatBaseComponent base = new ChatMessage(Mensagem, new Object[0]);
    base.setChatModifier(new ChatModifier());
    base.getChatModifier().setChatClickable(new ChatClickable(EnumClickAction.OPEN_URL, LinkAoClicar));
    base.getChatModifier().setChatHoverable(new ChatHoverable(EnumHoverAction.SHOW_TEXT, new ChatMessage(MouseEncima, new Object[0])));
    PlayerList list = MinecraftServer.getServer().getPlayerList();
    list.getPlayer(p).sendMessage(base);
  }


	public static String Comando(String displayName, String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
}