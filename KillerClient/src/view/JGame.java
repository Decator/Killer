import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JGame extends JPanel {
	
	private Killer killer;
	private JPanel dicesPanel;
	private JButton rollDiceLabel;
	private JLabel scoreLabel;
	private int numberClick;

	public JGame(Killer killer) {
		super();
		
		this.killer = killer;
		this.dicesPanel = null;
		this.rollDiceLabel = null;
		this.scoreLabel = null;
		this.numberClick = 0;
	
		this.setLayout(null);
		
		playersPanel();
		rollDiceLabel();
		scoreLabel();
	}
	
	public void playersPanel() {
		JPanel playersPanel = new JPanel();
		playersPanel.setLayout(new GridLayout(1, 4));
		for(PlayerInterface p: this.killer.getPlayers()) {
			try {
				playersPanel.add(new JLabel("<html><p style='text-align: center; font-weight: bold;'>"+ p.getName() +"<br/><p style='text-align: center'>"+ p.getHealthPoints() +" points de vie</p></html>"));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		playersPanel.setBounds(100, 50, 700, 100);
		this.add(playersPanel);	
	}
	
	public void rollDiceLabel() {
		if(this.rollDiceLabel != null) {
			this.remove(rollDiceLabel);
		}
		this.rollDiceLabel = new JButton("Lancez les dés");
		rollDiceLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				if(killer.getDices().length - numberClick <= 0) {
					endRoll();
				} else {
					Frame.frame.getServiceClient().rollTheDice(killer.getDices().length - numberClick);
				}
				numberClick = 0;
			}
		});
		if(!this.killer.getCurrentPlayer()) {
			rollDiceLabel.setEnabled(false);
		}
		rollDiceLabel.setBounds(300, 500, 200, 50);
		this.add(rollDiceLabel);
	}
	
	public void dicesPanel() {
		if(this.dicesPanel != null) {
			this.remove(dicesPanel);
		}
		this.dicesPanel = new JPanel();
		dicesPanel.setLayout(new GridLayout(1, this.killer.getDices().length));
		
		if(this.killer.getCurrentPlayer()) {
			for(int i=0; i < this.killer.getDices().length; i++) {
				JButton b = new JButton("<html><p style='text-align: center; font-weight: bold;'>"+ this.killer.getDices()[i] +"</p></html>");
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						Frame.frame.getServiceClient().setScore(b);
						numberClick++;
						b.setEnabled(false);
					}
				});
				dicesPanel.add(b);
			}
		} else {
			for(int i=0; i < this.killer.getDices().length; i++) {
				JButton b = new JButton("<html><p style='text-align: center; font-weight: bold;'>"+ this.killer.getDices()[i] +"</p></html>");
				b.setEnabled(false);
				dicesPanel.add(b);
			}
		}
		dicesPanel.setBounds(250, 275, 300, 50);
		this.add(dicesPanel);
		
		this.revalidate();
		this.repaint();
	}
	
	public void scoreLabel() {
		if(this.scoreLabel != null) {
			this.remove(scoreLabel);
		}
		this.scoreLabel = new JLabel("<html><h2 style='font-weight: bold;'>"+ killer.getScore() +"</h2></html>");
		this.scoreLabel.setBounds(700, 500, 100, 50);
		this.add(this.scoreLabel);
		this.revalidate();
		this.repaint();
	}
	
	public void endRoll() {
		try {
			int losePoint = 0;
			if((this.killer.getHealthPoints() + this.killer.getScore() - 30) >= (this.killer.getHealthPoints() - this.killer.getScore() + 12)) {
				losePoint = this.killer.getHealthPoints() + this.killer.getScore() - 30;
			} else {
				losePoint = this.killer.getHealthPoints() - this.killer.getScore() + 12;
			}
			this.killer.setHealthPoints(losePoint);
			
			if (this.killer.getScore() < 12  || this.killer.getScore() > 30) {
				//Attaque
			} else {
				Frame.frame.getServiceClient().endTurn();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

}
