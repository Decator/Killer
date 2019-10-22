import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JMenu extends JPanel {
	private JTextField nameField;

	public JMenu() {
		super();
	
		this.setLayout(null);
	    
	    nameField();
	    joinGameButton();
	}
	
	public void nameField() {
		this.nameField = new JTextField();
		this.nameField.setColumns(10);
		this.nameField.setBounds(275, 100, 250, 30);
		this.add(this.nameField);
	}
	
	public void joinGameButton() {
		JButton joinGameButton = new JButton("Rejoignez la game");
		joinGameButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e){
				Frame.frame.getServiceClient().addPlayer(nameField.getText());
				Frame.frame.switchPage("Waiting"); //Go on the Menu page
			}
		});
		joinGameButton.setBounds(300, 150, 200, 50);
		this.add(joinGameButton);
	}
}
