import java.rmi.RemoteException;

import javax.swing.JPanel;

public class JWinner extends JPanel {
	
	Client client;
	PlayerInterface winner;
	
	public JWinner(Client client) {
		super();
		
		this.setLayout(null);
		
		this.client = client;
		try {
			for(PlayerInterface p: this.client.getPlayers()) {
				if(p.getHealthPoints() > 0) {
					this.winner = p;
					break;
				}
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		this.revalidate();
		this.repaint();
	}
}
