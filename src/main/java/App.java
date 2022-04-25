import academico.AcademicoApp;
import academico.app.projections.MatriculaDto;

import java.io.IOException;

public class App {
    public static void main(String[] args) throws IOException {
        AcademicoApp application = new AcademicoApp.Builder()
                .withEncryptor()
                .withJpaRepository()
                .withEmailSender()
                .criar();

        MatriculaDto novaMatricula = new MatriculaDto("Henrique", "149.864.536-45", "coltxqueota@gmail.com");
        application.controleDeMatriculas.matricular(novaMatricula);
    }
}
