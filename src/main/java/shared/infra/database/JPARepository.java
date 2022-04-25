package shared.infra.database;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public abstract class JPARepository {
    protected final EntityManagerFactory connection;
    protected final EntityManager manager;

    public JPARepository(EntityManagerFactory connection) {
        this.connection = connection;
        this.manager = connection.createEntityManager();
    }
}
