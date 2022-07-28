package com.assignment.dongjin.support;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpRequest {

    private static final int TIMEOUT = 10000;
    private static final RequestConfig requestConfig = RequestConfig.custom()
            .setConnectionRequestTimeout(TIMEOUT)
            .setConnectTimeout(TIMEOUT)
            .setSocketTimeout(TIMEOUT)
            .build();

    public static Map<Integer, String> request(HttpMethod method,
                                               final String url,
                                               List<NameValuePair> params,
                                               Map<String, String> headers,
                                               List<NameValuePair> bodies,
                                               String jsonBodies) {
        if (Validation.isEmpty(url)) return null;

        RequestBuilder requestBuilder = HttpMethod.createMethod(method);
        if (Validation.isNull(requestBuilder)) return null;

        if (!HttpRequest.buildParam(requestBuilder, url, params)) return null;
        if (!HttpRequest.buildHeaders(requestBuilder, headers)) return null;
        if (!HttpRequest.buildBodies(requestBuilder, bodies, jsonBodies)) return null;

        CloseableHttpClient closeableHttpClient = HttpRequest.buildHttpClientBuilder();
        if (Validation.isNull(closeableHttpClient)) return null;

        CloseableHttpResponse closeableHttpResponse = HttpRequest.response(closeableHttpClient, requestBuilder);
        if (Validation.isNull(closeableHttpResponse)) return null;

        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        if (Validation.isNull(httpEntity)) return null;

        Map<Integer, String> returnMap = new HashMap<>();
        returnMap.put(closeableHttpResponse.getStatusLine().getStatusCode(), HttpRequest.getEntity(closeableHttpResponse));

        return returnMap;
    }

    private static boolean buildParam(RequestBuilder requestBuilder,
                                      final String url,
                                      List<NameValuePair> params) {
        if (Validation.isNull(requestBuilder)) return false;

        if (Validation.nonNull(params)) {
            if (params.size() > 0) {
                URI uriBuilder = HttpRequest.newURIBuilder(url, params);
                if (Validation.nonNull(uriBuilder)) {
                    requestBuilder.setUri(String.valueOf(uriBuilder));
                } else {
                    return false;
                }
            } else {
                requestBuilder.setUri(url);
            }
        } else {
            requestBuilder.setUri(url);
        }

        return true;
    }

    private static boolean buildHeaders(RequestBuilder requestBuilder,
                                        Map<String, String> headers) {
        if (Validation.isNull(requestBuilder)) return false;

        if (Validation.nonNull(headers)) {
            for (String key : headers.keySet()) {
                requestBuilder.setHeader(key, headers.get(key));
            }
        }

        return true;
    }

    private static boolean buildBodies(RequestBuilder requestBuilder,
                                       List<NameValuePair> bodies,
                                       String jsonBodies) {
        if (Validation.isNull(requestBuilder)) return false;

        if (Validation.nonNull(bodies)) {
            if (bodies.size() > 0) {
                UrlEncodedFormEntity urlEncodedFormEntity = HttpRequest.newUrlEncodedFormEntity(bodies);
                if (Validation.nonNull(urlEncodedFormEntity)) {
                    requestBuilder.setEntity(urlEncodedFormEntity);
                } else {
                    return false;
                }
            }
        }

        if (Validation.nonEmpty(jsonBodies)) {
            StringEntity stringEntity = HttpRequest.newStringEntity(jsonBodies);
            if (Validation.nonNull(stringEntity)) {
                requestBuilder.setEntity(stringEntity);
            } else {
                return false;
            }
        }

        return true;
    }

    private static URI newURIBuilder(final String url, List<NameValuePair> params) {
        try {
            return new URIBuilder(url).addParameters(params).build();
        } catch (Exception e) {
            return null;
        }
    }

    private static UrlEncodedFormEntity newUrlEncodedFormEntity(List<NameValuePair> bodies) {
        try {
            return new UrlEncodedFormEntity(bodies, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    private static StringEntity newStringEntity(String value) {
        try {
            return new StringEntity(value, "UTF-8");
        } catch (Exception e) {
            return null;
        }
    }

    private static CloseableHttpClient buildHttpClientBuilder() {
        try {
            return HttpClientBuilder.create().setDefaultRequestConfig(requestConfig).build();
        } catch (Exception e) {
            return null;
        }
    }

    private static CloseableHttpResponse response(CloseableHttpClient closeableHttpClient,
                                                  RequestBuilder requestBuilder) {
        try {
            return closeableHttpClient.execute(requestBuilder.build());
        } catch (Exception e) {
            return null;
        }
    }

    private static String getEntity(CloseableHttpResponse closeableHttpResponse) {
        try {
            return EntityUtils.toString(closeableHttpResponse.getEntity());
        } catch (Exception e) {
            return "";
        }
    }

    public enum HttpMethod {
        GET("GET"), POST("POST"), PUT("PUT"), PATCH("PATCH"), DELETE("DELETE");

        private final String label;

        HttpMethod(final String label) {
            this.label = label;
        }

        public static RequestBuilder createMethod(HttpMethod method) {
            switch (method) {
                case GET:
                    return RequestBuilder.get();
                case POST:
                    return RequestBuilder.post();
                case PUT:
                    return RequestBuilder.put();
                case PATCH:
                    return RequestBuilder.patch();
                case DELETE:
                    return RequestBuilder.delete();
                default:
                    return null;
            }
        }

        public String getLabel() {
            return this.label;
        }
    }
}
