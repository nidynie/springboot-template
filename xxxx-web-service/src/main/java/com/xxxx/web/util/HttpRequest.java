package com.xxxx.web.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.springframework.core.io.ClassPathResource;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Author niemengjin
 * @Description //TODO
 * @Date 2019/1/23 4:12 PM
 **/

@Slf4j
public class HttpRequest {


    public static Charset DEFAULT_CHARSET = Charset.forName("UTF-8");

    public static String DEFAULT_RESULT = "";


    /**
     * @Author niemengjin
     * @Description //TODO
     * @Date 2019/1/23 4:13 PM
     **/
    public static String doGet(String url) throws IOException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, "UTF-8");
            }
        }
        return DEFAULT_RESULT;
    }


    public static String doGetWithHttps(String url, String charset) throws IOException {
        try (CloseableHttpClient httpclient = createSSLClientDefault()) {
            HttpGet httpget = new HttpGet(url);
            CloseableHttpResponse response = httpclient.execute(httpget);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                return EntityUtils.toString(entity, charset);
            }
        }
        return DEFAULT_RESULT;
    }


    public static CloseableHttpClient createSSLClientDefault() {
        try {
            //使用 loadTrustMaterial() 方法实现一个信任策略，信任所有证书
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                // 信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            //NoopHostnameVerifier类:  作为主机名验证工具，实质上关闭了主机名验证，它接受任何
            //有效的SSL会话并匹配到目标主机。
            HostnameVerifier hostnameVerifier = NoopHostnameVerifier.INSTANCE;
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, hostnameVerifier);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();

    }


    /**
     * post 请求，异常自行处理
     *
     * @param url    请求url
     * @param params 请求地址
     * @return
     */
    public static String doPost(String url, String params) throws IOException {

        return doPost(url, Collections.EMPTY_LIST, params);
    }


    public static String doPostWithCert(String url, String params) throws Exception {

        return doPostWithCert(url, Collections.EMPTY_LIST, params);
    }


    /**
     * post 请求，异常自行处理
     *
     * @param url     请求地址
     * @param headers 请求头信息
     * @return
     */
    public static String doPostUrlEncoder(String url, List<Header> headers, Map<String, String> params) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (CollectionUtils.isNotEmpty(headers)) {
            httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
        }

        List<BasicNameValuePair> nameValuePairs = new ArrayList<>();
        if (params != null && params.size() > 0) {
            params.forEach((k, v) -> {
                nameValuePairs.add(new BasicNameValuePair(k, v));
            });
        }

        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity, "UTF-8");
        }
        return DEFAULT_RESULT;
    }

    /**
     * post 请求，异常自行处理
     *
     * @param url     请求地址
     * @param headers 请求头信息
     * @param params  请求参数，格式随意，与头部的content-type 一致即可
     * @return
     */
    public static String doPost(String url, List<Header> headers, String params) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (CollectionUtils.isNotEmpty(headers)) {
            httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
        }
        httpPost.setEntity(new StringEntity(params, DEFAULT_CHARSET));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity, "UTF-8");
        }
        return DEFAULT_RESULT;
    }


    public static String doPostWithCert(String url, List<Header> headers, String params) throws Exception {

        KeyStore keyStore = KeyStore.getInstance("PKCS12");

        //加载本地的证书进行https加密传输

        try (InputStream inputStream = new ClassPathResource("apiclient_cert.p12").getInputStream()) {
            keyStore.load(inputStream, "1563656811".toCharArray());  //加载证书密码，默认为商户ID
        } catch (Exception e) {
            log.info("微信退款请求加载证书出错，错误信息:{}", e.getMessage(), e);
        }

        // Trust own CA and all self-signed certs
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, "1563656811".toCharArray())       //加载证书密码，默认为商户ID
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext,
                new String[]{"TLSv1"},
                null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);


        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
        HttpPost httpPost = new HttpPost(url);
        if (CollectionUtils.isNotEmpty(headers)) {
            httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
        }
        httpPost.setEntity(new StringEntity(params, DEFAULT_CHARSET));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();

        if (entity != null) {
            return EntityUtils.toString(entity, "UTF-8");
        }
        return DEFAULT_RESULT;
    }


    /**
     * post 请求，异常自行处理
     *
     * @param url     请求地址
     * @param headers 请求头信息
     * @param params  请求参数，格式随意，与头部的content-type 一致即可
     * @return
     */
    public static InputStream doPostWithInputStream(String url, List<Header> headers, String params) throws IOException {
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        if (CollectionUtils.isNotEmpty(headers)) {
            httpPost.setHeaders(headers.toArray(new Header[headers.size()]));
        }
        httpPost.setEntity(new StringEntity(params, DEFAULT_CHARSET));
        CloseableHttpResponse response = httpclient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        return entity.getContent();

    }


    public static void main(String args[]) throws Exception {

        //System.out.println(doGet("https://www.baidu.com"));

//        List<Header> headers = ImmutableList.of(
//                new BasicHeader("Accept", "application/json, text/javascript, */*; q=0.01"),
//                new BasicHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8"),
//                new BasicHeader("Host", "www.lagou.com"),
//                new BasicHeader("Origin", "https://www.lagou.com"),
//                new BasicHeader("Referer",
//                        "https://www.lagou.com/jobs/list_java?labelWords=&fromSearch=true&suginput="),
//                new BasicHeader("User-Agent",
//                        "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_6) AppleWebKit/537.36 (KHTML," +
//                                " like Gecko) Chrome/69.0.3497.81 Safari/537.36")
//        );
//
//        String url = "https://www.lagou.com/jobs/positionAjax.json?city=%E6%88%90%E9%83%BD&needAddtionalResult=false";

//        List<Header> headers = ImmutableList.of(new BasicHeader("Content-Type", "application/json;charset=utf-8"), new BasicHeader("Accept", "application/json, text/javascript, */*; q=0.01"));
//
//        String result = doPost("http://info.spider.lhjksaas.com/api/taskLostMan/addTaskLostMan/", headers, "{\"cardNum\":\"5110241990xxxx\",\"iname\":\"张无忌\"}");
//
//        System.out.println(JSON.toJSONString(JSON.parse(result), true));

        System.out.println(IdUtil.getId());


    }

}
