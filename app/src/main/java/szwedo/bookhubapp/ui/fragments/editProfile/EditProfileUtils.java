package szwedo.bookhubapp.ui.fragments.editProfile;

import szwedo.bookhubapp.models.EditUserModel;
import szwedo.bookhubapp.utils.EditProfileStatus;
import szwedo.bookhubapp.utils.StringHelper;

class EditProfileUtils {
    public static EditProfileStatus validateUserDetails(EditUserModel user){
        if(user.getEmail().isEmpty()){
            return EditProfileStatus.EMPTY_EMAIL;
        }else if(user.getFirstName().isEmpty()){
            return EditProfileStatus.EMPTY_FIRST_NAME;
        }else if(user.getLastName().isEmpty()){
            return EditProfileStatus.EMPTY_LAST_NAME;
        }else if(user.getAddress().isEmpty()){
            return  EditProfileStatus.EMPTY_ADDRESS;
        }else if(user.getPhone().isEmpty()){
            return EditProfileStatus.EMPTY_PHONE;
        }else if(!StringHelper.validateEmailRegistration(user.getEmail())){
            return EditProfileStatus.INCORRECT_EMAIL;
        }else if(!StringHelper.name(user.getFirstName())){
            return EditProfileStatus.INCORRECT_FIRST_NAME;
        }else if(!StringHelper.name(user.getLastName())){
            return EditProfileStatus.INCORRECT_LAST_NAME;
        }else if(!StringHelper.address(user.getNewPassword())){
            return EditProfileStatus.INCORRECT_ADDRESS;
        } else if (!StringHelper.phone(user.getPhone())) {
            return EditProfileStatus.INCORRECT_PHONE;
        } else {
            return EditProfileStatus.CORRECT;
        }
    }
    public static EditProfileStatus validatePassword(String password1,String password2){
        if(password1.isEmpty()){
            return EditProfileStatus.EMPTY_PASSWORD;
        }else if(password2.isEmpty()){
            return EditProfileStatus.EMPTY_PASSWORD2;
        }else if(!StringHelper.validatePasswordRegistration(password1)) {
            return EditProfileStatus.INCORRECT_PASSWORD;
        }else if(!StringHelper.validateTwoPasswordRegistration(password1,password2)){
            return EditProfileStatus.INCORRECT_PASSWORD2;
        }else {
            return EditProfileStatus.CORRECT;
        }
    }
}
