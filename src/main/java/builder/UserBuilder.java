package builder;

import entities.User;

public class UserBuilder {
    public String  _name;
    public String _email;
    public String _gender;
    public String _status;


    public UserBuilder setName(String name){
        _name=name;
        return this;
    }

    public UserBuilder setEmail(String email){
        _email=email;
        return this;
    }

    public UserBuilder setGender(String gender){
        _gender=gender;
        return this;
    }

    public UserBuilder setStatus(String status){
        _status=status;
        return this;
    }

    public User build(){
        return new User(){{
            this.email=_email;
            this.gender=_gender;
            this.name=_name;
            this.status=_status;
        }};
    }
}
