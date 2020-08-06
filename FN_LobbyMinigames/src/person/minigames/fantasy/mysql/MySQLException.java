package person.minigames.fantasy.mysql;

@SuppressWarnings("serial")
public class MySQLException extends Exception {
	
	private String message;
	
	public MySQLException(String arg, String message) {
		super(arg);
		this.message = message;
	}
	
	public String getMessage(){
		return message;
	}
	
}
