package presentation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import businesslogic.KontaktLogik;
import domain.Kontakt;
import exceptions.KontaktAllreadyExists;
import exceptions.KontaktDoesNotExists;

public class KontaktSaveListener implements ActionListener {
	private KontaktDetail detail = null;
	private	KontaktLogik logik = new KontaktLogik();

	public KontaktSaveListener(KontaktDetail detail) {
		this.detail = detail;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		Kontakt kontakt = detail.getKontakt();
		if (detail.isEdit()) {
			try {
				logik.updateKontakt(kontakt);
				JOptionPane.showMessageDialog(detail, "Kontakt " + kontakt.getNavn() + " opdateret");
				detail.dispose();
			} catch (KontaktDoesNotExists e) {
				JOptionPane.showMessageDialog(this.detail, "Kontakt " + kontakt.getNavn() + " findes ikke",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.detail, "Fejl. Se log",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			}
		} else {
			try {
				logik.createKontakt(kontakt);
				JOptionPane.showMessageDialog(detail, "Kontakt " + kontakt.getNavn() + " oprettet");
				detail.dispose();
			} catch (KontaktAllreadyExists e) {
				JOptionPane.showMessageDialog(this.detail, "Postnummer " + kontakt.getNavn() + " findes allerede",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			} catch (Exception e) {
				e.printStackTrace();
				JOptionPane.showMessageDialog(this.detail, "Fejl. Se log",
						 "Fejl", JOptionPane.ERROR_MESSAGE);
			}
		}

	}

}
