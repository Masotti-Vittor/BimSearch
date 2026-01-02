package br.com.bimsearch.dal; // if it is in a package, we can easily import between classes and other packages.  
import java.sql.*;
public class DataAccessLayer{
		
// this is the connection method. If I want to run the program in a shared server, local or whatever, I'll just need to change the variables info. 
// Shouldn't it be private to be safer? Find out later. 

	public static Connection connector(){
		java.sql.Connection conec = null;
		
		// driver that will connect our programs
		String driver = "org.h2.Driver";
		// I'll use to set the server. 
		String url = "jdbc:h2:./bimsearch";
		// user who can access the server above. 
		String user = "";
		// password, for now it is empty.
		String password = "";

		// Here I'll add a try block: this way, if an error occurs it won't crash.
		try{
			Class.forName(driver);
			// the line under will "estabelecer" (establish?) a connection with the infos between ();
			conec = DriverManager.getConnection(url, user, password);
			return conec;
			
		}
		catch(Exception e){
			e.printStackTrace();
			return null; 
		}
	}
}

