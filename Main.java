import br.com.bimsearch.windows.LoginWindow; 
import java.sql.*;
import br.com.bimsearch.dal.DataAccessLayer;
import br.com.bimsearch.dal.DataBaseSetup;
public class Main{

	public static void main(String args[]){
	
		DataBaseSetup.setup();
		Connection conn = DataAccessLayer.connector();

		LoginWindow start; 
		start = new LoginWindow();
		start.setVisible(true);
	}	
}

