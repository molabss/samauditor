package br.com.constran.dao;

import java.sql.Connection;
import java.sql.SQLException;

import br.com.constran.model.LogAuditoria;
import br.com.constran.model.LogCollectionWrapper;
import br.com.constran.util.dao.AbstractDAO;


public class LogAuditoriaDAO extends AbstractDAO {
	

	public LogAuditoriaDAO(Connection conn) {
		super(conn);

	}

	
	public void cadastrarLog(LogCollectionWrapper logCollection) throws SQLException {
		
		StringBuilder insert = new StringBuilder();
		insert.append("INSERT INTO LOGAUDITORIA (MODULO,ACAO,DATAHORA,DISPOSITIVO,CCOBRA) ");
		insert.append("VALUES (?,?,?,?,?)");
		
		conn.setAutoCommit(false);
		
		prepStmt = conn.prepareStatement(insert.toString());
		
		for(LogAuditoria log : logCollection.getListaDeLogs(false)){
			
			prepStmt.setString(1,log.getModulo());
			prepStmt.setString(2,log.getAcao());
			prepStmt.setString(3,log.getDataHora());
			prepStmt.setString(4,log.getDispositivo());
			prepStmt.setInt(5,Integer.parseInt(logCollection.getCcObra()));
			
			prepStmt.addBatch();
		}
		
		prepStmt.executeBatch();
		conn.commit();
		conn.setAutoCommit(true);
	}
	
}
