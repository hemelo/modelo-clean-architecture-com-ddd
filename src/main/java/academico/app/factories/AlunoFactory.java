package academico.app.factories;

import academico.app.projections.MatriculaDto;
import academico.domain.models.Aluno;
import shared.domain.contracts.PasswordEncryptor;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class AlunoFactory {
    public static final String defaultPassword = "School" + LocalDateTime.now(ZoneId.of("UTC-3")).getYear();
    private final PasswordEncryptor encryptor;

    public AlunoFactory(PasswordEncryptor encryptor) {
        this.encryptor = encryptor;
    }

    public Aluno matricularNovoAlunoPadrao(MatriculaDto alunoData) {
        String cryptoPassword = encryptor.encryptPassword(defaultPassword);
        return new Aluno.Builder(alunoData.getNome(), alunoData.getCpf())
                .email(alunoData.getEmail())
                .username(MatriculaCodeFactory.getNewCode())
                .password(cryptoPassword)
                .criar();
    }

    private static class MatriculaCodeFactory {
        public static String getNewCode(){
            return LocalDateTime
                    .now(ZoneId.of("UTC-3"))
                    .format(DateTimeFormatter.ofPattern("yyMMddHHmmssS"));
        }
    }
}
