package edu.bjut.search.dao;

import edu.bjut.search.entity.DocAttribute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Map;

@Repository
public class DocDAO {
    private static Logger logger = LoggerFactory.getLogger(DocDAO.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入一个doc
     * @param docAttribute
     * @return 返回docId
     */
    public Integer insertDoc(DocAttribute docAttribute) {
        String sql = "insert into doc(link,title,doc_type) values(?,?,?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = jdbcTemplate.getDataSource().getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, docAttribute.getLink());
                ps.setString(2, docAttribute.getTitle());
                ps.setString(3, docAttribute.getDocType());
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    public DocAttribute getById(Integer docId) {
        try {
            Map<String, Object> map = jdbcTemplate.queryForMap("select * from doc where doc_id=?", new Object[]{docId});
            if (map == null || map.size() <= 0)
                return null;
            DocAttribute docAttribute = new DocAttribute();
            docAttribute.setDocId(docId);
            docAttribute.setTitle((String) map.get("title"));
            docAttribute.setLink((String) map.get("link"));
            docAttribute.setDocType((String) map.get("docType"));
            return docAttribute;
        } catch (Exception e) {
            logger.warn(e.getMessage());
            return null;
        }
    }


}
