package com.song.spark.web;

import com.song.spark.dao.CourseClickCountDAO;
import com.song.spark.domain.CourseClickCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * web层
 */
@RestController
public class CourseStatApp {

    private static Map<String, String> courses = new HashMap<>();

    static {
        courses.put("112", "Spark SQL慕课网日志分析");
        courses.put("128", "10小时入门大数据");
        courses.put("145", "深度学习之神经网络核心原理与算法");
        courses.put("146", "强大的Node.js在Web开发的应用");
        courses.put("131", "Vue+Django实战");
        courses.put("130", "Web前端性能优化");

    }

    @Autowired
    CourseClickCountDAO courseClickCountDAO;

//    @RequestMapping(value = "/course_clickcount_dynamic", method = RequestMethod.GET)
//    public ModelAndView courseClickCount() throws Exception {
//        ModelAndView modelAndView = new ModelAndView("index");
//
//        List<CourseClickCount> list = courseClickCountDAO.query("20180113");
//
//        for (CourseClickCount model : list) {
//            model.setName(courses.get(model.getName().substring(9)));
//
//        }
//        JSONArray json = JSONArray.fromObject(list);
//        modelAndView.addObject("data_json", json);
//
//
//        return modelAndView;
//
//    }


    @RequestMapping(value = "/course_clickcount_dynamic", method = RequestMethod.POST)
    @ResponseBody
    public List<CourseClickCount> courseClickCount() throws Exception {
        List<CourseClickCount> list = courseClickCountDAO.query("20180113");

        for (CourseClickCount model : list) {
            model.setName(courses.get(model.getName().substring(9)));
        }
        return list;

    }

    @RequestMapping(value = "/echarts", method = RequestMethod.GET)
    public ModelAndView echarts() {
        return new ModelAndView("echarts");

    }
}
