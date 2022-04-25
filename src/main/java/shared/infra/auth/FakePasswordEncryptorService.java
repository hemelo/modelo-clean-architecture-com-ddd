package shared.infra.auth;

import shared.domain.contracts.PasswordEncryptor;

public class FakePasswordEncryptorService implements PasswordEncryptor {
    @Override
    public String encryptPassword(String plainPassword) {
        return plainPassword;
    }

    @Override
    public boolean checkPassword(String inputPassword, String cryptoPassword) {
        return true;
    }
}
