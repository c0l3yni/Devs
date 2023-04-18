package com.tekgs.nextgen.tekegg.data.admin;

import java.util.List;

public class LoginFailureProvider {

    public static LoginFailureProvider getInstance(){
        return new LoginFailureProvider();
    }
    public List<String> get(String loginFailureFileName){
        return LoginFailureRepository.getInstance(loginFailureFileName).query();
    }

    public List<String> get() {
        return LoginFailureRepository.getInstance().query();
    }
}
