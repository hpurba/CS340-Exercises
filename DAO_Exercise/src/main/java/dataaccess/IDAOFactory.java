package dataaccess;

public interface IDAOFactory {
  DAO make (Class<?> clazz);
}
