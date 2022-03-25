package listener;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import db.DBConnection;

/**
 * Application Lifecycle Listener implementation class FirstListener
 *
 */
@WebListener
public class FirstListener implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public FirstListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
        
    	ServletContext servletContext = sce.getServletContext();
 		DBConnection db = new DBConnection();
 		try {
			db.connect();
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
 		
 		//DB Connection
 		servletContext.setAttribute("db", db);
    }
	
}
