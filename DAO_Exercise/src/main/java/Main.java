import dataaccess.DAO;
import dataaccess.sqlite.DAOFactory;
import dataaccess.sqlite.databaseInitializationDAO;
import view.Navigator;
import view.main.MainView;


public class Main {

    public static void main(String[] args) {

        boolean databaseInitialized = false;

        // Make the DAO Factory
        DAOFactory daoFactory = new DAOFactory();

        // Create a db initialization DAO
        DAO dbInitDAO = new databaseInitializationDAO();
        dbInitDAO = daoFactory.make(dbInitDAO.getClass());
        databaseInitialized = dbInitDAO.setDatabaseInitialized();

        if (databaseInitialized) {
            System.out.println("database initialized");
            Navigator.push(MainView.class, null);
        }
    }
}
