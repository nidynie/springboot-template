package com.xxxx.web.wrapper;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StreamUtils;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;


@Slf4j
public class BodyReaderHttpServletRequestWrapper extends HttpServletRequestWrapper {

    private byte[] requestBody = null;//用于将流保存下来

    public BodyReaderHttpServletRequestWrapper(HttpServletRequest request) throws IOException {
        super(request);
        requestBody = StreamUtils.copyToByteArray(request.getInputStream());



        if (requestBody == null || requestBody.length == 0) {
            return;
        }

        //请求参数可能存在空格，接下来使用trim处理掉


            String json = new String(requestBody, "UTF-8");
            if (StringUtils.isNotBlank(json)) {
                try {
                    Map<String, Object> map = JSON.parseObject(json);
                    map.forEach((k, v) -> {
                        if (v != null && v instanceof String) {
                            v = String.valueOf(v).trim();
                        }
                        map.put(k, v);
                    });
                    requestBody = JSON.toJSONString(map).getBytes();
                } catch (Exception e) {
                    log.error("预清除请求参数附带的空格出错，错误信息:{}", e.getMessage());
                }
            }



    }

    @Override
    public ServletInputStream getInputStream() throws IOException {


        final ByteArrayInputStream bais = new ByteArrayInputStream(requestBody);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener readListener) {

            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }


}
