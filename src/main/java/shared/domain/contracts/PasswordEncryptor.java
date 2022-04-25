package shared.domain.contracts;

public interface PasswordEncryptor {
    String encryptPassword(String plainPassword);
    boolean checkPassword(String inputPassword, String cryptoPassword);
}
