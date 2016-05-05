import java.util.Random;

public class roleDecider{
	private int numberOfPlayers;
	private String[] players;
	private boolean isLovers;
	
	public roleDecider(String[] players){
		numberOfPlayers = players.length;
		this.players = players;
		isLovers = (players.length > 8);
	}

	public String[][] decideRoles(){
		String[][] roles = new String[numberOfPlayers][2];
		Random random = new Random();
		
		int numberOfWolves = random.nextInt(numberOfPlayers/2); // No more than half the players are wolves!
		
			
		for (int i = 0; i < numberOfPlayers; i++){
			String player = players[i];
			roles[i][0] = player;
		}
	}
	public static void main(String[] args){
		Random random = new Random();

	}
}
