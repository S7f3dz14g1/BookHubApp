package szwedo.bookhubapp.ui.activity.register;

import szwedo.bookhubapp.models.RegistrationUserModel;
import szwedo.bookhubapp.utils.RegistrationStatus;
import szwedo.bookhubapp.utils.StringHelper;

class RegistrationActivityUtils {
    public static RegistrationStatus validate(RegistrationUserModel user) {
        if (user.getNick().isEmpty()) {
            return RegistrationStatus.EMPTY_NICK;
        } else if (user.getEmail().isEmpty()) {
            return RegistrationStatus.EMPTY_EMAIL;
        } else if (user.getPasswordFirst().isEmpty()) {
            return RegistrationStatus.EMPTY_PASSWORD;
        } else if (user.getPasswordSecond().isEmpty()) {
            return RegistrationStatus.EMPTY_REPEAT_PASSWORD;
        } else if (!StringHelper.validateLoginRegistration(user.getNick())) {
            return RegistrationStatus.INCORRECT_NICK;
        } else if (!StringHelper.validateEmailRegistration(user.getEmail())) {
            return RegistrationStatus.INCORRECT_EMAIL;
        } else if (!StringHelper.validatePasswordRegistration(user.getPasswordFirst())) {
            return RegistrationStatus.INCORRECT_PASSWORD;
        } else if (!StringHelper.validateTwoPasswordRegistration(user.getPasswordFirst()
                , user.getPasswordSecond())) {
            return RegistrationStatus.INCORRECT_PASSWORD2;
        } else {
            return RegistrationStatus.CORRECT;
        }
    }
}
