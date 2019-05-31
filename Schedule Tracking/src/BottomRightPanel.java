import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

public class BottomRightPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	public JButton add, remove;
	public RowAdder rowAdder;

	public BottomRightPanel() {
		this.setPreferredSize(new Dimension(200, 25));
		this.setBackground(Color.DARK_GRAY);
		this.setBorder(BorderFactory.createBevelBorder(1));

		this.setLayout(new BorderLayout());

		add = new JButton();
		remove = new JButton();

		this.add(add, BorderLayout.WEST);
		this.add(remove, BorderLayout.EAST);

		add.setForeground(Color.WHITE);
		add.setBackground(Color.DARK_GRAY);

		remove.setForeground(Color.WHITE);
		remove.setBackground(Color.DARK_GRAY);

		add.setPreferredSize(new Dimension(100, 25));
		remove.setPreferredSize(new Dimension(100, 25));

		add.setText("Add");
		remove.setText("Remove");

		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rowAdder = new RowAdder();
				rowAdder.setLocation(MouseInfo.getPointerInfo().getLocation());
				rowAdder.setVisible(true);
			}
		});

		remove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MainFrame.rightPanel.removeIt();
			}
		});

	}

}
