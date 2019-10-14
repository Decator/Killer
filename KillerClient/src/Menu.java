import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu extends JPanel {
	private static Frame frame;
	private JTextField nameField;
	private JButton joinGameButton;

	public Menu(Frame frame) {
		super();
	
		this.setLayout(null);
		this.frame = frame;
	    
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
		this.joinGameButton = new JButton("Rejoignez la game");
		this.joinGameButton.addActionListener(new ActionListener() {
		   public void actionPerformed(ActionEvent e){
			   frame.getServiceClient().addPlayer(nameField.getText());
			   frame.switchPage("Waiting"); //Go on the Menu page
			   }
			});
		this.joinGameButton.setBounds(300, 150, 200, 50);
		this.add(this.joinGameButton);
	}
}
