import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

public class RightPanel extends JPanel {

	// TODO
	// Change JTextLabel of reasons to JTextArea in order to change reason on the
	// fly
	// Make sure to update rowID when doing s
	// Same thing goes for button colors
	// Fix 8 button limit

	// look at swing11 grid bag layout files to reference

	private static final long serialVersionUID = 1L;
	final static boolean RIGHT_TO_LEFT = false;
	final static boolean shouldFill = true;
	final static boolean shouldWeightX = true;

	public static ArrayList<rowID> rowArray = new ArrayList<rowID>();
	public static ArrayList<rowID> selected = new ArrayList<rowID>();
	public String colorStr, ID;
	public Dimension d;
	public JLabel text;
	public JPanel display;
	public JToggleButton toggleButton;
	public ColorPicker colorPicker;
	public static Color importedColor;

	public int x = 0, y = 0;
	public static int arrayCurr = 0;
	public GridBagConstraints c = new GridBagConstraints();

	public RightPanel() {

		this.setPreferredSize(new Dimension(200, 260));
		this.setBackground(Color.DARK_GRAY);
		this.setBorder(BorderFactory.createBevelBorder(1));

		this.setLayout(new GridBagLayout());

	}

	public void addSelection(String colorStr, String reason, Boolean rebuild) {

		rowID temp = new rowID(colorStr, reason);

		if (arrayCurr == 8) {
			System.out.println("MAXIMUM LIMIT REACHED");
			return;
		}

		// default color state (since evaluation is in try catch)
		Color color = Color.BLACK;

		try {
			color = (Color) Color.class.getField(colorStr).get(null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		importedColor = color;
		x = 0;

		// -----------------------------------------

		// LEFTMOST BUTTON \\

		toggleButton = new JToggleButton();
		toggleButton.setForeground(Color.DARK_GRAY);
		toggleButton.setBackground(Color.DARK_GRAY);
		d = new Dimension(10, 10);
		toggleButton.setPreferredSize(d);

		c.gridx = x;
		c.gridy = y;
		c.insets = new Insets(5, 5, 5, 5);
		this.add(toggleButton, c);

		toggleButton.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent ev) {
				if (ev.getStateChange() == ItemEvent.SELECTED) {
					selected.add(temp);
				} else if (ev.getStateChange() == ItemEvent.DESELECTED) {
					selected.remove(temp);
				}
			}
		});

		x++;

		// -----------------------------------------

		// COLOR DISPLAY \\

		JButton button = new JButton();
		d = new Dimension(20, 20);
		button.setPreferredSize(d);
		// button.setBackground(color);

		c.gridx = x;
		c.gridy = y;

		button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {

				if (e.getButton() == 1) {

					// DO LEFT CLICK STUFF

				}

				if (e.getButton() == 3) {

					// DO RIGHT CLICK STUFF

					// holy spaghetti

					colorPicker = new ColorPicker();

					colorPicker.setVisible(true);
					Color prevColor = button.getBackground();

					button.setBackground(importedColor);

					String tempColor = "BLACK";
					tempColor = MainFrame.backToString(prevColor);

					int temp = findInArray(tempColor, reason);

					selected.add(rowArray.get(temp));

					tempColor = MainFrame.backToString(importedColor);
					addSelection(tempColor, reason, false);

					removeIt();
					// printArrayElements();
				}

			}

		});

		button.setBackground(importedColor);
		this.add(button, c);

		x++;

		// -----------------------------------------

		// REASON \\

		text = new JLabel(reason);
		text.setFont(new Font("Arial", Font.BOLD, 12));
		text.setForeground(Color.WHITE);
		d = new Dimension(50, 20);

		c.gridx = x;
		c.gridy = y;

		this.add(text, c);

		y++;

		if (!rebuild) {
			rowArray.add(temp);
			arrayCurr++;
		}
	}

	public void rebuild() {
		this.removeAll();
		this.revalidate();
		this.repaint();

		for (int i = 0; i < arrayCurr; i++) {
			this.addSelection(rowArray.get(i).getColor(), rowArray.get(i).getReason(), true);
		}

	}

	public static void printArrayElements() {
		System.out.println();
		for (int i = 0; i < rowArray.size(); i++) {
			System.out.println("ELEMENT " + i + " ----");
			System.out.println(rowArray.get(i).getColor() + ":" + rowArray.get(i).getReason());
		}

		// System.out.println(arrayCurr);

	}

	public void removeIt() {
		rowID temp;
		for (int i = 0; i < selected.size(); i++) {

			// for some reason rowArray.remove(temp) does not work here

			temp = selected.get(i);
			for (int x = 0; x < rowArray.size(); x++) {
				if (rowArray.get(x).getColor() == temp.getColor() && rowArray.get(x).getReason() == temp.getReason()) {
					rowArray.remove(x);
					arrayCurr--;
				}
			}
		}

		selected.clear();
		rebuild();
		// printArrayElements();
	}

	public void addIt(String colorStr, String reason) {
		reason = reason.toUpperCase();
		addSelection(colorStr, reason, false);
		rowID temp = new rowID(colorStr, reason);
		rowArray.add(rowArray.size(), temp);
		rebuild();
	}

	public int findInArray(String colorStr, String reason) {

		for (int i = 0; i < rowArray.size(); i++) {
			if (rowArray.get(i).getReason() == reason && rowArray.get(i).getColor() == colorStr) {
				return i;
			}
		}

		return -1;
	}

}
