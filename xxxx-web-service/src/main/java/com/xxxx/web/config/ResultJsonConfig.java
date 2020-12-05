package com.xxxx.web.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;


/**
 * 响应json 格式化配置
 */
@Configuration
public class ResultJsonConfig implements WebMvcConfigurer {
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

//        if (CollectionUtils.isNotEmpty(converters)) {
//            Iterator<HttpMessageConverter<?>> iterator = converters.iterator();
//            while (iterator.hasNext()) {
//                if (iterator.next() instanceof MappingJackson2HttpMessageConverter) {
//                    iterator.remove();
//                }
//            }
//        }

        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(
                //List字段如果为null,输出为[],而非null
                SerializerFeature.WriteNullListAsEmpty,
                //是否输出值为null的字段,默认为false
                SerializerFeature.WriteMapNullValue,
                //字符串null返回空字符串
                SerializerFeature.WriteNullStringAsEmpty,
                //空布尔值返回false
                SerializerFeature.WriteNullBooleanAsFalse,
                //结果是否格式化,默认为false
                //SerializerFeature.PrettyFormat
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteDateUseDateFormat

        );
        //格式化日期
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm");
        converter.setFastJsonConfig(fastJsonConfig);
        converters.add(0, converter);
    }


    //    @Bean
//    public HttpMessageConverters configureMessageConverters() {
//        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(
//                //List字段如果为null,输出为[],而非null
//                SerializerFeature.WriteNullListAsEmpty,
//                //是否输出值为null的字段,默认为false
//                SerializerFeature.WriteMapNullValue,
//                //字符串null返回空字符串
//                SerializerFeature.WriteNullStringAsEmpty,
//                //空布尔值返回false
//                SerializerFeature.WriteNullBooleanAsFalse
//                //结果是否格式化,默认为false
//                //SerializerFeature.PrettyFormat
//        );
//        //格式化日期
//        //fastJsonConfig.setDateFormat("YYYY-MM-dd HH:mm:ss");
//        converter.setFastJsonConfig(fastJsonConfig);
//        return new HttpMessageConverters(converter);
//    }

//    @Bean
//    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
//        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder
//                .deserializerByType(String.class, new StdScalarDeserializer<Object>(String.class) {
//                    @Override
//                    public String deserialize(JsonParser jsonParser, DeserializationContext ctx)
//                            throws IOException {
//                        return StringUtils.trimWhitespace(jsonParser.getValueAsString());
//                    }
//                });
//    }
}
