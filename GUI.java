
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {
	private Hirsipuu hirsipuu;
	private JPanel panel;
	private JTextArea arvattuTeksti;
	private JProgressBar havioBar;

	public GUI(Hirsipuu hirsipuu) {
		this.hirsipuu = hirsipuu;
	}

	public void start() {
		JFrame frame = new JFrame();

		panel = new JPanel();
		panel.setBackground(Color.BLACK);

		final String kirjaimet = "ABCDEFGHIJKLMNOPQRSTUVWXYZÄÖ";

		for (int i = 0; i < kirjaimet.length(); i++) {
			JButton kirjain = new JButton("" + kirjaimet.charAt(i));
			kirjain.setPreferredSize(new Dimension(50, 50));
			kirjain.setBackground(Color.GRAY);
			kirjain.setFont(new Font("Helvetica", Font.BOLD, 14));
			kirjain.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					JButton source = (JButton) e.getSource();
					boolean ok = hirsipuu.arvaa(source.getText().charAt(0));
					source.setEnabled(false);

					if (ok)
						source.setBackground(Color.WHITE);
					else
						source.setBackground(Color.BLACK);

					update();
				}
			});
			panel.add(kirjain);
		}

		arvattuTeksti = new JTextArea(String.valueOf(hirsipuu.getAvatut()));
		arvattuTeksti.setFont(new Font("Helvetica", Font.PLAIN, 40));
		arvattuTeksti.setBackground(Color.GRAY);
		panel.add(arvattuTeksti);

		havioBar = new JProgressBar();
		havioBar.setPreferredSize(new Dimension(400, 30));
		havioBar.setStringPainted(true);
		havioBar.setForeground(Color.GRAY);
		havioBar.setBackground(Color.BLACK);
		havioBar.setValue(100);
		havioBar.setString("Arvauksia jäljellä: " + hirsipuu.arvauksiaOnJaljella());
		panel.add(havioBar);

		frame.add(panel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 400);
		frame.setVisible(true);
	}

	public void update() {
		arvattuTeksti.setText(String.valueOf(hirsipuu.getAvatut()));
		havioBar.setValue((int)((float) hirsipuu.arvauksiaOnJaljella() / hirsipuu.getArvausMax() * 100));
		havioBar.setString("Arvauksia jäljellä: " + hirsipuu.arvauksiaOnJaljella());
		
		if (hirsipuu.onLoppu()) {
			JOptionPane.showMessageDialog(panel, "VOITIT PELIN :)");
			for (Component c : panel.getComponents()) {
				c.setEnabled(false);
			}
		}

		if (hirsipuu.arvauksiaOnJaljella() <= 0) {
			arvattuTeksti.setText(hirsipuu.sana().toUpperCase());
			JOptionPane.showMessageDialog(panel, "HÄVISIT PELIN :(");
			for (Component c : panel.getComponents()) {
				c.setEnabled(false);
			}
		}
	}
}