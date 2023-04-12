package ToDoApp;

import Util.ConnectionFactory;
import java.sql.Connection;

public class App {

    public static void main(String[] args) {
        
        Connection c = ConnectionFactory.getConnection();
        
        ConnectionFactory.closeConnection(c);
    }
}
