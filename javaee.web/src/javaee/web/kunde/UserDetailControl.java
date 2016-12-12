package javaee.web.kunde;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.logging.Logger;

import javaee.ejb.beans.BankBeanLocal;

@Named()
@SessionScoped
public class UserDetailControl implements Serializable {
	private static final long serialVersionUID = 3193006383857093L;
	@EJB private BankBeanLocal ejb;
	@Inject UserDetailModel model;
	private Logger logger = Logger.getLogger(UserDetailControl.class);
	
	@PostConstruct
	public void init() {
	}
	
	public void create() {
		logger.info("method create entered");
		try {
			ejb.opretKunde(model.getUser());
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kunde oprettet"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kunde findes allerede", "Kunde findes allerede"));
		}
	}
	public void update() {
		logger.info("method update entered");
		try {
			ejb.opdaterKunde(model.getUser());
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Kunde opdateret"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Kunde findes ikke", "Kunde findes ikke"));
		}
	}
	


}
