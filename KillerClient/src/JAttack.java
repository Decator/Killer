import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.RemoteException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class JAttack extends JPanel {
	
	private Client client;
	private JPanel myPanel;
	private JPanel enemyPanel;
	private JPanel dicesPanel;
	private JButton rollDiceLabel;
	private JButton endTurnLabel;
	private int numberAttack;
	private JLabel scoreLabel;
	private JLabel attackScoreLabel;

	public JAttack(Client client) {
		super();
		
		this.setLayout(null);
		
		this.client = client;
		this.myPanel = null;
		this.enemyPanel = null;
		this.dicesPanel = null;
		this.rollDiceLabel = null;
		this.endTurnLabel = null;
		this.scoreLabel = null;
		this.attackScoreLabel = null;
		this.numberAttack = 0;

		myPanel();
		enemyPanel();
//		dicesPanel();
		rollDiceLabel();
		attackScoreLabel();
		scoreLabel();
		
		this.revalidate();
		this.repaint();
	}
	
	public void enemyPanel() {
		this.enemyPanel = new JPanel();
		this.enemyPanel.setLayout(new GridLayout(1, 1));
		try {
			JLabel playerLabel = new JLabel("<html><p style='text-align: center; font-weight: bold;'>"+ this.client.getTargetPlayer().getName() +"<br/><p style='text-align: center'>"+ this.client.getTargetPlayer().getHealthPoints() +"</p></html>");
			if(this.client.getTargetPlayer().getName().equals(this.client.getName())) {
				playerLabel.setBorder(BorderFactory.createLineBorder(Color.red));
			} else {
				playerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			}
			playerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.enemyPanel.add(playerLabel);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.enemyPanel.setBounds(300, 50, 200, 100);
		this.add(this.enemyPanel);
	}
	
	public void myPanel() {
		this.myPanel = new JPanel();
		this.myPanel.setLayout(new GridLayout(1, 1));
		try {
			JLabel playerLabel = new JLabel("<html><p style='text-align: center; font-weight: bold;'>"+ this.client.getAttackPlayer().getName() +"<br/><p style='text-align: center'>"+ this.client.getAttackPlayer().getHealthPoints() +"</p></html>");
			if(this.client.getAttackPlayer().getName().equals(this.client.getName())) {
				playerLabel.setBorder(BorderFactory.createLineBorder(Color.red));
			} else {
				playerLabel.setBorder(BorderFactory.createLineBorder(Color.black));
			}
			playerLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.myPanel.add(playerLabel);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.myPanel.setBounds(300, 420, 200, 100);
		this.add(this.myPanel);
	}
	
	public void dicesPanel() {
		if(this.dicesPanel != null) {
			this.remove(this.dicesPanel);
		}
		this.dicesPanel = new JPanel();
		this.dicesPanel.setLayout(new GridLayout(1, this.client.getDices().length));
		for(int i=0; i < this.client.getDices().length; i++) {
			JLabel dice = new JLabel("<html><p style='text-align: center; font-weight: bold;'>"+ this.client.getDices()[i] +"</p></html>");
			if(this.client.getAttack() == this.client.getDices()[i]) {
				dice.setBorder(BorderFactory.createLineBorder(Color.blue));
				this.numberAttack++;
				this.client.setScore(this.client.getScore() + this.client.getAttack());
			} else {
				dice.setBorder(BorderFactory.createLineBorder(Color.black));
			}
			dice.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
			this.dicesPanel.add(dice);
		}
		this.scoreLabel();
		if(this.numberAttack == 0) {
			this.endTurnLabel();
		}
		this.dicesPanel.setBounds(250, 225, 300, 50);
		this.add(this.dicesPanel);
		this.revalidate();
		this.repaint();
	}
	
	public void rollDiceLabel() {
		if(this.endTurnLabel != null) {
			this.remove(this.endTurnLabel);
		}
		if(this.rollDiceLabel != null) {
			this.remove(this.rollDiceLabel);
		}
		this.rollDiceLabel = new JButton("Lancez les d�s");
		this.rollDiceLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				int rollDiceNumber = client.getDices().length - numberAttack;
				numberAttack = 0;
				Frame.frame.getServiceClient().rollTheDice(rollDiceNumber);
			}
		});
		try {
			if(!this.client.getName().equals(this.client.getAttackPlayer().getName())) {
				this.rollDiceLabel.setEnabled(false);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		this.rollDiceLabel.setBounds(300, 350, 200, 40);
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
		this.endTurnLabel = new JButton("Fin de l'attaque");
		this.endTurnLabel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				endTurnLabel.setEnabled(false);
				try {
					client.getTargetPlayer().attack(client.getScore());
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
				Frame.frame.getServiceClient().endTurn();
			}
		});
		this.endTurnLabel.setBounds(300, 350, 200, 40);
		this.add(this.endTurnLabel);
		this.revalidate();
		this.repaint();
	}
	
	public void attackScoreLabel() {
		if(this.attackScoreLabel != null) {
			this.remove(attackScoreLabel);
		}
		this.attackScoreLabel = new JLabel("<html><h2 style='font-weight: bold;'> Attaque de "+ client.getAttack() +"</h2></html>");
		this.attackScoreLabel.setBounds(50, 500, 200, 50);
		this.add(this.attackScoreLabel);
		this.revalidate();
		this.repaint();
	}
	
	public void scoreLabel() {
		if(this.scoreLabel != null) {
			this.remove(scoreLabel);
		}
		this.scoreLabel = new JLabel("<html><h2 style='font-weight: bold;'>"+ client.getScore() +"</h2></html>");
		this.scoreLabel.setBounds(700, 500, 100, 50);
		this.add(this.scoreLabel);
		this.revalidate();
		this.repaint();
	}
}
