//package com.meiliyaya.web.component;
//
//import com.qcloud.cos.COSClient;
//import com.qcloud.cos.ClientConfig;
//import com.qcloud.cos.auth.BasicCOSCredentials;
//import com.qcloud.cos.auth.COSCredentials;
//import com.qcloud.cos.model.DeleteObjectsRequest;
//import com.qcloud.cos.model.ObjectMetadata;
//import com.qcloud.cos.model.PutObjectResult;
//import com.qcloud.cos.region.Region;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.commons.collections4.CollectionUtils;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import java.io.InputStream;
//import java.util.List;
//import java.util.stream.Collectors;
//
//
//@Slf4j
//@Component
//public class OSSComponent {
//
//    /**
//     * oss 秘钥id
//     */
//    @Value("${oss.tencent.secretId}")
//    private String secretId;
//
//    /**
//     * os 秘钥key
//     */
//    @Value("${oss.tencent.secretKey}")
//    private String secretKey;
//
//    /**
//     * 存储桶名称
//     */
//    @Value("${oss.tencent.bucketName}")
//    private String bucketName;
//
//
//    /**
//     * oss对象存储位置
//     */
//    @Value("${oss.tencent.region}")
//    private String regionString;
//
//    @Value("${oss.tencent.host}")
//    private String host;
//
//
//    private COSClient getCOSClient() {
//        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
//        Region region = new Region(regionString);
//        ClientConfig clientConfig = new ClientConfig(region);
//        COSClient cosClient = new COSClient(cred, clientConfig);
//        return cosClient;
//    }
//
//
//    public String putObject(String key, InputStream inputStream) {
//
//        COSClient cosClient = getCOSClient();
//        ObjectMetadata metadata = new ObjectMetadata();
//        metadata.setContentType("application/octet-stream");
//        PutObjectResult result = cosClient.putObject(bucketName, key, inputStream, metadata);
//
//        log.info("文件已上传;key:{},result:{}", result.getMetadata());
//        return host +"/"+ key;
//    }
//
//    public void deleteObject(List<String> keys) throws Exception {
//        if (CollectionUtils.isEmpty(keys)) {
//            return;
//        }
//        COSClient cosClient = getCOSClient();
//        DeleteObjectsRequest request = new DeleteObjectsRequest(bucketName);
//        request.setKeys(keys.stream().map(s -> new DeleteObjectsRequest.KeyVersion(s)).collect(Collectors.toList()));
//        cosClient.deleteObjects(request);
//
//    }
//
//}
