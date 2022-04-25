package academico.app;

import academico.app.events.MatriculaInvalidaEvent;
import academico.app.factories.AlunoFactory;
import academico.app.projections.MatriculaDto;
import academico.domain.models.Aluno;
import academico.app.events.MatriculaEvent;
import shared.app.services.EventDispatcher;
import shared.domain.contracts.PasswordEncryptor;
import academico.domain.repositories.AlunoRepository;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

public class ControleDeMatriculas {
    private AlunoRepository repositorio;
    private EventDispatcher dispatcher;
    private AlunoFactory alunoFactory;
    private PasswordEncryptor encryptor;
    private Validator validator;

    public void matricular(MatriculaDto dadosAluno) {
        if(repositorio.buscarPorCpf(dadosAluno.getCpf()) != null) throw new IllegalArgumentException();

        Aluno aluno = alunoFactory.matricularNovoAlunoPadrao(dadosAluno);
        Set<ConstraintViolation<Aluno>> violations = validator.validate(aluno);

        if(violations.isEmpty()) {
            repositorio.adicionar(aluno);
            dispatcher.dispatch(new MatriculaEvent(aluno));
        }
        else {
            dispatcher.dispatch(new MatriculaInvalidaEvent(violations));
        }
    }

    public static class Builder {

        private ControleDeMatriculas controleDeMatriculas;

        public Builder() {
            this.controleDeMatriculas = new ControleDeMatriculas();
        }

        public Builder alunoRepository(AlunoRepository repositorio){
            this.controleDeMatriculas.repositorio = repositorio;
            return this;
        }

        public Builder dispatcher(EventDispatcher dispatcher){
            this.controleDeMatriculas.dispatcher = dispatcher;
            return this;
        }

        public Builder encryptor(PasswordEncryptor encryptor){
            this.controleDeMatriculas.encryptor = encryptor;
            return this;
        }

        public Builder validator(Validator validator){
            this.controleDeMatriculas.validator = validator;
            return this;
        }

        public ControleDeMatriculas criar() {
            this.controleDeMatriculas.alunoFactory = new AlunoFactory(this.controleDeMatriculas.encryptor);
            return this.controleDeMatriculas;
        }
    }
}
