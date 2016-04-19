package br.com.constran.util.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;


@WebListener
public class IniciaDS implements ServletContextListener {


	
    public IniciaDS() {

    }
    
    //01/06 
    //12-06
    
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		
		ConnectionFactory.init();
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}
	
	

	

}
