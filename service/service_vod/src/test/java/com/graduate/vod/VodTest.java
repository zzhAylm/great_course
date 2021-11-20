package com.graduate.vod;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoRequest;
import com.aliyuncs.vod.model.v20170321.GetPlayInfoResponse;

import java.util.List;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/11 1:39
 */
public class VodTest {
    /*获取播放地址函数*/
    public static GetPlayInfoResponse getPlayInfo(DefaultAcsClient client) throws Exception {
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("视频ID");
        return client.getAcsResponse(request);
    }

//   根据id获取到播放凭证


    /*根据id获取视频播放地址*/
    public static void main(String[] argv) {
        DefaultAcsClient client =InitObject.initVodClient("LTAI5t6vH691eRBEZj1girmU", "oLan1Uvx2VfUHADijkLqTMYPwRPAq6");

        GetPlayInfoResponse response = new GetPlayInfoResponse();
        GetPlayInfoRequest request = new GetPlayInfoRequest();
        request.setVideoId("f2255828b5dd40a2b752254a093f0e34");
//        request.setVideoId("0b2555775973433a94dd1966a44e06ea");

        try {
            response = client.getAcsResponse(request);
//            获取到视频播放凭证




            List<GetPlayInfoResponse.PlayInfo> playInfoList = response.getPlayInfoList();

            //播放地址
            for (GetPlayInfoResponse.PlayInfo playInfo : playInfoList) {
                System.out.print("PlayInfo.PlayURL = " + playInfo.getPlayURL() + "\n");
            }
            //Base信息
            System.out.print("VideoBase.Title = " + response.getVideoBase().getTitle() + "\n");

        } catch (Exception e) {
            System.out.print("ErrorMessage = " + e.getLocalizedMessage());
        }
        System.out.print("RequestId = " + response.getRequestId() + "\n");
    }
}
