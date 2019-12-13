import config.Config;
import dao.ContainerDao;
import entity.Environment;
import exception.DaoException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException, DaoException {

        Class.forName(Config.getProperty(Config.DB_DRIVER));

        ContainerDao dao = new ContainerDao();
//        System.out.println(dao.deleteAllRows());

        List<Environment> environments = dao.getEntity(1);

        int index = environments.isEmpty() ? 0 : environments.get(0).getTemperature();

        System.out.println(environments.get(0).toString());
        System.out.println(dao.postEntity(new Environment(1,++index,60)));
    }
}
