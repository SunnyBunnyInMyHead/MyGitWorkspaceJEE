package graph;

import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ButtonPanel extends JPanel {
	private JButton button;
	
	public ButtonPanel() {
		super();
		button = new JButton("calculate & repaint");
		this.add(button);
	}
	
	public void addButtonListener(ActionListener listener) {
		button.addActionListener(listener);
	}

}
