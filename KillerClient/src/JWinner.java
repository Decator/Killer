import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JWinner extends JPanel {
	
	public JWinner() {
		super();
		
		this.setLayout(null);
		
		winnerLoserLabel();
		replayButton();
		this.revalidate();
		this.repaint();
	}
	
	public void winnerLoserLabel() {
		JLabel winnerLoser = null;
		try {
			if(Frame.frame.getServiceClient().getClient().getHealthPoints() > 0) {
				winnerLoser = new JLabel("Bravo vous avez gagn� !");
			} else {
				winnerLoser = new JLabel("Dommage, vous avez perdu ...");
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		winnerLoser.setBounds(300, 100, 250, 30);
		this.add(winnerLoser);
	}
	
	public void replayButton() {
		JButton replayButton = new JButton("Rejouer");
		replayButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Frame.frame.getServiceClient().replay();
			}
		});
		replayButton.setBounds(300, 150, 200, 50);
		this.add(replayButton);
	}
}
