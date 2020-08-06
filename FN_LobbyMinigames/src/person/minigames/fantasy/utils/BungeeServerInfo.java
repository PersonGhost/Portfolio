package person.minigames.fantasy.utils;

public class BungeeServerInfo
{
  private volatile boolean isOnline;
  private volatile int onlinePlayers;
  private volatile int maxPlayers;
  private volatile String motd1;
  private volatile String motd2;
  private volatile long lastRequest;
  
  public BungeeServerInfo()
  {
    this.isOnline = false;
    this.motd1 = "";
    this.motd2 = "";
    updateLastRequest();
  }
  
  public boolean isOnline()
  {
    return this.isOnline;
  }
  
  public void setOnline(boolean isOnline)
  {
    this.isOnline = isOnline;
  }
  
  public int getOnlinePlayers()
  {
    return this.onlinePlayers;
  }
  
  public void setOnlinePlayers(int onlinePlayers)
  {
    this.onlinePlayers = onlinePlayers;
  }
  
  public int getMaxPlayers()
  {
    return this.maxPlayers;
  }
  
  public void setMaxPlayers(int maxPlayers)
  {
    this.maxPlayers = maxPlayers;
  }
  
  public String getMotd1()
  {
    return this.motd1;
  }
  
  public String getMotd2()
  {
    return this.motd2;
  }
 
  public long getLastRequest()
  {
    return this.lastRequest;
  }
  
  public void updateLastRequest()
  {
    this.lastRequest = System.currentTimeMillis();
  }
}
