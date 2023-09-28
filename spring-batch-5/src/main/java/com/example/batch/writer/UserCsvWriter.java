package com.example.batch.writer;

import com.example.batch.request.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
@Service
@RequiredArgsConstructor
public class UserCsvWriter implements ItemWriter<Person> {
    private final JdbcTemplate template;
    private String query = "insert into users (id, name,email,dob,phone,address) values (?, ?,?,?,?,?)";
    @Override
    public void write(List<? extends Person> list) throws Exception {

        template.batchUpdate(query, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Person user = list.get(i);
                ps.setLong(1, user.getId());
                ps.setString(2, user.getName());
                ps.setString(3, user.getEmail());
                ps.setDate(4, Date.valueOf( DateTimeFormatter.ofPattern("dd-MMM-yy").parse(user.getDob(), LocalDate::from)));
                ps.setString(5, user.getPhone());
                ps.setString(6, user.getAddress());
            }

            @Override
            public int getBatchSize() {
                return list.size();
            }
        });
    }
}
