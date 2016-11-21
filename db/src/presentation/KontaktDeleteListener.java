package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import businesslogic.KontaktLogik;
import domain.Kontakt;
import exceptions.KontaktDoesNotExists;

public class KontaktDeleteListener implements ActionListener {
	private KontaktDetail detail = null;
	private	KontaktLogik logik = new KontaktLogik();

	public KontaktDeleteListener(KontaktDetail detail) {
		this.detail = detail;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Kontakt kontakt = detail.getKontakt();
		if (detail.isEdit()) {
			try {
				logik.deleteKontakt(detail.getKontaktid());
				JOptionPane.showMessageDialog(detail, "Kontakt " + kontakt.getNavn() + " slettet");
				detail.dispose();
			} catch (KontaktDoesNotExists e) {
				JOptionPane.showMessageDialog(this.detail, "Kontakt " + kontakt.getNavn() + " findes ikke",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.detail, "Fejl. Se log",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
