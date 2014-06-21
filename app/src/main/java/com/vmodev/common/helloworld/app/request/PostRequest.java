package com.vmodev.common.helloworld.app.request;

import com.google.api.client.http.ByteArrayContent;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpHeaders;
import com.google.api.client.http.HttpMediaType;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.MultipartContent;
import com.google.api.client.util.Maps;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by Kien on 6/12/2014.
 */
public class PostRequest<Result> extends BaseRequest<Result> {

    public PostRequest(Class<Result> clazz) {
        super(clazz);
    }

    @Override
    protected void LoadDataOnBackround(Result result) {

    }

    @Override
    protected HttpRequest getRequest(GenericUrl url,List<PostObject> requestBody) throws IOException {
        Map<String, String> parameters = Maps.newHashMap();
        for (PostObject o:requestBody)
            parameters.put(o.getKey(),o.getValue());
        MultipartContent content = new MultipartContent().setMediaType(
                new HttpMediaType("multipart/form-data")
                        .setParameter("boundary", "__END_OF_PART__"));
        for (String name : parameters.keySet()) {
            MultipartContent.Part part = new MultipartContent.Part(
                    new ByteArrayContent(null, parameters.get(name).getBytes()));
            part.setHeaders(new HttpHeaders().set(
                    "Content-Disposition", String.format("form-data; name=\"%s\"", name)));
            content.addPart(part);
        }
        HttpRequest request = getHttpRequestFactory().buildPostRequest(url,content);
        return request;
    }
}
