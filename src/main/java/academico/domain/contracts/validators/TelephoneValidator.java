package academico.domain.contracts.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TelephoneValidator implements
        ConstraintValidator<Telephone, String> {

    @Override
    public void initialize(Telephone contactNumber) {
    }

    @Override
    public boolean isValid(String contactField, ConstraintValidatorContext cxt) {
        return contactField.matches("(?:^\\([0]?[1-9]{2}\\) |^[0]?[1-9]{2}[\\.\\-\\s]?)[9]?[1-9]\\d{3}[\\.\\-\\s]?\\d{4}$");
    }

}