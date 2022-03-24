package com.example.back.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.back.common.*;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

            /*scheduleExample scheduleExample = new scheduleExample();
            com.example.back.common.scheduleExample.Criteria criteria = scheduleExample.createCriteria();*/
            int check=0;
            if (!date.equals("*")) {
                /*criteria.andStartdatecnEqualTo(date);*/
                value+="where startdatecn between "+"'"+date+" 00:00:00' and "+"'"+date+" 23:59:59'";
                check=1;
            }
            if (!item.equals("*")) {
                /*criteria.andSubitemnameEqualTo(item);*/
                if (check==1) {
                    value += " and subitemname = " + "'" + item + "'";
                } else {
                    value+="where subitemname = "+"'"+item+"'";
                    check=1;
                }
            }
            if (!place.equals("*")) {
                /*criteria.andVenuenameEqualTo(place);*/
                if (check==1) {
                    value += " and venuename = " + "'" + place + "'";
                } else {
                    value += "where venuename = " + "'" + place + "'";
                }
            }
            value+=";";

            /*Map<String,Object> parameters=new java.util.HashMap<>();
            parameters.put("0",date);
            parameters.put("1",item);
            parameters.put("1",place);*/

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
