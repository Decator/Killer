import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JMenu extends JPanel {
	private JTextField nameField;

	public JMenu() {
		super();
	
		this.setLayout(null);

	    titleLabel();
	    nameField();
	    joinGameButton();
	    rulesPanel();
	}
	
	public void titleLabel() {
		JLabel titleLabel = new JLabel("<html><p style='text-align: center; font-weight: bold; font-size: 30;'>Le Killer</p></html>");
		titleLabel.setBounds(333, 30, 200, 50);
		this.add(titleLabel);
	}
	
	public void nameField() {
		this.nameField = new JTextField();
		this.nameField.setColumns(10);
		this.nameField.setText("Pseudo");
		this.nameField.setBounds(273, 100, 245, 30);
		this.add(this.nameField);
	}
	
	public void joinGameButton() {
		JButton joinGameButton = new JButton("Rejoignez la game");
		joinGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				MainFrame.frame.getServiceClient().addClient(nameField.getText());
				MainFrame.frame.switchPage("Waiting"); //Go on the Menu page
			}
		});
		joinGameButton.setBounds(320, 150, 150, 40);
		this.add(joinGameButton);
	}
	
	public void rulesPanel() {
		JPanel rulesPanel = new JPanel();
		rulesPanel.setLayout(new GridLayout(10, 1));

		JLabel rulestitleLabel = new JLabel("<html><p style='text-align: center; font-weight: bold; font-size: 20;'>Règles :</p></html>");
		rulestitleLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		rulesPanel.add(rulestitleLabel);	
		JLabel emptySpace = new JLabel("");
		rulesPanel.add(emptySpace);
		JLabel rulesLabel1 = new JLabel(" Le but du jeu du Killer consiste a être le dernier en joueur en vie.");
		rulesPanel.add(rulesLabel1);
		JLabel rulesLabel2 = new JLabel(" Chaque tour vous aurez 6 dés. Vous devrez faire un score d'au moins 30 ou de moins de 12.");
		rulesPanel.add(rulesLabel2);
		JLabel rulesLabel3 = new JLabel(" Pour cela, vous devrez prendre au moins un dé à chaque lancer.");
		rulesPanel.add(rulesLabel3);
		JLabel rulesLabel4 = new JLabel(" Lorsque vous n'avez plus de dés :");
		rulesPanel.add(rulesLabel4);
		JLabel rulesLabel5 = new JLabel("     - Si votre score est supérieur à 12 ou inférieur à 30 alors vos points de vies diminuerons de la différence.");
		rulesPanel.add(rulesLabel5);
		JLabel rulesLabel6 = new JLabel("     - Sinon, vos points de vie augementerons de la différence. De plus, vous pourrez attaquer un adversaire.");
		rulesPanel.add(rulesLabel6);
		JLabel rulesLabel7 = new JLabel(" Vous attaquerez d'autant de points que la différence.");
		rulesPanel.add(rulesLabel7);
		JLabel rulesLabel8 = new JLabel(" Pour ce faire vous lancerez les dés jusqu'à ce qu'il n'y ait plus de dés avec la montant de l'attaque.");
		rulesPanel.add(rulesLabel8);
		
		rulesPanel.setBounds(50, 230, 700, 300);
		rulesPanel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.add(rulesPanel);
	}
}
