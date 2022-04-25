package academico.app.events;

import shared.domain.events.LoggableEvent;
import academico.domain.models.Aluno;
import shared.app.events.Event;

import javax.validation.ConstraintViolation;

public class MatriculaInvalidaEvent extends Event implements LoggableEvent {
    private Iterable<ConstraintViolation<Aluno>> erros;

    public MatriculaInvalidaEvent(Iterable<ConstraintViolation<Aluno>> violations) {
        this.erros = violations;
    }

    @Override
    public String generateLog() {
        StringBuilder builder = new StringBuilder();
        erros.forEach(val -> builder.append(val.getMessage()).append("\n"));
        return builder.toString();
    }
}
