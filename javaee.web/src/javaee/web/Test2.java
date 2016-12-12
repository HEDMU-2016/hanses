package javaee.web;

import java.io.IOException;
import java.time.LocalDate;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javaee.domain.Kunde;
import javaee.ejb.beans.BankBeanLocal;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test2")
public class Test2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB private BankBeanLocal ejb;
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Kunde kunde = ejb.hentKunde(1, LocalDate.now()).get();
		System.out.println(kunde);
		kunde = ejb.hentKunde("1234567890", LocalDate.now()).get();
		System.out.println(kunde);
		response.getWriter().append("Kunde fundet: ").append("" + kunde);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
