package shared.infra.auth;

import shared.domain.contracts.PasswordEncryptor;
import org.jasypt.util.password.StrongPasswordEncryptor;

public class PasswordEncryptService implements PasswordEncryptor {
    StrongPasswordEncryptor encryptor;

    public PasswordEncryptService() {
        this.encryptor = new StrongPasswordEncryptor();
    }

    @Override
    public String encryptPassword(String plainPassword) {
        return encryptor.encryptPassword(plainPassword);
    }

    @Override
    public boolean checkPassword(String inputPassword, String cryptoPassword) {
        return encryptor.checkPassword(inputPassword, cryptoPassword);
    }
}