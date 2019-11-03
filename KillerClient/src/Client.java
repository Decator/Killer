
import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
	  
public class Client extends UnicastRemoteObject implements PlayerInterface, Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private int healthPoints;
	private ObservablePlayer observablePlayer;
	private String waiting;
	private boolean currentPlayer;
	private ArrayList<PlayerInterface> players;
	private int[] dices;
	private int score;
	private int attack;
	private PlayerInterface attacker;
	private PlayerInterface target;

	public Client(String name) throws RemoteException {
		this.name = name;
		this.healthPoints = 30;
		this.waiting = "";
		this.currentPlayer = false;
		this.players = new ArrayList<PlayerInterface>();
		this.dices = new int[6];
		this.score = 0;
		this.attack = 0;
		
		this.observablePlayer = new ObservablePlayer();
	}

	public ObservablePlayer getObservablePlayer() {
		return this.observablePlayer;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	@Override
	public boolean getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	@Override
	public int getHealthPoints() throws RemoteException {
		return healthPoints;
	}

	public void setHealthPoints(int healthPoints) throws RemoteException {
		this.healthPoints = healthPoints;
	}

	@Override
	public void waiting(String msg) throws RemoteException {
		this.waiting = msg;
		this.observablePlayer.notifyFrame("waiting");
	}

	@Override
	public void initialisation() throws RemoteException {
		this.observablePlayer.notifyFrame("initialisation");
	}
	
	public String getWaiting() {
		return this.waiting;
	}
	
	@Override
	public void setPlayers(ArrayList<PlayerInterface> players) {
		this.players = players;
		this.observablePlayer.notifyFrame("setPlayers");
	}
	
	public ArrayList<PlayerInterface> getPlayers() {
		return this.players;
	}
	
	@Override
	public void setDices(int[] dices) {
		this.dices = dices;
		this.observablePlayer.notifyFrame("rollTheDice");
	}
	
	public int[] getDices() {
		return this.dices;
	}
	
	@Override
	public void setScore(int score) {
		this.score = score;
		this.observablePlayer.notifyFrame("score");
	}
	
	public int getScore() {
		return this.score;
	}
	
	public int getAttack() {
		return this.attack;
	}
	
	@Override
	public void endTurn() throws RemoteException {
		this.dices = new int[6];
		this.score = 0;
		this.observablePlayer.notifyFrame("initialisation");
	}

	@Override
	public void setCurrentPlayer(boolean currentPlayer) throws RemoteException {
		this.currentPlayer = currentPlayer;
	}
	
	public PlayerInterface getAttackPlayer() {
		return this.attacker;
	}
	
	public PlayerInterface getTargetPlayer() {
		return this.target;
	}

	@Override
	public void startAttack(PlayerInterface attacker, PlayerInterface target) throws RemoteException {
		this.attacker = attacker;
		this.target = target;
		if(this.score < 12) {
			this.attack = 12 - this.score;
		} else {
			this.attack = this.score - 30;
		}
		this.score = 0;
		this.observablePlayer.notifyFrame("attack");
	}
	
	@Override
	public void attack(int damage) throws RemoteException {
		this.healthPoints -= damage;
	}
	
	@Override
	public void endGame() throws RemoteException {
		this.observablePlayer.notifyFrame("endGame");
	}

	@Override
	public void replay() throws RemoteException {
		this.observablePlayer.notifyFrame("replay");
		
	}
}
