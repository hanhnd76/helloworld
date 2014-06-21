package com.vmodev.common.helloworld.app.request;

import com.vmodev.common.helloworld.app.common.Constant;

/**
 * Created by steve on 6/21/14.
 */
public class LoginRequest extends GetRequest {
    @Path
    public final String path = Constant.LOGIN_PATH;

    @ParamPOST(name="lname")
    public String lname = "userName";

    @ParamPOST(name="pass")
    public String pass = "password";


    public LoginRequest(String lname, String pass) {
        super(LoginRequestResponse.class);
        this.lname = lname;
        this.pass = pass;
    }

}
