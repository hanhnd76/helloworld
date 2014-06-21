package com.vmodev.common.helloworld.app.request;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;

import java.io.IOException;
import java.util.List;

/**
 * Created by Kien on 6/12/2014.
 */
public class GetRequest<Result> extends BaseRequest<Result>{

    public GetRequest(Class<Result> clazz) {
        super(clazz);
    }

    @Override
    protected void LoadDataOnBackround(Result result) {

    }

    @Override
    protected HttpRequest getRequest(GenericUrl url,List<PostObject> requestBody) throws IOException {
        return getHttpRequestFactory().buildGetRequest(url);
    }
}
