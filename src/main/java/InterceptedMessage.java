import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;

import java.util.List;

@Data
public class InterceptedMessage {

    private final static ObjectMapper objectMapper = new ObjectMapper();

    private Request request;

    private Response response;

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    @Data
    public static class Request {

        public String method;

        public String url;

        public List<String[]> headers;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        public byte[] body;

        public byte[] getBody() {
            return body;
        }

        public void setBody(byte[] body) {
            this.body = body;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    @Data
    public static class Response {

        @JsonProperty("status_code")
        public int statusCode;

        public List<String[]> headers;

        @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
        public byte[] body;

        public byte[] getBody() {
            return body;
        }

        public void setBody(byte[] body) {
            this.body = body;
        }
    }
}