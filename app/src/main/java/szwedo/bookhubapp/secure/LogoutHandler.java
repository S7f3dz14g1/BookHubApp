package szwedo.bookhubapp.secure;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import szwedo.bookhubapp.dataAccess.local.LocalDataAccess;
import szwedo.bookhubapp.ui.activity.login.LoginActivity;
import szwedo.bookhubapp.ui.activity.main.MainActivity;

public class LogoutHandler {

    public static void saveLogout(String message) {
        Context appContext = MainActivity.getAppContext();

        LocalDataAccess.clean();
        showMessage(appContext, message);

        Intent intent = new Intent(appContext, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                Intent.FLAG_ACTIVITY_CLEAR_TASK |
                Intent.FLAG_ACTIVITY_NEW_TASK); // To clean up all activities
        appContext.startActivity(intent);
    }

    private static void showMessage(Context ctx, String message) {
        Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
    }
}
