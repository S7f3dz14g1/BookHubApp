package szwedo.bookhubapp.exception;

import java.io.IOException;
import java.lang.annotation.Annotation;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Response;
import szwedo.bookhubapp.dataAccess.LibraryAccess;

public class ApiErrorParser extends  Exception{
    public static ApiError parseError(Response<?> response) {
        Converter<ResponseBody, ApiError> converter =
                LibraryAccess.getInstance()
                        .getRetrofit()
                        .responseBodyConverter(ApiError.class, new Annotation[0]);

        ApiError error;

        try {
            if(response.errorBody() == null) error = new ApiError();
            else error = converter.convert(response.errorBody());
        } catch (IOException e) {
            return new ApiError();
        }

        return error;
    }
}
