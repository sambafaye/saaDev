/**
 * 
 */
package com.yonima.sopeduc.controller;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;


@ManagedBean
@RequestScoped
public class TestBean implements Serializable {
	private String refSIOrdre;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4104005041486073922L;

	public String getRefSIOrdre(){
		refSIOrdre= "Hello ";
//		 OdtRGenererReferenceSIOrdre genererReferenceSiOrdre = serviceGenererReference.genererReferenceSiOrdre(null);
//		refSIOrdre= genererReferenceSiOrdre.getReferenceSiOrdre();
		return refSIOrdre;
	}
	
	public void setRefSIOrdre(String refSIOrdre) {
		this.refSIOrdre = refSIOrdre;
	}
}
