import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ColorPicker extends JDialog {

	public int locx, locy;
	public JPanel panel;
	public String colorStr;
	public GridBagConstraints c = new GridBagConstraints();
	public Dimension d;
	public String[] allColors = new String[] { "RED", "GREEN", "BLUE", "YELLOW", "CYAN", "MAGENTA", "WHITE", "BLACK",
			"GRAY", "LIGHT_GRAY", "DARK_GRAY", "ORANGE", "PINK" };

	public ColorPicker() {
		this.setModal(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setSize(200, 20);
		this.setTitle("ColorPicker");
		this.setResizable(false);
		this.setUndecorated(true);

		this.setLocation(MouseInfo.getPointerInfo().getLocation());

		panel = new JPanel();
		getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridBagLayout());
		panel.setBackground(Color.DARK_GRAY);

		addButtons();

	}

	public void addButtons() {

		Color color = Color.BLACK;

		for (int i = 0; i < allColors.length; i++) {

			colorStr = allColors[i];

			try {
				color = (Color) Color.class.getField(colorStr).get(null);
			} catch (Exception e) {
				e.printStackTrace();
			}

			JButton button = new JButton();
			button.setBackground(color);

			d = new Dimension(15, 15);
			button.setPreferredSize(d);

			c.gridx = i;
			c.gridy = 0;

			int buttonID = i;
			button.addActionListener(new ActionListener() {

				public void actionPerformed(ActionEvent e) {
					RightPanel.importedColor = button.getBackground();
					// System.out.println(button.getBackground());
					// System.out.println(buttonID);
					setVisible(false);

				}

			});

			panel.add(button);

		}
	}

}
