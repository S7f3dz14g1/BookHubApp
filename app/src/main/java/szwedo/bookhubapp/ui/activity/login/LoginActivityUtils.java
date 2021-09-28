package szwedo.bookhubapp.ui.activity.login;

import szwedo.bookhubapp.models.LoginUserModel;
import szwedo.bookhubapp.utils.LoginStatus;
import szwedo.bookhubapp.utils.StringHelper;

class LoginActivityUtils {
    static LoginStatus validate(LoginUserModel user) {
        if (user.getPassword().isEmpty()) {
            return LoginStatus.EMPTY_PASSWORD;
        } else if (user.getNick().isEmpty()&&user.getEmail().isEmpty()) {
            return LoginStatus.EMPTY_NICK;
        } else if (!StringHelper.validateNickLogin(user.getNick())) {
            return LoginStatus.INCORRECT_NICK;
        } else if (!StringHelper.validatePasswordLogin(user.getPassword())) {
            return LoginStatus.INCORRECT_PASSWORD;
        } else
            return LoginStatus.CORRECT;
    }
}
