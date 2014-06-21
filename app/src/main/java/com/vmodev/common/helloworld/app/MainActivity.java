package com.vmodev.common.helloworld.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.octo.android.robospice.Jackson2GoogleHttpClientSpiceService;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;
import com.vmodev.common.helloworld.app.common.Constant;
import com.vmodev.common.helloworld.app.request.GetRequest;
import com.vmodev.common.helloworld.app.request.LoginRequest;
import com.vmodev.common.helloworld.app.request.LoginRequestResponse;


public class MainActivity extends Activity {

    private final SpiceManager spiceManager = new SpiceManager(Jackson2GoogleHttpClientSpiceService.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MyLog.i("", "onCreate");
        final EditText editUser = (EditText) findViewById(R.id.editUsername);
        final EditText editPassword = (EditText) findViewById(R.id.editPassword);
        Button btnLogin = (Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("onClick", " click to login button");
                if (editPassword.getText().equals("admin") && editUser.getText().equals("admin")) {
                    //TODO: request to server and submit user name
                    LoginRequest request = new LoginRequest(
                            editUser.getText().toString(),
                            editPassword.getText().toString()
                    );

                    //spiceManager.start(this);
                    //spiceManager.shouldStop();
                    spiceManager.execute(request, new RequestListener<LoginRequestResponse>() {

                        @Override
                        public void onRequestFailure(SpiceException e) {
                            Log.d(Constant.TAG, String.format("error, failed to retrieve category %s", e.toString()));
                        }

                        @Override
                        public void onRequestSuccess(LoginRequestResponse response) {
                            if (response.success) {
                                Log.d(Constant.TAG, "ok");
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        spiceManager.start(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        spiceManager.shouldStop();
    }
}
