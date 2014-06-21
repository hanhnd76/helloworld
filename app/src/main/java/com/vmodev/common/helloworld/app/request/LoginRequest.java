package com.vmodev.common.helloworld.app.request;

import com.vmodev.common.helloworld.app.common.Constant;

/**
 * Created by steve on 6/21/14.
 */
public class LoginRequest extends PostRequest {
    @Path
    public final String path = Constant.LOGIN_PATH;

    @ParamPOST(name="_cl_platform")
    public String _cl_platform = "1";

    @ParamPOST(name="_app_id")
    public String _app_id = Constant.APP_ID;

    @ParamPOST(name="_cl_ajax")
    public String _cl_ajax = "1";


    @ParamPOST(name="_cl_rest")
    public String _cl_rest = "1";


    @ParamPOST(name="_cl_submit")
    public String _cl_submit = "1";

    @ParamPOST(name="lname")
    public String lname = "a@g.com";

    @ParamPOST(name="pass")
    public String pass = "123";


    public LoginRequest(String lname, String pass) {
        super(LoginRequestResponse.class);
        //this.lname = lname;
        //this.pass = pass;
    }

}
