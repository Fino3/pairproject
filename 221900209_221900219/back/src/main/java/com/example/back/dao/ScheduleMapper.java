package com.example.back.dao;

import com.example.back.common.Schedule;
import com.example.back.common.ScheduleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScheduleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table schedule
     *
     * @mbg.generated Thu Mar 24 10:58:16 CST 2022
     */
    long countByExample(ScheduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table schedule
     *
     * @mbg.generated Thu Mar 24 10:58:16 CST 2022
     */
    int deleteByExample(ScheduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table schedule
     *
     * @mbg.generated Thu Mar 24 10:58:16 CST 2022
     */
    int insert(Schedule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table schedule
     *
     * @mbg.generated Thu Mar 24 10:58:16 CST 2022
     */
    int insertSelective(Schedule record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table schedule
     *
     * @mbg.generated Thu Mar 24 10:58:16 CST 2022
     */
    List<Schedule> selectByExample(ScheduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table schedule
     *
     * @mbg.generated Thu Mar 24 10:58:16 CST 2022
     */
    int updateByExampleSelective(@Param("record") Schedule record, @Param("example") ScheduleExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table schedule
     *
     * @mbg.generated Thu Mar 24 10:58:16 CST 2022
     */
    int updateByExample(@Param("record") Schedule record, @Param("example") ScheduleExample example);
}