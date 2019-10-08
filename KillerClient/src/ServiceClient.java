import java.rmi.RemoteException;

import javax.swing.JOptionPane;

public class ServiceClient {
	private KillerService server;
	private Player player;
	
	ServiceClient(KillerService server) {
		this.server = server;
	}
	
	public void getInformation() throws RemoteException {
		String txt = JOptionPane.showInputDialog("What is your name?");
		
		String response = this.server.getInformation(txt);
		JOptionPane.showMessageDialog(null, response);
	}
	
	public void addPlayer(String name) {
		try {
			this.player = this.server.addPlayer(name);
		} catch (RemoteException e) {
			System.out.println("Votre joueur n'a pas ete cree.");
		}
	}
	
	public Player getPlayer() {
		return this.player;
	}
}