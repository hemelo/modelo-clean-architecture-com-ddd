package academico.infra.database.JPA;

import academico.domain.models.Aluno;
import academico.domain.repositories.AlunoRepository;
import shared.infra.database.JPARepository;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.List;

public class JPAAlunoRepository extends JPARepository implements AlunoRepository {
    public JPAAlunoRepository(EntityManagerFactory connection) {
        super(connection);
    }

    @Override
    public void adicionar(Aluno aluno){
        manager.getTransaction().begin();
        manager.persist(aluno);
        manager.getTransaction().commit();
    }

    @Override
    public Aluno buscarPorCpf(String cpf){
        Query query = manager.createQuery("SELECT a from Aluno as a "+
                        "WHERE a.cpf = :cpf").setParameter("cpf", cpf);
        return (Aluno) query.getSingleResult();
    }

    @Override
    public Aluno buscaPorEmail(String email) {
        Query query = manager.createQuery("SELECT a from Aluno as a "+
                "WHERE a.email = :email").setParameter("email", email);
        return (Aluno) query.getSingleResult();
    }

    @Override
    public Aluno buscaPorMatricula(String matricula) {
        Query query = manager.createQuery("SELECT a from Aluno as a "+
                "WHERE a.matricula = :matricula").setParameter("matricula", matricula);
        return (Aluno) query.getSingleResult();
    }

    @Override
    public List<Aluno> todosAlunosMatriculados() {
        Query query = manager.createQuery("SELECT * from Aluno");
        return query.getResultList();
    }
}
