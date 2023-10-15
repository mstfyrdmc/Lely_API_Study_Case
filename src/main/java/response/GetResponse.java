package response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
@JsonIgnoreProperties(ignoreUnknown = true)

public class GetResponse {
    public Meta meta;
    public ArrayList<Data> data;

  public class Meta{
    Pagination pagination;
  }

  public class Pagination{
      public int total;
      public int pages;
      public int page;
      public int limit;
      public Links links;
  }

  public class Links{
      public Object previous;
      public String current;
      public String next;
  }

  public class Data{
        public int id;
        public String  name;
        public String email;
        public String gender;
        public String status;
  }

}
