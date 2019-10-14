import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class Killer extends UnicastRemoteObject implements KillerService {

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
    	int number = (int) (Math.random() * 3);
		System.out.println(number);
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
            player.waiting("Une partie est d�j� en cours ...");
    	} else {
            player.waiting("En attente d'autres joueurs !");
    	}
    }
  }
  
  public ArrayList<PlayerInterface> getPlayers() {
    return this.players;
  }

}