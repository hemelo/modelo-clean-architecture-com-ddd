package academico.app.events;

import shared.domain.events.MailEvent;
import academico.app.factories.AlunoFactory;
import academico.domain.contracts.HasEmail;
import academico.domain.models.Aluno;
import shared.domain.events.LoggableEvent;
import shared.app.events.Event;

public class MatriculaEvent extends Event implements LoggableEvent, MailEvent {
    private Aluno aluno;

    public MatriculaEvent(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String generateLog() {
        return "Matrícula do aluno " + aluno.getNome() + " com código " + aluno.getUsername() + " criada com sucesso!";
    }

    @Override
    public String getMailAddress() {
        HasEmail hasEmail = (HasEmail) aluno;
        return hasEmail.getEmail();
    }

    @Override
    public String getHeaderMessage() {
        return "Você foi matriculado no colégio XXXXX";
    }

    @Override
    public String getBodyMessage() {
        return "<h1>Seja bem vindo!</h1>" +
                "<p>Agora você possui acesso ao sistema utilizando sua matrícula " +
                aluno.getUsername() + " ou seu email. " +
                "Sua senha é " + AlunoFactory.defaultPassword + ". E você pode trocá-la clicando " +
                "<a href=\"\">aqui.</a></p>";
    }
}
