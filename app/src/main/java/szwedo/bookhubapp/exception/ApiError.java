package szwedo.bookhubapp.exception;

import java.util.Date;

public class ApiError {
    private Date timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    public Date getTimestamp() {
        return timestamp;
    }

    public int getStatus() {
        return status;
    }

    public String getError() {
        return error;
    }

    public String getMessage() {
        return message;
    }

    public String getPath() {
        return path;
    }
}
