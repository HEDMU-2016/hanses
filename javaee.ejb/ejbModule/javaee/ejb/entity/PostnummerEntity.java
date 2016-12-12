package javaee.ejb.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Entity implementation class for Entity: PostnummerEntity
 *
 */
@Entity(name="Postnummer")

public class PostnummerEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	   
	@Id
	@Column(length=4)
	private String postnr;
	@Column(name="bynavn", length=50, nullable=false)
	private String by;

	public PostnummerEntity() {
		super();
	}  
	
	public PostnummerEntity(String postnr, String by) {
		super();
		this.postnr = postnr;
		this.by = by;
	}
	
	public String getPostnr() {
		return this.postnr;
	}

	public void setPostnr(String postnr) {
		this.postnr = postnr;
	}   
	public String getBy() {
		return this.by;
	}

	public void setBy(String by) {
		this.by = by;
	}
   
}
