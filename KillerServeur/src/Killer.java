import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Killer extends UnicastRemoteObject implements KillerInterface {

	private static final long serialVersionUID = 1L;
  
	private ArrayList<PlayerInterface> players = new ArrayList<PlayerInterface>();

	protected Killer() throws RemoteException {
		super();
	}

	@Override
	public synchronized void addPlayer(PlayerInterface player) throws RemoteException {
		boolean isSameName = false;
		for(PlayerInterface p: this.players) {
			if(p.getName().equals(player.getName())) {
				player.waiting("Un autre joueur utilise déjà ce pseudo ...");
				isSameName = true;
			}
		}
		if(!isSameName) {
			this.players.add(player);
			if(this.players.size() == 4) {
				int number = (int) (Math.random() * 3.99999);
				this.players.get(number).setCurrentPlayer(true);
				for(PlayerInterface p: this.players) {
					p.initialisation();
				}
			} else {
				if(this.players.size() > 4) {
					player.waiting("Une partie est déjà en cours ...");
				} else {
					player.waiting("En attente d'autres joueurs !");
				}
			}
		}
	}
  
	@Override
	public void getPlayers(String name) throws RemoteException {
		for(PlayerInterface p: this.players) {
			if(p.getName().equals(name)) {
				p.setPlayers(players);
			}
		}
	}

	@Override
	public void rollTheDice(int diceNumber) throws RemoteException {
		int[] dices = new int[diceNumber];
		for(int i=0; i < dices.length; i++) {
			dices[i] = (int) (Math.random() * 5.99999) + 1;
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
		int nbLivePlayers = 0;
		for(int i=0; i < 4; i++) {
			if(this.players.get(i).getHealthPoints() > 0) {
				nbLivePlayers++;
			}
		}
		if (nbLivePlayers > 1) {
			for(int i=0; i < 4; i++) {
				if(this.players.get(i).getCurrentPlayer()) {
					if( i != 3) {
						index = i + 1;
						break;
					}
				}
			}
			for(int i=0; i < 2; i++) {
				if(this.players.get(index).getHealthPoints() <= 0) {
					index ++;
					if(index == 4) {
						index = 0;
					}
				} else {
					break;
				}
			}
			for(int i=0; i < 4; i++) {
				if(i == index) {
					System.out.println("true: "+ this.players.get(i).getName());
					this.players.get(i).setCurrentPlayer(true);
				} else {
					System.out.println("false: "+ this.players.get(i).getName());
					this.players.get(i).setCurrentPlayer(false);
				}
			}
			for(int i=0; i < 4; i++) {
				this.players.get(i).endTurn();
			}
		} else {
			// GG
		}
	}
}