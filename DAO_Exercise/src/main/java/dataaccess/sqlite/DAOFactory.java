package dataaccess.sqlite;

import dataaccess.DAO;
import dataaccess.IDAOFactory;

public class DAOFactory implements IDAOFactory {

  @Override
  public DAO make(Class<?> clazz) {
    if (clazz == databaseInitializationDAO.class) {
      return new databaseInitializationDAO();
    } else if (clazz == expenseDAO.class) {
      return new expenseDAO();
    }
    return null;
  }
}
