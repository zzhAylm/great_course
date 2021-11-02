package com.graduate.edu.easy_excel;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.alibaba.excel.event.SyncReadListener;

import java.util.Map;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/1 9:38
 */
//EasyExcel实现读操作
public class EasyExcelRead {

    public static void main(String[] args) {
        String fileName="D:\\qq\\IDEA\\IdeaProjects\\graduation_project\\great_course\\service\\service_edu\\src\\test\\java\\com\\graduate\\edu\\easy_excel\\student.xlsx";
        EasyExcel.read(fileName, ReadStudent.class, new ExcelListener()).sheet().doRead();

        System.out.println("---------------end------------");
    }



}
//创建一个监听器
class ExcelListener extends AnalysisEventListener<ReadStudent>{

//    一行一行的读取文件的内容
    @Override
    public void invoke(ReadStudent readStudent, AnalysisContext analysisContext) {

        System.out.println(readStudent.toString());
    }

//   读取表头的的内容
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        super.invokeHeadMap(headMap, context);
        System.out.println("表头的内容"+headMap);
    }

    //读取完成之后
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {}
}
