import java.rmi.RemoteException;import java.util.ArrayList;

import javax.swing.JButton;

public class ServiceClient {
	private KillerInterface service;
	private Killer killer;
	private ArrayList<PlayerInterface> players;
	
	ServiceClient(KillerInterface service) {
		this.service = service;
	}
	
	
	public Killer getPlayer() {
		return this.killer;
	}
	
	public void addPlayer(String name) {
		try {
			this.killer = new Killer(name);
			getPlayer().getObservablePlayer().addObserver(Frame.frame);
			this.service.addPlayer(killer);
			
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void getPlayers() {
		try {
			this.service.getPlayers();
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
			this.service.setScore(Integer.parseInt(b.getText().split(">")[2].split("<")[0]) + this.killer.getScore());
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
}