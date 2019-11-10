
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * The Client class contains the Player and the game's information. 
 */
public class Client extends UnicastRemoteObject implements ClientInterface, Serializable {
	
	/**
	 * The dices of the current player. 
	 */
	private int[] dices;
	
	/**
	 * The score of the current player. 
	 */
	private int score;
	
	/**
	 * The clients Observable. 
	 */
	private ObservableClient observableClient;
	
	/**
	 * A message from the server. 
	 */
	private String serverMessage;
	
	/**
	 * A list of all the clients. 
	 */
	private ArrayList<ClientInterface> clients;
	
	/**
	 * The amount of the attack damage. 
	 */
	private int attack;
	
	/**
	 * The attacking client. 
	 */
	private ClientInterface attacker;
	
	/**
	 * The target client. 
	 */
	private ClientInterface target;

	/**
	 * The clients player. 
	 */
	private Player player;

	public Client(String name) throws RemoteException {
		this.player = new Player(name);
		this.serverMessage = "";
		this.clients = new ArrayList<ClientInterface>();
		this.dices = new int[6];
		this.score = 0;
		this.attack = 0;
		
		this.observableClient = new ObservableClient();
	}

	@Override
	public void initialisation() throws RemoteException {
		this.observableClient.notifyFrame("initialisation");
	}

	@Override
	/**
	 * Initialize the attack. 
	 * Set the attacking and target players, set the attack damage amount and reset the score. 
	 */
	public void startAttack(ClientInterface attacker, ClientInterface target) throws RemoteException {
		this.attacker = attacker;
		this.target = target;
		if(this.score < 12) {
			this.attack = 12 - this.score;
		} else {
			this.attack = this.score - 30;
		}
		this.score = 0;
		this.observableClient.notifyFrame("attack");
	}
	
	@Override
	/**
	 * Finish the attack.
	 * Take the damage. 
	 */
	public void attack(int damage) throws RemoteException {
		this.player.setHealthPoints(this.player.getHealthPoints() - damage);
	}
	
	@Override
	public void endTurn() throws RemoteException {
		this.dices = new int[6];
		this.score = 0;
		this.observableClient.notifyFrame("initialisation");
	}
	
	@Override
	public void endGame() throws RemoteException {
		this.observableClient.notifyFrame("endGame");
	}

	@Override
	public void replay() throws RemoteException {
		this.observableClient.notifyFrame("replay");
	}

	public ObservableClient getObservableClient() {
		return this.observableClient;
	}

	@Override
	public PlayerInterface getPlayer() throws RemoteException {
		return this.player;
	}

	public String getServerMessage() {
		return this.serverMessage;
	}
	
	@Override
	public void setServerMessage(String msg) throws RemoteException {
		this.serverMessage = msg;
		this.observableClient.notifyFrame("message");
	}
	
	public ArrayList<ClientInterface> getClients() {
		return this.clients;
	}
	
	@Override
	public void setClients(ArrayList<ClientInterface> clients) {
		this.clients = clients;
		this.observableClient.notifyFrame("setClients");
	}
	
	public int[] getDices() {
		return this.dices;
	}
	
	@Override
	public void setDices(int[] dices) {
		this.dices = dices;
		this.observableClient.notifyFrame("rollTheDice");
	}
	
	public int getScore() {
		return this.score;
	}
	
	@Override
	public void setScore(int score) {
		this.score = score;
		this.observableClient.notifyFrame("score");
	}
	
	public int getAttack() {
		return this.attack;
	}
	
	public ClientInterface getAttackPlayer() {
		return this.attacker;
	}
	
	public ClientInterface getTargetPlayer() {
		return this.target;
	}
}
