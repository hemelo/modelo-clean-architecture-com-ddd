package academico.domain.repositories;

import academico.domain.models.Aluno;

import java.util.List;

public interface AlunoRepository {
    void adicionar(Aluno aluno);
    Aluno buscarPorCpf(String cpf);
    Aluno buscaPorEmail(String email);
    Aluno buscaPorMatricula(String matricula);
    List<Aluno> todosAlunosMatriculados();
}
