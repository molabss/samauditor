package br.com.constran.util;



import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import br.com.constran.util.dao.ConnectionFactory;

public abstract class AbstractController {

	public Connection conn = null; 
	
	public void isolarTransacao(int nivel) throws SQLException {
		conn.setTransactionIsolation(nivel);
	}
	
	public void abreConexaoBD() throws SQLException{
		conn = ConnectionFactory.getConnection();

	}
	
	public void fechaConexaoBD(){
		ConnectionFactory.closeConnection(conn);
	}

	
	public void desabilitarBDautoCommit() throws SQLException{
		conn.setAutoCommit(false);
	}
	
	public void habilitarBDautoCommit() throws SQLException{
		conn.setAutoCommit(true);
	}
	
	public void dbCommit() throws SQLException{
		System.out.println("Comitando dados inseridos na base....");
		conn.commit();
		System.out.println("Commit OK!");
	}
	
	
	public void dbRollback(){
		
		try {
			System.out.println("Realizando rollback de dados inseridos na base...");
			if(conn != null){
				conn.rollback();
				System.out.println("Rollback OK!");
			}
			
		} catch (SQLException e) {
			System.out.println("Erro ao realizar rollback: "+e.getMessage());
			e.printStackTrace();
		}
	}
	
	
    private static final SimpleDateFormat brasilDataHoraFormato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    private static final SimpleDateFormat americanoDataHoraFormato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	public static void main(String[] args) throws ParseException {
		
		System.out.println(convertAMtoBR("2015-09-24 11:07:22"));
	}
	
	
	
    public static String convertAMtoBR(String dataHoraAM){

        Date d = null;

        try {

            d = americanoDataHoraFormato.parse(dataHoraAM);
            return brasilDataHoraFormato.format(d);

        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
	
	
	
}
