import com.example.db.connection.EMFFactory;
import com.example.db.entity.Tabs;
import com.example.db.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

public class testDB {

    @Test
    public void start() {
        EntityManagerFactory emf = EMFFactory.getInstance();
        EntityManager em = emf.createEntityManager();

        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<User> cq = cb.createQuery(User.class);
            Root<User> root = cq.from(User.class);
            cq.select(root);

            List<User> users = em.createQuery(cq).getResultList();

            for (User user : users) {

                System.out.println(user.toString());


                Set<Tabs> tabs = user.getTabs();
                System.out.println("Tabs:");
                for (Tabs tab : tabs) {

                    System.out.println(tab.toString());

                }
                System.out.println("------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
            emf.close();
        }
    }
}
