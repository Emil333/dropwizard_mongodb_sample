import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponse {


    private int statusCode;
    private ApiData data;

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public ApiData getData() {
        return data;
    }

    public void setData(ApiData data) {
        this.data = data;
    }
}
