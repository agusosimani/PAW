package ar.edu.itba.paw.persistence;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.function.Supplier;

class Transactions {

    public Transactions(){
        throw new AssertionError("Can't instantiate a utility class");
    }

    public static <T> T executeInTransaction(JdbcTemplate jdbcTemplate, Supplier<T> sup) {
        try {
            jdbcTemplate.execute("BEGIN TRANSACTION ");
            final T ret = sup.get();

            jdbcTemplate.execute("COMMIT");
            return ret;
        }catch(RuntimeException e){
            jdbcTemplate.execute("ROLLBACK");
            throw e;
        }
    }

}
