import java.rmi.RemoteException;import java.util.ArrayList;

import javax.swing.JButton;

/**
 * This class allows the Client and the Server to communicate. 
 */
public class ServiceClient {
	private ServerInterface server;
	private Client client;
	private ArrayList<ClientInterface> clients;
	
	ServiceClient(ServerInterface server) {
		this.server = server;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	/**
	 * Create a new Client and initialize the Observer. 
	 * @param name the name of the client's player. 
	 */
	public void addClient(String name) {
		try {
			this.client = new Client(name);
			this.client.getObservableClient().addObserver(MainFrame.frame);
			this.server.addClient(client);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getClients() {
		try {
			this.server.getClients(this.client.getPlayer().getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void rollTheDice(int diceNumber) {
		try {
			this.server.rollTheDice(diceNumber);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setScore(JButton b) {
		try {
			this.server.setScore(Integer.parseInt(b.getText().split(">")[2].split("<")[0]) + this.client.getScore());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void endTurn() {
		try {
			this.server.endTurn();
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
	}

	public void startAttack(String attacker, String target) {
		try {
			this.server.startAttack(attacker, target);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void replay() {
		try {
			this.server.replay();
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}