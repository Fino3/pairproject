package com.example.back.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.back.common.Result;
import com.example.back.common.ToJson;
import com.example.back.common.Total;
import com.example.back.common.TotalExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController

public class MsgController {

    @GetMapping("total")
    public String totalJson() throws IOException {
        String json=buildJson(1);
        return json;
    }

    @GetMapping("schedule")
    public String scheduleJson() {
        return null;
    }

    public String buildJson(int i) throws IOException {
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-generator/mybatis-config.xml");
        //初始化mybatis,创建SqlSessionFactory类的实例
        SqlSessionFactory sqlSessionFactory =  new SqlSessionFactoryBuilder().build(resourceAsStream);
        //创建session实例
        SqlSession session = sqlSessionFactory.openSession();
        if (i==1) {
            List<Total> list = session.selectList("com.example.back.dao.TotalMapper.selectAll");
            Object o=JSONObject.toJSON(list);
            ToJson toJson = new ToJson(list.size(), o);
            JSONObject json1= (JSONObject) JSON.toJSON(toJson);
            Result result = new Result(200,json1,"成功");
            String json2 = JSONObject.toJSONString(result);
            session.close();
            return json2;
        } else if (i==2) {
            return null;
        }
        return null;
    }

}
