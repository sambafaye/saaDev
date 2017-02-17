package com.yonima.sopeduc.servicepersistence;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.yonima.sopeduc.resources.ReloadableConfig;

@Repository
public class PersistenceGenererReference {

	@Resource(name="jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("persistenceGenererReferenceDAO")
	private ReloadableConfig persistenceGenererReference;
	
	public String getIdOrdre() {
		String sqlQuery = persistenceGenererReference.getPropertyStr("SQL.getIdOrdre");
		long idOrdre = jdbcTemplate.queryForLong(sqlQuery);	
		return "" + idOrdre;
	}

	public String getIdOperation() {
		String sqlQuery = persistenceGenererReference.getPropertyStr("SQL.getIdOperation");
		long idSequence = jdbcTemplate.queryForLong(sqlQuery);	
		return "" + idSequence;
	}
	
	public String getIdRefSI() {
		String sqlQuery = persistenceGenererReference.getPropertyStr("SQL.getRefSI");
		long refSI = jdbcTemplate.queryForLong(sqlQuery);	
		return "" + refSI;
	}
		
	
}
