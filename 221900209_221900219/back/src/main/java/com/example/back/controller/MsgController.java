package com.example.back.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.back.common.Result;
import com.example.back.common.ToJson;
import com.example.back.common.schedule;
import com.example.back.common.total;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

@RestController
@CrossOrigin
public class MsgController {

    @GetMapping("total")
    public String totalJson() throws IOException, ParseException {
        String json=buildJson(1,null,null,null);
        return json;
    }

    @GetMapping("schedule")
    public String scheduleJson(@RequestParam String date,String item,String place) throws IOException, ParseException {
        String json=buildJson(2,date,item,place);
        return json;
    }

    public String buildJson(int i,String date,String item,String place) throws IOException, ParseException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(resourceAsStream);
        //创建session实例
        SqlSession session = sqlSessionFactory.openSession();
        if (i==1) {
            List<total> list = session.selectList("com.example.back.dao.totalMapper.selectAll");
            Object o=JSONObject.toJSON(list);
            ToJson toJson = new ToJson(list.size(), o);
            JSONObject json1= (JSONObject) JSON.toJSON(toJson);
            Result result = new Result(200,json1,"成功");
            String json2 = JSONObject.toJSONString(result);
            session.close();
            return json2;
        } else if (i==2) {
            if (date.length()==9) {
                StringBuffer stringBuilder=new StringBuffer(date);
                stringBuilder.insert(8,'0');
                date=stringBuilder.toString();
            }

            String value="select * from schedule ";
            int check=0;
            if (!date.equals("*")) {
                value+="where startdatecn between "+"'"+date+" 00:00:00' and "+"'"+date+" 23:59:59'";
                check=1;
            }
            if (!item.equals("*")) {
                if (check==1) {
                    value += " and subitemname = " + "'" + item + "'";
                } else {
                    value+="where subitemname = "+"'"+item+"'";
                    check=1;
                }
            }
            if (!place.equals("*")) {
                if (check==1) {
                    value += " and venuename = " + "'" + place + "'";
                } else {
                    value += "where venuename = " + "'" + place + "'";
                }
            }
            value+=";";

            List<schedule> list = session.selectList("com.example.back.dao.scheduleMapper.findFieldByTableName",value);
            Object o=JSONObject.toJSON(list);
            ToJson toJson = new ToJson(list.size(), o);
            JSONObject json1= (JSONObject) JSON.toJSON(toJson);
            Result result = new Result(200,json1,"成功");
            String json2 = JSONObject.toJSONString(result);
            session.close();
            return json2;
        }
        return null;
    }

}
