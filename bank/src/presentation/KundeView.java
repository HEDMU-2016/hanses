package presentation;

import java.time.LocalDate;

import domain.PrivatKunde;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import logic.Bank;
import logic.BankV1;

public class KundeView {
	private Bank logik = new BankV1();
	
	public void start() {
		
		// En hel masse JavaFX
		
		
		Button opret = new Button("Opret");
		opret.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// Lav et kundeobjekt og kald logik
				PrivatKunde kunde = new PrivatKunde("Hans", "1234567890");
				kunde.setBirthdate(LocalDate.of(1951, 3, 1));

				
				
				logik.opretKunde(kunde);
				
			}
		});
		
		
		
		
	}

}
