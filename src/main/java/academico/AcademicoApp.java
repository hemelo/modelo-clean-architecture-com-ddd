package academico;

import academico.app.ControleDeMatriculas;
import shared.domain.contracts.EmailFactory;
import shared.domain.contracts.PasswordEncryptor;
import shared.app.listeners.CriarLog;
import shared.app.listeners.NotificarEmail;
import shared.app.services.EventDispatcher;
import shared.infra.auth.PasswordEncryptService;
import shared.infra.auth.FakePasswordEncryptorService;
import academico.infra.config.Config;
import academico.infra.database.JPA.JPAAlunoRepository;
import academico.infra.database.JPA.JPAIndicacaoRepository;
import academico.infra.database.Runtime.AlunoRepositoryMem;
import academico.infra.database.Runtime.IndicacaoRepositoryMem;
import shared.infra.mail.MailFactory;
import shared.infra.mail.MailQueue;
import shared.infra.mail.MailSender;
import academico.app.ControleDeIndicacoes;
import academico.domain.repositories.AlunoRepository;
import academico.domain.repositories.IndicacaoRepository;

import javax.mail.Session;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.io.IOException;

public class AcademicoApp {
    Config config;
    Session mailSession;
    EmailFactory emailFactory;
    MailQueue mailQueue;
    MailSender mailSender;
    IndicacaoRepository indicacaoRepository;
    AlunoRepository alunoRepository;
    public ControleDeIndicacoes controleDeIndicacoes;
    public ControleDeMatriculas controleDeMatriculas;
    EventDispatcher eventDispatcher;
    Validator fieldValidator;
    PasswordEncryptor encryptor;

    public static class Builder {
        AcademicoApp academicoApp;

        public Builder() throws IOException {
            academicoApp = new AcademicoApp();
            academicoApp.config = new Config();
            ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
            academicoApp.fieldValidator = factory.getValidator();
        }

        public Builder withJpaRepository() {
            EntityManagerFactory e = Persistence.createEntityManagerFactory("academico");
            academicoApp.alunoRepository = new JPAAlunoRepository(e);
            academicoApp.indicacaoRepository = new JPAIndicacaoRepository(e);
            return this;
        }

        public Builder withRuntimeRepository() {
            academicoApp.alunoRepository = new AlunoRepositoryMem();
            academicoApp.indicacaoRepository = new IndicacaoRepositoryMem();
            return this;
        }

        public Builder withEncryptor() {
            academicoApp.encryptor = new PasswordEncryptService();
            return this;
        }

        public Builder withEmailSender() {
            academicoApp.mailSession = Session.getDefaultInstance(academicoApp.config.properties);
            academicoApp.mailQueue = new MailQueue();
            academicoApp.emailFactory = new MailFactory(academicoApp.mailSession, academicoApp.mailQueue, academicoApp.config.email);
            academicoApp.mailSender = new MailSender(academicoApp.mailSession, academicoApp.mailQueue.getQueue(), academicoApp.config.email);
            Thread notificatorSystem = new Thread(academicoApp.mailSender);
            notificatorSystem.start();
            return this;
        }

        public AcademicoApp criar() {

            academicoApp.eventDispatcher = new EventDispatcher()
                    .adicionar(new CriarLog());

            if (academicoApp.alunoRepository == null)
                withRuntimeRepository();

            if (academicoApp.mailQueue != null && academicoApp.eventDispatcher != null)
                academicoApp.eventDispatcher.adicionar(new NotificarEmail(academicoApp.emailFactory));

            if(academicoApp.encryptor == null)
               academicoApp.encryptor = new FakePasswordEncryptorService();

            academicoApp.controleDeMatriculas = new ControleDeMatriculas.Builder()
                    .dispatcher(academicoApp.eventDispatcher)
                    .alunoRepository(academicoApp.alunoRepository)
                    .validator(academicoApp.fieldValidator)
                    .encryptor(academicoApp.encryptor)
                    .criar();

            academicoApp.controleDeIndicacoes = new ControleDeIndicacoes.Builder()
                    .dispatcher(academicoApp.eventDispatcher)
                    .alunoRepository(academicoApp.alunoRepository)
                    .indicacaoRepository(academicoApp.indicacaoRepository)
                    .validator(academicoApp.fieldValidator)
                    .criar();

            return this.academicoApp;
        }
    }
}
