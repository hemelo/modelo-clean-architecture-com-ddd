package academico.infra.database.Runtime;

import java.util.ArrayList;
import java.util.List;

import academico.domain.models.Aluno;
import academico.domain.repositories.AlunoRepository;

public class AlunoRepositoryMem implements AlunoRepository {

	private List<Aluno> matriculados = new ArrayList<>();
	
	@Override
	public void adicionar(Aluno aluno) {
		this.matriculados.add(aluno);
	}

	@Override
	public Aluno buscarPorCpf(String cpf){
		return this.matriculados.stream()
				.filter(a -> a.getCpf().equals(cpf))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Aluno buscaPorEmail(String email) {
		return this.matriculados.stream()
				.filter(a -> a.getEmail().equals(email))
				.findFirst()
				.orElse(null);
	}

	@Override
	public Aluno buscaPorMatricula(String matricula) {
		return this.matriculados.stream()
				.filter(a -> a.getUsername().equals(matricula))
				.findFirst()
				.orElse(null);
	}

	@Override
	public List<Aluno> todosAlunosMatriculados() {
		return this.matriculados;
	}

}
