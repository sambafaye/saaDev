package com.yonima.sopeduc;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import org.springframework.beans.factory.annotation.Autowired;

import com.yonima.sopeduc.odt.OdtRGenererReferenceSIOrdre;
import com.yonima.sopeduc.serviceelementaire.SEGenererReference;



@Stateless
public class OrchestrateurGenererReferenceImpl implements OrchestrateurGenererReference {

	@Autowired
	private SEGenererReference service;

	
	@TransactionAttribute(TransactionAttributeType.SUPPORTS)
	public OdtRGenererReferenceSIOrdre genererReferenceSiOrdre() {
		OdtRGenererReferenceSIOrdre odtRGenererReferenceSIOrdre =  new OdtRGenererReferenceSIOrdre();
		String referenceSiOrdre =  service.genererReferenceSiOrdre();
		odtRGenererReferenceSIOrdre.setReferenceSiOrdre(referenceSiOrdre);
		return odtRGenererReferenceSIOrdre.setRetourOK();
		
	}
}
