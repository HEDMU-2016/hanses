package javaee.web;

import java.io.IOException;
import java.time.LocalDate;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaee.domain.ErhvervsKunde;
import javaee.domain.PrivatKunde;
import javaee.ejb.beans.BankBeanLocal;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB private BankBeanLocal ejb;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrivatKunde kunde = new PrivatKunde("Hans", "1234567890");
		kunde.setStartdato(LocalDate.now());
		kunde.setSlutdato(LocalDate.of(9999, 12, 31));
		kunde.setBirthdate(LocalDate.of(1951, 3, 1));
		kunde.setGade("Den ene vej");
		kunde.setEmail("hi@eamv.dk");
		kunde.setTelefon("+45 96700000");
		kunde.setPostnr("7400");
		kunde.setBy("Herning");
		long id = ejb.opretKunde(kunde);
		kunde = new PrivatKunde("Søren", "0123456789");
		kunde.setStartdato(LocalDate.now());
		kunde.setSlutdato(LocalDate.of(9999, 12, 31));
		kunde.setBirthdate(LocalDate.of(1951, 3, 1));
		kunde.setGade("Den ene vej");
		kunde.setEmail("ski@eamv.dk");
		kunde.setTelefon("+45 96700000");
		kunde.setPostnr("7500");
		kunde.setBy("Holstebro");
		id = ejb.opretKunde(kunde);
		ErhvervsKunde ekunde = new ErhvervsKunde("Iversens", "12345678");
		ekunde.setStartdato(LocalDate.now());
		ekunde.setSlutdato(LocalDate.of(9999, 12, 31));
		ekunde.setGade("Den ene vej");
		ekunde.setEmail("kontakt@iversens.dk");
		ekunde.setTelefon("+45 96700000");
		ekunde.setPostnr("7480");
		ekunde.setBy("Vildbjerg");
		id = ejb.opretKunde(ekunde);
		response.getWriter().append("Kunde oprettet. Id: ").append("" + id);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
