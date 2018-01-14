package com.song.spark.dao;

import com.song.spark.domain.CourseClickCount;
import com.song.spark.util.HBaseUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 实战课程访问数量 数据访问层
 */
@Component
public class CourseClickCountDAO {

    public List<CourseClickCount> query(String day) throws Exception {
        List<CourseClickCount> list = new ArrayList<>();
        //去Hbase表中根据day获取实战课程点击数量
        Map<String, Long> map = HBaseUtils.getInstance().query("course_clickcount", day);
        for (Map.Entry entry : map.entrySet()) {
            CourseClickCount model = new CourseClickCount();
            model.setName((String) entry.getKey());
            model.setValue((Long) entry.getValue());
            list.add(model);
        }
        return list;

    }

    public static void main(String[] args) throws Exception {
        CourseClickCountDAO courseClickCountDAO = new CourseClickCountDAO();

        List<CourseClickCount> list = courseClickCountDAO.query("20180113");

        for (CourseClickCount courseClickCount : list) {
            System.out.println(courseClickCount.getName() + ":" + courseClickCount.getValue());
        }
    }
}
