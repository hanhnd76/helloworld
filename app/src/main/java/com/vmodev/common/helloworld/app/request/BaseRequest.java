package com.vmodev.common.helloworld.app.request;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.octo.android.robospice.request.googlehttpclient.GoogleHttpClientSpiceRequest;
import com.vmodev.common.helloworld.app.common.Constant;


import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import roboguice.util.temp.Ln;

/**
 * Created by Kien on 6/12/2014.
 */
public abstract class BaseRequest<Result> extends GoogleHttpClientSpiceRequest<Result> {
    private static final String HOST = Constant.HOST_API;

    private static final JacksonFactory jacksonFactory = new JacksonFactory();

    private String requestBody;

    private List<PostObject> listParam= new ArrayList<PostObject>();

    public BaseRequest(Class<Result> clazz) {
        super(clazz);
    }

    @Override
    public Result loadDataFromNetwork() throws Exception {
        final GenericUrl url = generateUrl();

        HttpRequest request = getRequest(url,listParam);
        request.setParser(jacksonFactory.createJsonObjectParser());

        HttpResponse response = request.execute();

        Result result = response.parseAs(getResultType());

        LoadDataOnBackround(result);

        return result;
    }

    public static String TrimEnd(String input, String charsToTrim)
    {
        return input.replaceAll("[" + charsToTrim + "]+$", "");
    }

    protected abstract void LoadDataOnBackround(Result result);

    abstract protected HttpRequest getRequest(GenericUrl url,List<PostObject> requestBody) throws IOException;

    private GenericUrl generateUrl() {
        final GenericUrl url = new GenericUrl();

        url.setHost(HOST);
        url.setScheme("http");

        for (Field field : this.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(Path.class)) {
                try {
                    url.setRawPath((String) field.get(this));
                } catch (IllegalAccessException e) {
                    Ln.d("unable to access %s", field.getName());
                }
            }

            if (field.isAnnotationPresent(ParamGET.class)) {
                final ParamGET annotation = field.getAnnotation(ParamGET.class);

                String paramName = field.getName();

                if (!annotation.name().equals("")) {
                    paramName = annotation.name();
                }

                try {
                    String value = field.get(this).toString();

                    url.set(paramName, value);

                } catch (IllegalAccessException e) {
                    Ln.d("unable to access %s", field.getName());
                }
            }
            if (field.isAnnotationPresent(ParamPOST.class)) {
                final ParamPOST annotation = field.getAnnotation(ParamPOST.class);

                String paramName = field.getName();

                if (!annotation.name().equals("")) {
                    paramName = annotation.name();
                }

                try {
                    String value = field.get(this).toString();
                    PostObject object = new PostObject();
                    object.setKey(paramName);
                    object.setValue(value);
                    listParam.add(object);

                } catch (IllegalAccessException e) {
                    Ln.d("unable to access %s", field.getName());
                }
            }
        }

        Log.d(Constant.TAG, url.toString());

        return url;
    }

    @Retention(RetentionPolicy.RUNTIME)
    static @interface Path {
    }

    @Retention(RetentionPolicy.RUNTIME)
    static @interface ParamGET {
        String name() default "";
    }

    @Retention(RetentionPolicy.RUNTIME)
    static @interface ParamPOST{
        String name() default "";
    }

    public class PostObject{
        private String key;
        private String value;

        public PostObject(){

        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

}
