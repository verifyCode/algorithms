package com.biz.mybatis;

import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author xjn
 * @since 2020-03-12
 */

public interface DebtMapper {

    @Select({"select * from t_dept "})
    List<Map> selectDebt();

    @Select({"select deptId from emp"})
    BigDecimal select();
}