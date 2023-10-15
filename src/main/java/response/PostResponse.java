package response;

public class PostResponse {
    public Meta meta;
    public Data data;

    public class Data{
        public int id;
        public String  name;
        public String email;
        public String gender;
        public String status;
    }

    public class Meta{
        GetResponse.Pagination pagination;
    }
}
