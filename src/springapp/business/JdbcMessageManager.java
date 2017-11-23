package springapp.business;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

@Service
public class JdbcMessageManager implements IMessageManager {

    private JdbcTemplate jdbcTemplate;

    protected final Log logger = LogFactory.getLog(getClass());

    @PostConstruct
    public void init() {
        logger.info("Create Table");
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS WA_MESSAGE(" + //
                "number INT AUTO_INCREMENT PRIMARY KEY," + //
                "text   VARCHAR(500)" + //
                ")");
    }

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    static private Message messageMapper(ResultSet rs, int rank) throws SQLException {
        Message m = new Message();
        m.setNumber(rs.getInt("number"));
        m.setText(rs.getString("text"));
        return m;
    }

    @Override
    public Collection<Message> findAll() {
        return this.jdbcTemplate.query("SELECT * FROM MESSAGE",
            JdbcMessageManager::messageMapper);
    }

    @Override
    public void add(String data) {
        this.jdbcTemplate.update("INSERT INTO WA_MESSAGE (text) VALUES (?)", data);
    }

    @Override
    public int removeAll() {
        return this.jdbcTemplate.update("DELETE FROM WA_MESSAGE");
    }

}