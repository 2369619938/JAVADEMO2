package cn.hrk.spring.oss;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Data
@Component
@Configuration
public class ConstantProperties implements InitializingBean {
    public static String POINT;
    public static String KEY_ID;
    public static String KEY_SECRET;
    public static String BUCKET_NAME;
    public static String FOLDER;
    public static String WEB_URL;


    @Value("${aliyun.file.endpoint}")
    private String endpoint;

    @Value("${aliyun.file.accessKeyId}")
    private String accessKeyId;

    @Value("${aliyun.file.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliyun.file.folder}")
    private String folder;

    @Value("${aliyun.file.bucketName}")
    private String bucketName;

    @Value("${aliyun.file.webUrl}")
    private String webUrl;

    @Override
    public void afterPropertiesSet() throws Exception {
        POINT=endpoint;
        KEY_ID=accessKeyId;
        KEY_SECRET=accessKeySecret;
        BUCKET_NAME=bucketName;
        FOLDER=folder;
        WEB_URL=webUrl;
    }
}
