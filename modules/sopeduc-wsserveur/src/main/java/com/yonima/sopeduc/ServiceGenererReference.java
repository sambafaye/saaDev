package com.yonima.sopeduc;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

import com.yonima.sopeduc.odt.OdtRGenererReferenceSIOrdre;

@WebService
@Stateless
public class ServiceGenererReference implements ServiceGenererReferenceInterface {

	@EJB
	private OrchestrateurGenererReference orchestrateurGenererReference;
	
	
	@Override
	public OdtRGenererReferenceSIOrdre genererReferenceSiOrdre() {
		return orchestrateurGenererReference.genererReferenceSiOrdre();
	}
	

}
