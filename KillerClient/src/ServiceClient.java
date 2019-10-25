import java.rmi.RemoteException;import java.util.ArrayList;

import javax.swing.JButton;

public class ServiceClient {
	private ServerInterface service;
	private Client client;
	private ArrayList<PlayerInterface> players;
	
	ServiceClient(ServerInterface service) {
		this.service = service;
	}
	
	public Client getClient() {
		return this.client;
	}
	
	public void addPlayer(String name) {
		try {
			this.client = new Client(name);
			this.client.getObservablePlayer().addObserver(Frame.frame);
			this.service.addPlayer(client);
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void getPlayers() {
		try {
			this.service.getPlayers(this.client.getName());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void rollTheDice(int diceNumber) {
		try {
			this.service.rollTheDice(diceNumber);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void setScore(JButton b) {
		try {
			this.service.setScore(Integer.parseInt(b.getText().split(">")[2].split("<")[0]) + this.client.getScore());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public void endTurn() {
		try {
			this.service.endTurn();
		} catch (RemoteException e) {
			e.printStackTrace();
		}		
	}

	public void attack(String attacker, String target) {
		try {
			this.service.attack(attacker, target);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}