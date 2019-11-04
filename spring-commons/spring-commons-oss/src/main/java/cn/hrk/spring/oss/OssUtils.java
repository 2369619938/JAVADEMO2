package cn.hrk.spring.oss;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.internal.OSSHeaders;
import com.aliyun.oss.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


public class OssUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(OssUtils.class);

    private static String ENDPOINT=ConstantProperties.POINT;
    private static String ACCESS_KEY_ID = ConstantProperties.KEY_ID;
    private static String ACCESS_KEY_SECRET =ConstantProperties.KEY_SECRET;
    private static String BUCKET_NAME = ConstantProperties.BUCKET_NAME;
    private static String FILE_DIR = ConstantProperties.FOLDER;
    private static String WEB_URL=ConstantProperties.WEB_URL;
    private static OSS ossClient = new OSSClientBuilder().build(ENDPOINT, ACCESS_KEY_ID, ACCESS_KEY_SECRET);

    public static String getcontentType(String Filename) {
        if (Filename.equalsIgnoreCase(".jpg")|| Filename.equalsIgnoreCase(".png") || Filename.equalsIgnoreCase(".jpeg")){
            return "image/jpeg";
        }
        if (Filename.equalsIgnoreCase(".gif")){
            return "image/gif";
        }
        return "image/jpeg";
    }




    public static FileR upload(File file) {
        LOGGER.info("OSS文件上传开始:" + file.getName());
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());
        if (null == file) {
            return null;
        }
        try {
            String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1);
            PutObjectResult result = null;
            //创建文件路径
            String fileUrl = FILE_DIR + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + file.getName());
            //上传文件
            result = uploadFileOSS(file, fileUrl);
            if(null != result){
                LOGGER.info("OSS文件上传成功,OSS地址："+fileUrl);
                FileR r= new FileR(
                        file.length(),//文件大小
                        fileUrl,//文件的绝对路径
                        WEB_URL +"/" + fileUrl,//文件的web访问地址
                        suffix,//文件后级
                        BUCKET_NAME,//存储的bucket
                        file.getName(),//原文件名
                        FILE_DIR//存储的文件夹
                );
                r.setRequest(result);
                return r;
            }
        }catch (OSSException oe){
            LOGGER.error(oe.getMessage());
        }catch (ClientException ce){
            LOGGER.error(ce.getMessage());
        }finally {
            //关闭
            //ossClient.shutdown();
        }
        return null;
    }
    public static FileR upload(List<MultipartFile> files){
        LOGGER.info("OSS文件上传开始");
        String imgsPath="";
        String imgsWebUrl = "";

        FileR fileResult = null;
        if (files !=null && files.size() >0){
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String dateStr = format.format(new Date());

            fileResult = new FileR(
                    null,
                    null,
                    null,
                    null,
                    BUCKET_NAME,
                    null,
                    FILE_DIR
            );
            for (int i=0;i<files.size();i++){
                MultipartFile file = files.get(i);
                String originalFilename = file.getOriginalFilename();
                String suffix = file.getName().substring(file.getName().lastIndexOf(".")+1).toLowerCase();

                String fileUrl = FILE_DIR+"/"+(dateStr+"/"+UUID.randomUUID().toString().replace("-","")+"-"+originalFilename);
                PutObjectResult result = null;
                try {
                    result = uploadFileOSS(file.getInputStream(),fileUrl);
                    if(result !=null){
                        imgsPath+= i==0?fileUrl:"," + fileUrl;
                        imgsWebUrl+= i==0?WEB_URL+"/"+fileUrl:","+WEB_URL+"/"+fileUrl;
                        System.out.println(i);
                        System.out.println(i+":"+imgsWebUrl);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            System.out.println(imgsWebUrl);
            fileResult.setFileAPUrl(imgsPath);
            fileResult.setWebUrl(imgsWebUrl);
        }
        return fileResult;
    }

    public static FileR upload(MultipartFile file){
        LOGGER.info("OSS文件上传开始:" + file.getName());
        FileR fileR=null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = format.format(new Date());

        String originalFilename=file.getOriginalFilename();
        String suffix = file.getName().substring(file.getName().lastIndexOf(".") + 1).toLowerCase();

        //创建文件路径
        String fileUrl = FILE_DIR + "/" + (dateStr + "/" + UUID.randomUUID().toString().replace("-", "") + "-" + originalFilename);
        PutObjectResult result=null;

        try {
            result=uploadFileOSS(file.getInputStream(),fileUrl);

            if(null != result){
                LOGGER.info("OSS文件上传成功,OSS地址："+fileUrl);
                fileR = new FileR(
                        file.getSize(),//文件大小
                        fileUrl,//文件的绝对路径
                        WEB_URL +"/" + fileUrl,//文件的web访问地址
                        suffix,//文件后级
                        BUCKET_NAME,//存储的bucket
                        file.getName(),//原文件名
                        FILE_DIR//存储的文件夹
                );

                fileR.setRequest(result);
            }
        }catch (Exception oe){
                LOGGER.error(oe.getMessage());
            }
        return fileR;
    }



    private static PutObjectResult uploadFileOSS(InputStream inputStream, String fileName) {
        PutObjectResult result = null;
        try{
            //.如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentLength(inputStream.available());
            objectMetadata.setCacheControl("no-cache");
            objectMetadata.setHeader("Pragma","no-cache");
            objectMetadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS,StorageClass.Standard.toString());
            objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
            objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
            objectMetadata.setContentDisposition("inline;filename=" + fileName.substring(fileName.lastIndexOf("/") + 1));
            //上传文件
            //设置权限这里是公开读
            //ossClient. setBucketAcl(bucketNane, CannedAccessControllist ,PublicRead);
            PutObjectRequest putobjectRequest=new PutObjectRequest(BUCKET_NAME,fileName, inputStream, objectMetadata);
            //上传文件。
            result = ossClient.putObject(putobjectRequest);
        }catch (Exception e){
            LOGGER.error(e.getMessage());
        }
        return result;
    }

    private static PutObjectResult uploadFileOSS(File file, String fileName) {
        PutObjectResult result = null;
        try{
        //.如果需要上传时设置存储类型与访问权限，请参考以下示例代码。
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.length());
        objectMetadata.setCacheControl("no-cache");
        objectMetadata.setHeader("Pragma","no-cache");
        objectMetadata.setHeader(OSSHeaders.OSS_STORAGE_CLASS,StorageClass.Standard.toString());
        objectMetadata.setObjectAcl(CannedAccessControlList.PublicRead);
        objectMetadata.setContentType(getcontentType(fileName.substring(fileName.lastIndexOf("."))));
        objectMetadata.setContentDisposition("inline;filename=" + fileName.substring(fileName.lastIndexOf("/") + 1));
        //上传文件
        //设置权限这里是公开读
        //ossClient. setBucketAcl(bucketNane, CannedAccessControllist ,PublicRead);
        PutObjectRequest putobjectRequest=new PutObjectRequest(BUCKET_NAME,fileName, file, objectMetadata);
        //上传文件。
        result = ossClient.putObject(putobjectRequest);
    }catch (Exception e){
        LOGGER.error(e. getMessage());
        }
        return result;
    }
}


