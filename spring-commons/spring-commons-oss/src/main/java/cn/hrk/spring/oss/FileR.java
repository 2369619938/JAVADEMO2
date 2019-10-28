package cn.hrk.spring.oss;

import com.aliyun.oss.model.PutObjectRequest;
import com.aliyun.oss.model.PutObjectResult;
import lombok.Data;

import java.io.Serializable;
@Data
public class FileR implements Serializable {
    /*
    * 文件大小
    * */
    private Long fileSize; //文件大小
    private String  fileAPUrl; //文件的绝对路径
    private String  webUrl; //文件的web访问地址
    private String  fileSuffix; //文件后缀名
    private String  fileBucket; //存储的bucket
    private String  oldFileName; //原文件名
    private String  folder; //存储的文件夹
    /*
    * 上传后oss的响应对象
    * */
    PutObjectResult request;

    public FileR(Long fileSize, String fileAPUrl, String webUrl, String fileSuffix, String fileBucket, String oldFileName, String folder) {
        this.fileSize = fileSize;
        this.fileAPUrl = fileAPUrl;
        this.webUrl = webUrl;
        this.fileSuffix = fileSuffix;
        this.fileBucket = fileBucket;
        this.oldFileName = oldFileName;
        this.folder = folder;
    }

    public FileR() {

    }
}
