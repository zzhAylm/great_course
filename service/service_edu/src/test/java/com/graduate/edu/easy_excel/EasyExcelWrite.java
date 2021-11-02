package com.graduate.edu.easy_excel;

import com.alibaba.excel.EasyExcel;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:测试类
 * @Author: 张紫韩
 * @Crete 2021/11/1 9:23
 */
//EasyExcel实现写操作
public class EasyExcelWrite {

    public static void main(String[] args) {

        String fileName="D:\\qq\\IDEA\\IdeaProjects\\graduation_project\\great_course\\service\\service_edu\\src\\test\\java\\com\\graduate\\edu\\easy_excel\\student.xlsx";
        EasyExcel.write(fileName, WriteStudent.class).sheet("学生列表").doWrite(getList());


    }

    public static List<WriteStudent> getList(){
        ArrayList<WriteStudent> list = new ArrayList<>();

        for (int i=0;i<10;i++){
            WriteStudent writeStudent = new WriteStudent();
            writeStudent.setName("lucy"+i);
            writeStudent.setNum("00"+i);
            list.add(writeStudent);
        }
        return list;
    }
}
