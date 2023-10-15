package response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PostErrorResponse {

    public List<Data> data;
    public Meta meta;

    public class Data{
        public String field;
        public String message;
    }

    public class Meta{

    }
}
