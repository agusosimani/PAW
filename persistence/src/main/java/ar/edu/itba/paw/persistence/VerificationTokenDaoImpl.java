package ar.edu.itba.paw.persistence;

import ar.edu.itba.paw.interfaces.dao.VerificationTokenDao;
import ar.edu.itba.paw.model.VerificationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class VerificationTokenDaoImpl implements VerificationTokenDao {

    private final static RowMapper<VerificationToken> ROW_MAPPER = (rs, rowNum) ->
            new VerificationToken(rs.getString("token"), rs.getInt("user_id"), rs.getDate("expiry_date"));

    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcInsert jdbcInsert;

    @Autowired
    public VerificationTokenDaoImpl(final DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsert = new SimpleJdbcInsert(ds)
                .withTableName("verification_tokens")
                .usingGeneratedKeyColumns("token_id");
    }

    @Override
    public Optional<VerificationToken> findByToken(String token) {
        return jdbcTemplate.query("SELECT * FROM verification_tokens WHERE token = ? ", ROW_MAPPER, token).stream().findAny();
    }

    @Override
    public Optional<VerificationToken> save(VerificationToken.Builder tokenBuilder) {
        Map<String, Object> tokenRow =new HashMap<>();

        tokenRow.put("token", tokenBuilder.getToken());
        tokenRow.put("user_id", tokenBuilder.getUserId());
        tokenRow.put("expiry_date", tokenBuilder.getExpiryDate());
        tokenRow.put("token_status","REGULAR");

        try {
            jdbcInsert.execute(tokenRow);
        } catch (Exception ex) {
            return Optional.empty();
        }
        return Optional.of(new VerificationToken(tokenBuilder));
    }
}

