package werewolves;
import java.util.Random;

public class roleDecider{
	private int numberOfPlayers;
	private String[] players;
	private boolean isLovers;
	
	public roleDecider(String[] players){	// Constructor
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
		// TODO Add scaling code, so playing with a hundred people doens't fuck shit up
		rolesArray[numberOfWolves] = "Seer";	// One seer
		rolesArray[numberOfWolves + 1] = "Trapper";	// One trapper
		rolesArray[numberOfWolves + 2] = "Vigilante";	// One vigilante
		rolesArray[numberOfWolves + 3] = "Protector";	// One Protector
		
		if (isLovers){	// There's no need for lovers if there's no lovers.
			rolesArray[numberOfWolves + 4] = "Lover";
			rolesArray[numberOfWolves + 5] = "Lover";
			
			for (int i = numberOfWolves + 6; i < numberOfPlayers; i++)	rolesArray[i] = "Villager";	// Fill the rest of the array with "Villager"
		} else for (int i = numberOfWolves + 4; i < numberOfPlayers; i++)	rolesArray[i] = "Villager";	// Fill the rest of the array with "Villager"

		return rolesArray;
	}

	public String[][] decideRoles(){	// The only real method called by anything.
		// Returns a 2D string array where each top level array is 
		String[][] roles = new String[numberOfPlayers][2];
		Random random = new Random();
		
		int numberOfWolves = random.nextInt(numberOfPlayers/2); // No more than half the players are wolves!
		while (numberOfWolves <= 0){
			numberOfWolves = random.nextInt(numberOfPlayers/2);	
		}
		
		String[] rolesArray = populateRolesArray(numberOfWolves, numberOfPlayers);
		boolean[] usedArraySlots = new boolean[numberOfPlayers];
		
		for (int i = 0; i < numberOfPlayers; i++){
			String player = players[i];
			roles[i][0] = player;
			boolean usedRole = true;
			while (usedRole){
				int randomIndex = random.nextInt(numberOfPlayers);
				roles[i][1] = rolesArray[randomIndex];
				usedRole = usedArraySlots[randomIndex];
				usedArraySlots[randomIndex] = true;
			}
		}
		return roles;
	}
	public static void main(String[] args){
		// Main only called when run directly. This is an object designed to be used in a bigger project.
		// In other words, all this is for TESTING
		String[] names = {"Richard", "Gus", "Ash", "Ben", "Brandon", "Chris", "Connor", "Dennis", "Ellie E", "Iman", "Ru", "Ellie OL", "Kajetan", "Willow", "Jake" , "Moo" , "Nicole", "Sonny", "Caitlin", "Niamh"};	// Testing example names
		roleDecider rd = new roleDecider(names);
		
		String[][] finalRoles = rd.decideRoles();
		for (String[] player: finalRoles){
			System.out.println(player[0] + " is a " + player[1]);
		}
	}
}
