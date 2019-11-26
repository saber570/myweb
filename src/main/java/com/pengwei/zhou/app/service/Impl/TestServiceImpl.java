package com.pengwei.zhou.app.service.Impl;

import com.google.common.collect.Maps;
import com.pengwei.zhou.app.service.TestService;
import com.pengwei.zhou.app.utils.FreemarkerHelper;
import java.time.LocalDate;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  @Override
  public void create(String name, Integer age) {
    jdbcTemplate.update("insert into USER_1(NAME, AGE) values(?, ?)", name, age);
  }
  @Override
  public void deleteByName(String name) {
    jdbcTemplate.update("delete from USER_1 where NAME = ?", name);
  }
  @Override
  public Integer getAllUsers() {
    return jdbcTemplate.queryForObject("select count(1) from USER_1", Integer.class);
  }
  @Override
  public void deleteAllUsers() {
    jdbcTemplate.update("delete from USER_1");
  }

  @Override
  public String handle(String name) {
      Map<String, Object> map = Maps.newHashMap();
      map.put("info", "bo");
      return FreemarkerHelper.process(name, map);
  }



}
