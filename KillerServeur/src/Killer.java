import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Killer extends UnicastRemoteObject implements KillerInterface {

	private static final long serialVersionUID = 1L;
  
	private ArrayList<PlayerInterface> players = new ArrayList<PlayerInterface>();
	private PlayerInterface currentPlayer;

	protected Killer() throws RemoteException {
		super();
	}

	@Override
	public synchronized void addPlayer(PlayerInterface player) throws RemoteException {
		this.players.add(player);
		if(this.players.size() == 4) {
			int number = (int) (Math.random() * 3.99999);
			for(int i=0; i < 4; i++) {
				if(i == number) {
					this.players.get(i).initialisation(true);
					currentPlayer = this.players.get(i);
				} else {
					this.players.get(i).initialisation(false);
				}
			}
		} else {
			if(this.players.size() > 4) {
				player.waiting("Une partie est déjà en cours ...");
			} else {
				player.waiting("En attente d'autres joueurs !");
			}
		}
	}
  
	@Override
	public void getPlayers() throws RemoteException {
		for(PlayerInterface p: this.players) {
			p.setPlayers(players);
		}
	}

	@Override
	public void rollTheDice(int diceNumber) throws RemoteException {
		int[] dices = new int[diceNumber];
		for(int i=0; i < dices.length; i++) {
			dices[i] = (int) (Math.random() * 4.99999) + 1;
		}
		for(PlayerInterface p: this.players) {
			p.setDices(dices);
		}
	}

	@Override
	public void setScore(int score) throws RemoteException {
		for(PlayerInterface p: this.players) {
			p.setScore(score);
		}
	}

	@Override
	public void endTurn() throws RemoteException {
		int index = 0;
		for(int i=0; i < 4; i++) {
			if(this.players.get(i) == this.currentPlayer) {
				if(i != 3) {
					index = i + 1;
					break;
				}
			}
		}
		for(int i=0; i < 4; i++) {
			if(i == index) {
				this.players.get(i).endTurn(true);
				currentPlayer = this.players.get(i);
			} else {
				this.players.get(i).endTurn(false);
			}
		}
	}
}