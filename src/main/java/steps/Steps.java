package steps;

import builder.UserBuilder;
import clients.GoRestClient;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import entities.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import response.GetResponse;
import response.PostErrorResponse;
import response.PostResponse;

import java.util.ArrayList;
import java.util.List;


public class Steps {

    private GetResponse getGoRestResponse;
    private User user;
    private PostResponse postGoRestResponse;
    private PostErrorResponse postErrorResponse;
    GoRestClient goRestClient = new GoRestClient();



    @When("^I send a get request")
    public void iSendAGetRequest() {
        getGoRestResponse = goRestClient.getGoRest();
    }

    @Then("^I should see all of data.id values are 4 digit integers and none of them are null")
    public void iShouldSeeAllOfDataIdValuesAreFourDigitAndNoneOfThemAreNull(){
        Assertions.assertTrue( isDataIdsAreNotNull(),"Data ids are null");
        Assertions.assertTrue( isDataIdsValuesFourDigit(),"Data ids are not 4 digits");
    }

    @When("^I send a post request with name \"([^\"]*)\" , email \"([^\"]*)\" , gender \"([^\"]*)\" , status \"([^\"]*)\"")
    public void iSendAPostRequest(String name,String email,String gender,String status){
        user= new UserBuilder()
                .setEmail(email)
                .setName(name)
                .setGender(gender)
                .setStatus(status)
                .build();

       postGoRestResponse= goRestClient.postGoRest(user);
    }

    @Then("^I should see the user created")
    public void iShouldSeeTheUserCreated(){
        Assert.assertEquals(postGoRestResponse.data.email,user.email);
        Assert.assertEquals(postGoRestResponse.data.gender,user.gender);
        Assert.assertEquals(postGoRestResponse.data.name,user.name);
        Assert.assertEquals(postGoRestResponse.data.status,user.status);
    }

    @When("^I send a post request with same name \"([^\"]*)\" , email \"([^\"]*)\" , gender \"([^\"]*)\" , status \"([^\"]*)\"")
    public void iSendAPostRequestWithSameUser(String name,String email,String gender,String status){
        user= new UserBuilder()
                .setEmail(email)
                .setName(name)
                .setGender(gender)
                .setStatus(status)
                .build();

        postErrorResponse= goRestClient.postSameUserGoRest(user);
    }

    @Then("^I should see same user can not created again")
    public void iShouldSeeSameUserCanNotCreatedAgain(){
        Assert.assertEquals(postErrorResponse.data.get(0).message,"has already been taken");
    }

    public boolean isDataIdsAreNotNull(){
        //int can not be null , so we will use Integer
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < getGoRestResponse.data.size(); i++) {
           values.add(getGoRestResponse.data.get(i).id);
        }
        for (int i = 0; i < values.size(); i++) {
            if(values.get(i)!=null){
                return true;
            }
        }
        return false;
    }

    public boolean isDataIdsValuesFourDigit(){
        for (int i = 0; i < getGoRestResponse.data.size(); i++) {
            if(countIntegerDigits(getGoRestResponse.data.get(i).id)==4){
                return true;
            }
        }
        return false;
    }

    public int countIntegerDigits(int number){
        int count = 0;
        while (number!=0){
            number/=10;
            count++;
        }
        return count;
    }


}
