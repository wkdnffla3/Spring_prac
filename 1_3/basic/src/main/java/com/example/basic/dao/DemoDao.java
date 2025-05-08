package com.example.basic.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.example.basic.model.Demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
public class DemoDao {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> select1() {
        String sql = "select seq, user from demo";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;

    }

    public List<Demo> select2() {
        String sql = "select seq, user from demo";
        List<Demo> list = jdbcTemplate.query(sql, new RowMapper<Demo>() {
            @Override
            public Demo mapRow(ResultSet rs, int index) throws SQLException {
                Long seq = rs.getLong("seq");
                String user = rs.getString("user");
                Demo demo = new Demo();
                demo.setSeq(seq);
                demo.setUser(user);
                return demo;
            }

        });
        return list;
    }

    public int insert(Demo demo) {
        String sql = "insert into demo (seq, user) values (?, ?)";
        int result = jdbcTemplate.update(sql, demo.getSeq(), demo.getUser());
        return result;
    }

    public int delete(int seq) {
        String sql = "delete from demo where seq = ?";
        int result = jdbcTemplate.update(sql, seq);
        return result;
    }

}
