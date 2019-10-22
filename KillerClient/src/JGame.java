import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JGame extends JPanel {
	
	private Killer killer;
	private JPanel dicesPanel;
	private JButton rollDiceLabel;
	private JButton endTurnLabel;
	private JPanel myPanel;
	private JPanel playersPanel;
	private JLabel scoreLabel;
	private int numberClick;

	public JGame(Killer killer) {
		super();
		
		this.setLayout(null);
		
		this.killer = killer;
		this.dicesPanel = null;
		this.rollDiceLabel = null;
		this.endTurnLabel = null;
		this.myPanel = null;
		this.playersPanel = null;
		this.scoreLabel = null;
		this.numberClick = 0;
		
		myPanel();
		playersPanel();
		rollDiceLabel();
		scoreLabel();
		this.revalidate();
		this.repaint();
	}
	
	public void myPanel() {
		this.myPanel = new JPanel();
		this.myPanel.setLayout(new GridLayout(1, 1));
		try {
			JLabel playerLabel = new JLabel("<html><p style='text-align: center; font-weight: bold;'>"+ this.killer.getName() +"<br/><p style='text-align: center'>"+ this.killer.getHealthPoints() +"</p></html>");
			if(this.killer.getCurrentPlayer()) {
				playerLabel.setBorder(BorderFactory.createLineBorder(Color.red));
			} else {
				System.out.println("else");
				playerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			}
			playerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.myPanel.add(playerLabel);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.myPanel.setBounds(30, 400, 220, 120);
		this.add(this.myPanel);
	}
	
	public void playersPanel() {
		this.playersPanel = new JPanel();
		this.playersPanel.setLayout(new GridLayout(1, 3));
		for(PlayerInterface p: this.killer.getPlayers()) {
			try {
				if(!p.getName().equals(this.killer.getName())) {
					JLabel playerLabel = new JLabel("<html><p style='text-align: center; font-weight: bold;'>"+ p.getName() +"<br/><p style='text-align: center'>"+ p.getHealthPoints() +"</p></html>");
					if(p.getCurrentPlayer()) {
						playerLabel.setBorder(BorderFactory.createLineBorder(Color.red));
					} else {
						playerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
					}
					playerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
					this.playersPanel.add(playerLabel);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		this.playersPanel.setBounds(70, 50, 660, 120);
		this.add(this.playersPanel);
	}
	
	public void rollDiceLabel() {
		if(this.endTurnLabel != null) {
			this.remove(this.endTurnLabel);
		}
		if(this.rollDiceLabel != null) {
			this.remove(this.rollDiceLabel);
		}
		this.rollDiceLabel = new JButton("Lancez les dés");
		this.rollDiceLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
//				if(killer.getDices().length - numberClick <= 0) {
//					endRoll();
//				} else {
					Frame.frame.getServiceClient().rollTheDice(killer.getDices().length - numberClick);
//				}
				numberClick = 0;
				rollDiceLabel.setEnabled(false);
			}
		});
		if(!this.killer.getCurrentPlayer()) {
			this.rollDiceLabel.setEnabled(false);
		}
		this.rollDiceLabel.setBounds(300, 500, 200, 50);
		this.add(this.rollDiceLabel);
		
		this.revalidate();
		this.repaint();
	}
	
	public void endTurnLabel() {
		if(this.endTurnLabel != null) {
			this.remove(this.endTurnLabel);
		}
		if(this.rollDiceLabel != null) {
			this.remove(this.rollDiceLabel);
		}
		this.endTurnLabel = new JButton("Fin du tour");
		this.endTurnLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
//				if(killer.getDices().length - numberClick <= 0) {
					endRoll();
//				} else {
//					Frame.frame.getServiceClient().rollTheDice(killer.getDices().length - numberClick);
//				}
				numberClick = 0;
				endTurnLabel.setEnabled(false);
			}
		});
		if(!this.killer.getCurrentPlayer()) {
			this.endTurnLabel.setEnabled(false);
		}
		this.endTurnLabel.setBounds(300, 500, 200, 50);
		this.add(this.endTurnLabel);
		
		this.revalidate();
		this.repaint();
	}
	
	public void dicesPanel() {
		if(this.dicesPanel != null) {
			this.remove(this.dicesPanel);
		}
		this.dicesPanel = new JPanel();
		this.dicesPanel.setLayout(new GridLayout(1, this.killer.getDices().length));
		
		if(this.killer.getCurrentPlayer()) {
			for(int i=0; i < this.killer.getDices().length; i++) {
				JButton b = new JButton("<html><p style='text-align: center; font-weight: bold;'>"+ this.killer.getDices()[i] +"</p></html>");
				b.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e){
						Frame.frame.getServiceClient().setScore(b);
						numberClick++;
						b.setEnabled(false);
						if(killer.getDices().length - numberClick <= 0) {
							endTurnLabel();
						} else {
							rollDiceLabel.setEnabled(true);
						}
					}
				});
				this.dicesPanel.add(b);
			}
		} else {
			for(int i=0; i < this.killer.getDices().length; i++) {
				JButton b = new JButton("<html><p style='text-align: center; font-weight: bold;'>"+ this.killer.getDices()[i] +"</p></html>");
				b.setEnabled(false);
				this.dicesPanel.add(b);
			}
		}
		this.dicesPanel.setBounds(250, 275, 300, 50);
		this.add(this.dicesPanel);
		
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
			
			if (this.killer.getScore() < 12) {
				attackButton();
			} else if (this.killer.getScore() > 30) {
				attackButton();
			} else {
				Frame.frame.getServiceClient().endTurn();
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public void attackButton() {
		this.playersPanel = null;
		this.playersPanel.setLayout(new GridLayout(1, 3));
		for(PlayerInterface p: this.killer.getPlayers()) {
			try {
				if(!p.getName().equals(this.killer.getName())) {
					JButton playerButton = new JButton("<html><p style='text-align: center; font-weight: bold;'>"+ p.getName() +"<br/><p style='text-align: center'>"+ p.getHealthPoints() +"</p></html>");
					playerButton.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
					if(p.getHealthPoints() <= 0) {
						playerButton.setEnabled(false);
					}
					this.playersPanel.add(playerButton);
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		this.playersPanel.setBounds(70, 50, 660, 120);
		this.add(this.playersPanel);
		this.revalidate();
		this.repaint();
	}
}
