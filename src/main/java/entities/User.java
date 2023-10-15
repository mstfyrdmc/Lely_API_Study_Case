package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
        @JsonProperty("id")
        public int id;
        @JsonProperty("name")
        public String  name;
        @JsonProperty("email")
        public String email;
        @JsonProperty("gender")
        public String gender;
        @JsonProperty("status")
        public String status;


}
