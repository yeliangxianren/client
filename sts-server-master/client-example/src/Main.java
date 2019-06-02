import com.google.gson.Gson;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
//import com.google.gson.Gson;
//import domain.PersonalAccount;
import com.google.gson.GsonBuilder;
import utils.CustomResp;
import utils.HttpCommon;

import com.google.gson.reflect.TypeToken;
import utils.*;

public class Main {

    public static void main(String[] args) throws IOException {
        AdminAccount adminAccount = new AdminAccount("8888888888", "000000");
        String json = new Gson().toJson(adminAccount);
        System.out.println(json);

        CustomResp cr = new HttpCommon().doHttp("/index/all", "GET", json);
        System.out.println(cr.getResultJSON());
        System.out.println(cr.getObjectJSON());
        
//        Type listType = new TypeToken<ArrayList<Integer>>(){}.getType();
//        List<Integer> fundIds = new Gson().fromJson(cr.getObjectJSON(), listType);
      //  System.out.println(cr.getObjectJSON().);
//        CustomResp cr = new HttpCommon().doHttp("/index/all", "GET", null);
//        PersonalAccount account = new Gson().fromJson(cr.getObjectJSON(), PersonalAccount.class);
//        System.out.println(account.getRegisterDate());
        
    }
}
