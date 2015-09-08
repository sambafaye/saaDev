package com.yonima.sopeduc.odt;

import java.util.List;

import com.yonima.sopeduc.odt.reponse.OdtReponse;


public class OdtRgenererRefSIOperationFromRefSiOrdre extends OdtReponse {

	private static final long serialVersionUID = -2661127753250865261L;
	private List<String> lstRefSiOperation;
	
	public List<String> getLstRefSiOperation() {
		return lstRefSiOperation;
	}
	public void setLstRefSiOperation(List<String> lstRefSiOperation) {
		this.lstRefSiOperation = lstRefSiOperation;
	}
}
