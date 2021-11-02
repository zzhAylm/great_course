package com.graduate.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.graduate.oss.service.OssService;
import com.graduate.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/10/30 18:07
 */
@Service
public class OssServiceImpl implements OssService {
//    上传头像
    @Override
    public String uploadFileAvatar(MultipartFile file){
        // yourEndpoint填写Bucket所在地域对应的Endpoint。以华东1（杭州）为例，Endpoint填写为https://oss-cn-hangzhou.aliyuncs.com。
        String endpoint = ConstantPropertiesUtils.END_POINT;
        // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
        String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName=ConstantPropertiesUtils.BUCKET_NAME;
        String fileName=file.getOriginalFilename();


//        防止文件名相同造成文件被替换，我们为文件创建唯一值
        String uuid= UUID.randomUUID().toString().replaceAll("-", "");
        fileName=uuid+fileName;

//        按照日期对文件进行分类
        String datePath= new DateTime().toString("yyyy/MM/dd");
        fileName=datePath+"/"+fileName;


        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 填写本地文件的完整路径。如果未指定本地路径，则默认从示例程序所属项目对应本地路径中上传文件流。
            InputStream inputStream = file.getInputStream();

            // 依次填写Bucket名称（例如examplebucket）和Object完整路径（例如exampledir/exampleobject.txt）。Object完整路径中不能包含Bucket名称。
            ossClient.putObject(bucketName,fileName, inputStream);
            // 关闭OSSClient。
            ossClient.shutdown();
            return "https://"+bucketName+"."+endpoint+"/"+fileName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


    }
}
