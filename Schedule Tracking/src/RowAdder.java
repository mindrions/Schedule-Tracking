import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JToggleButton;

public class RowAdder extends JDialog {

	// hold here for now because I'm tired

	// must be tied to a default color in case no user input
	public Color colorTemp = Color.BLACK;
	public String colorToSend = " ";
	public String reasonToSend = " ";

	public int locx, locy;
	public JPanel panel;
	public String colorStr;
	public GridBagConstraints c = new GridBagConstraints();
	public Dimension d;
	public String[] allColors = new String[] { "RED", "GREEN", "BLUE", "YELLOW", "CYAN", "MAGENTA", "WHITE", "BLACK",
			"GRAY", "LIGHT_GRAY", "DARK_GRAY", "ORANGE", "PINK" };

	public RowAdder() {

		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(300, 50);
		this.setTitle("Add Row");
		this.setResizable(false);
		this.setUndecorated(true);
		Point mouseLocation = MouseInfo.getPointerInfo().getLocation();
		this.setLocation(mouseLocation);

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setBorder(BorderFactory.createBevelBorder(1));
		panel.setBackground(Color.DARK_GRAY);

		addComponents();

	}

	public void addComponents() {

		Color color = Color.BLACK;

		ButtonGroup group = new ButtonGroup();

		for (int i = 0; i < allColors.length; i++) {

			colorStr = allColors[i];

			try {
				color = (Color) Color.class.getField(colorStr).get(null);
			} catch (Exception e) {
				e.printStackTrace();
			}

			JToggleButton toggleButton = new JToggleButton();
			toggleButton.setBackground(color);
			d = new Dimension(15, 15);
			toggleButton.setPreferredSize(d);

			c.gridx = i;
			c.gridy = 0;

			toggleButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// System.out.println("Selected Color: " + toggleButton.getBackground());

					colorTemp = toggleButton.getBackground();

				}
			});

			toggleButton.setBorder(BorderFactory.createBevelBorder(1));

			panel.add(toggleButton);
			group.add(toggleButton);
		}

		c.gridx = 0;
		c.gridy = 1;

		JTextArea enterReason = new JTextArea();
		d = new Dimension(120, 20);
		enterReason.setBorder(BorderFactory.createBevelBorder(1));
		enterReason.setPreferredSize(d);
		enterReason.setBackground(Color.DARK_GRAY);
		enterReason.setForeground(Color.WHITE);
		enterReason.setToolTipText("Enter Reason Here");
		panel.add(enterReason);

		c.gridx = 1;
		c.gridy = 1;

		JButton enterButton = new JButton();
		// enterButton.setBorder(BorderFactory.createBevelBorder(1));
		enterButton.setPreferredSize(d);
		enterButton.setText("Enter");
		enterButton.setForeground(Color.WHITE);
		enterButton.setBackground(Color.DARK_GRAY);
		panel.add(enterButton);

		enterButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				colorToSend = MainFrame.backToString(colorTemp);

				reasonToSend = enterReason.getText();
				// System.out.println("COLOR: " + colorToSend);
				// System.out.println("REASON: " + reasonToSend);
				MainFrame.rightPanel.addSelection(colorToSend, reasonToSend.toUpperCase(), false);

				MainFrame.rightPanel.revalidate();
				MainFrame.rightPanel.repaint();

				setVisible(false);
			}

		});

	}

}
