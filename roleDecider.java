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

	private String[] populateRolesArray(int numberOfWolves, int numberOfPlayers){	// Will only work with at least (numberOfWolves + 4) players
		String[] rolesArray = new String[numberOfPlayers];

		int numberOfCubs = numberOfWolves/2;	// About half, mostly adults though
		for (int i=0; i < numberOfCubs; i++){
			rolesArray[i] = "Werewolf cub"; 
		}
		for (int i = numberOfCubs; i < numberOfWolves; i++){
			rolesArray[i] = "Werewolf";
		}
		rolesArray[numberOfWolves] = "Seer";	// One seer
		rolesArray[numberOfWolves + 1] = "Trapper";
		rolesArray[numberOfWolves + 2] = "Vigilante";
		rolesArray[numberOfWolves + 3] = "Protector";

		for (int i = numberOfWolves + 4; i < numberOfPlayers; i++){
			rolesArray[i] = "Villager";
		}

		return rolesArray;
	}

	public String[][] decideRoles(){
		String[][] roles = new String[numberOfPlayers][2];
		Random random = new Random();
		
		int numberOfWolves = random.nextInt(numberOfPlayers/2); // No more than half the players are wolves!
		while (numberOfWolves <= 0){
			numberOfWolves = random.nextInt(numberOfPlayers/2);	
		}
		
		String[] rolesArray = populateRolesArray(numberOfWolves, numberOfPlayers);

		for (int i = 0; i < numberOfPlayers; i++){
			String player = players[i];
			boolean[] indexUsedArray = new boolean[numberOfPlayers];
			roles[i][0] = player;
			boolean indexUsed = true;
			while (indexUsed){
				int randomIndex = random.nextInt(numberOfPlayers);
				roles[i][1] = rolesArray[randomIndex];
				indexUsed = indexUsedArray[randomIndex];
				indexUsedArray[randomIndex] = true;
			}
		}
		return roles;
	}
	public static void main(String[] args){
		String[] names = {"Richard", "Iman", "Ru", "Ellie", "Kajetan", "Willow", "A" , "B" , "C"};
		roleDecider rd = new roleDecider(names);
		
		String[][] finalRoles = rd.decideRoles();
		for (String[] player: finalRoles){
			System.out.println(player[0] + " is a " + player[1]);
		}
	}
}
