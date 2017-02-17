package com.yonima.sopeduc.serviceelementaire;

import java.text.SimpleDateFormat;
import java.util.Date;

//import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yonima.sopeduc.servicepersistence.PersistenceGenererReference;


@Service
public class SEGenererReference{
	
	@Autowired
	private PersistenceGenererReference persistenceGenererReference;

	
	public String genererReferenceSiOrdre() {
		// Format  de la ref SI : ppAAAAQQQnnnnnnnnmmmmmmm
		return "test";
	}
	
}
