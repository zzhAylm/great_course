package com.graduate.vod.service.impl;

import com.aliyun.oss.ClientException;
import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.graduate.base.exception.MyException;
import com.graduate.vod.service.VodService;
import com.graduate.vod.utils.ConstantVodUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static com.graduate.vod.utils.InitVodClient.initVodClient;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/12 0:40
 */
@Service
public class VodServiceImpl implements VodService {
    @Override
    public String uploadVideo(MultipartFile file) {

        String fileName = file.getOriginalFilename();
        assert fileName != null;
        String title = fileName.substring(0, fileName.lastIndexOf("."));
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UploadStreamRequest request = new UploadStreamRequest(ConstantVodUtil.KEY_ID, ConstantVodUtil.KEY_SECRET, title, fileName, inputStream);

        UploadVideoImpl uploader = new UploadVideoImpl();
        UploadStreamResponse response = uploader.uploadStream(request);
        System.out.print("RequestId=" + response.getRequestId() + "\n");  //请求视频点播服务的请求ID
        if (response.isSuccess()) {
            System.out.print("VideoId=" + response.getVideoId() + "\n");
        } else { //如果设置回调URL无效，不影响视频上传，可以返回VideoId同时会返回错误码。其他情况上传失败时，VideoId为空，此时需要根据返回错误码分析具体错误原因
            System.out.print("VideoId=" + response.getVideoId() + "\n");
            System.out.print("ErrorCode=" + response.getCode() + "\n");
            System.out.print("ErrorMessage=" + response.getMessage() + "\n");
        }
        return response.getVideoId();
    }

    //    删除视频
    @Override
    public void removeVideo(String videoId) {
            String accessKeyId = ConstantVodUtil.KEY_ID;
            String accessKeySecret = ConstantVodUtil.KEY_SECRET;
            DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
            DeleteVideoResponse response = new DeleteVideoResponse();
            try {
                System.out.print("RequestId = " + response.getRequestId() + "\n");
                DeleteVideoRequest request = new DeleteVideoRequest();
                //支持传入多个视频ID，多个用逗号分隔
                request.setVideoIds(videoId);
                response = client.getAcsResponse(request);
                System.out.print("RequestId = " + response.getRequestId() + "\n");
            } catch (Exception e) {
                throw new MyException(500, "视频删除失败");
            }
    }

    @Override
    public void removeVideoList(List videoList) {
        String accessKeyId = ConstantVodUtil.KEY_ID;
        String accessKeySecret = ConstantVodUtil.KEY_SECRET;
        DefaultAcsClient client = initVodClient(accessKeyId, accessKeySecret);
        DeleteVideoResponse response = new DeleteVideoResponse();
        try {
            System.out.print("RequestId = " + response.getRequestId() + "\n");
            DeleteVideoRequest request = new DeleteVideoRequest();
            //支持传入多个视频ID，多个用逗号分隔

            if (videoList!=null&&!videoList.isEmpty()){
                String idArray = StringUtils.join(videoList.toArray(), ",");
                request.setVideoIds(idArray);
            }
            response = client.getAcsResponse(request);
            System.out.print("RequestId = " + response.getRequestId() + "\n");
        } catch (Exception e) {
            throw new MyException(500, "视频删除失败");
        }



    }
}
