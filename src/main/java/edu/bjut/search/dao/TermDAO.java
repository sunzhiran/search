package edu.bjut.search.dao;

import edu.bjut.search.entity.TermAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;

@Repository
public class TermDAO {

    private static Logger logger = LoggerFactory.getLogger(TermDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Integer insertTerm(TermAttribute termAttribute) {
        String sql = "insert into index_term(term,doc_list,frequency,offset) values(?,?,?,?)";
        return jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql);
                ps.setString(1, termAttribute.getTerm());
                ps.setString(2, termAttribute.getDocList());
                ps.setString(3, termAttribute.getFrequency());
                ps.setString(4, termAttribute.getOffset());
                return ps;
            }
        });
    }

    public TermAttribute queryByTerm(String term) {
        try {
            Map<String, Object> map = jdbcTemplate.queryForMap("select * from index_term where term=?", new Object[]{term});
            if (map == null || map.size() <= 0)
                return null;
            TermAttribute termAttribute = new TermAttribute();
            termAttribute.setTerm(term);
            termAttribute.setDocList((String) map.get("doc_list"));
            termAttribute.setFrequency((String) map.get("frequency"));
            termAttribute.setOffset((String) map.get("offset"));
            return termAttribute;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }

    public Integer updateByTerm(TermAttribute termAttribute) {
        return jdbcTemplate.update("update index_term set doc_list=?,frequency=?,offset=? where term=?", new Object[]{termAttribute.getDocList(),
                termAttribute.getFrequency(), termAttribute.getOffset(), termAttribute.getTerm()});
    }


}
