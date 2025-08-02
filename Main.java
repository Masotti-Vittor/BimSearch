import java.sql.*;
import br.com.bimsearch.windows.LoginWindow;
import br.com.bimsearch.dal.DataAccessLayer;
import br.com.bimsearch.dal.DataBaseSetup;
public class Main{

	public static void main(String[] args){
		DataBaseSetup.setup();
		Connection conn = DataAccessLayer.connector();
		LoginWindow login = new LoginWindow();
		login.setVisible(true);
	}

}
