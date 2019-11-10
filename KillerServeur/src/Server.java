import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

/**
 * The server class. 
 */
public class Server extends UnicastRemoteObject implements ServerInterface {
  
	private ArrayList<ClientInterface> clients = new ArrayList<ClientInterface>();

	public Server() throws RemoteException {
		super();
	}

	@Override
	public synchronized void addClient(ClientInterface client) throws RemoteException {
		boolean isSameName = false;
		for(ClientInterface p: this.clients) {
			if(p.getPlayer().getName().equals(client.getPlayer().getName())) {
				client.setServerMessage("Un autre joueur utilise déjà ce pseudo ...");
				isSameName = true;
			}
		}
		if(!isSameName) {
			this.clients.add(client);
			if(this.clients.size() == 4) {
				int number = (int) (Math.random() * 3.99999);
				this.clients.get(number).getPlayer().setCurrentPlayer(true);
				for(ClientInterface p: this.clients) {
					p.initialisation();
				}
			} else {
				if(this.clients.size() > 4) {
					client.setServerMessage("Une partie est déjà en cours ...");
				} else {
					client.setServerMessage("En attente d'autres joueurs !");
				}
			}
		}
	}
  
	@Override
	/**
	 * Tell the client the list of all the clients. 
	 * @param name the client that wants the list of clients
	 */
	public void getClients(String name) throws RemoteException {
		for(ClientInterface p: this.clients) {
			if(p.getPlayer().getName().equals(name)) {
				p.setClients(clients);
			}
		}
	}

	@Override
	public void rollTheDice(int diceNumber) throws RemoteException {
		int[] dices = new int[diceNumber];
		for(int i=0; i < dices.length; i++) {
			dices[i] = (int) (Math.random() * 5.99999) + 1;
		}
		for(ClientInterface p: this.clients) {
			p.setDices(dices);
		}
	}

	@Override
	public void setScore(int score) throws RemoteException {
		for(ClientInterface p: this.clients) {
			p.setScore(score);
		}
	}

	@Override
	public void endTurn() throws RemoteException {
		int index = 0;
		int nbLivePlayers = 0;
		for(int i=0; i < 4; i++) {
			if(this.clients.get(i).getPlayer().getHealthPoints() > 0) {
				nbLivePlayers++;
			}
		}
		if (nbLivePlayers > 1) {
			for(int i=0; i < 4; i++) {
				if(this.clients.get(i).getPlayer().getCurrentPlayer()) {
					if( i != 3) {
						index = i + 1;
						break;
					}
				}
			}
			for(int i=0; i < 2; i++) {
				if(this.clients.get(index).getPlayer().getHealthPoints() <= 0) {
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
					this.clients.get(i).getPlayer().setCurrentPlayer(true);
				} else {
					this.clients.get(i).getPlayer().setCurrentPlayer(false);
				}
			}
			for(int i=0; i < 4; i++) {
				this.clients.get(i).endTurn();
			}
		} else {
			for(ClientInterface p: this.clients) {
				p.endGame();
			}
		}
	}

	@Override
	public void startAttack(String attacker, String target) throws RemoteException {
		ClientInterface attackerPlayer = null;
		ClientInterface targetPlayer = null;
		for(ClientInterface p: this.clients) {
			if(p.getPlayer().getName().equals(attacker)) {
				attackerPlayer = p;
			}
			if(p.getPlayer().getName().equals(target)) {
				targetPlayer = p;
			}
		}
		rollTheDice(6);
		for(ClientInterface p: this.clients) {
			p.startAttack(attackerPlayer, targetPlayer);
		}
	}

	@Override
	public void replay() throws RemoteException {
		for(ClientInterface p: this.clients) {
			p.replay();
		}
		this.clients = new ArrayList<ClientInterface>();
	}
}