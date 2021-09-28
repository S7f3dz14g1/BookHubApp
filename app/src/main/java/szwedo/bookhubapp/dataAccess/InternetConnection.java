package szwedo.bookhubapp.dataAccess;

import android.content.Context;
import android.net.ConnectivityManager;

public class InternetConnection {
    public static boolean isConnection(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }
}
