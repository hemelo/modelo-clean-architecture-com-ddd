package academico.infra.database.JPA;

import academico.domain.models.Indicacao;
import academico.domain.repositories.IndicacaoRepository;
import shared.infra.database.JPARepository;

import javax.persistence.EntityManagerFactory;

public class JPAIndicacaoRepository extends JPARepository implements IndicacaoRepository {
    public JPAIndicacaoRepository(EntityManagerFactory connection) {
        super(connection);
    }

    @Override
    public void adicionar(Indicacao indicacao) {

    }
}
