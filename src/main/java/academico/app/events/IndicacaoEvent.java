package academico.app.events;

import academico.domain.contracts.HasEmail;
import academico.domain.models.Indicacao;
import shared.domain.events.LoggableEvent;
import shared.domain.events.MailEvent;
import shared.app.events.Event;

public class IndicacaoEvent extends Event implements LoggableEvent, MailEvent {
    private Indicacao indicacao;

    public IndicacaoEvent(Indicacao indicacao) {
        this.indicacao = indicacao;
    }

    @Override
    public String generateLog() {
        return null;
    }

    @Override
    public String getMailAddress() {
        HasEmail hasEmail = (HasEmail) indicacao;
        return hasEmail.getEmail();
    }

    @Override
    public String getHeaderMessage() {
        return null;
    }

    @Override
    public String getBodyMessage() {
        return null;
    }
}
