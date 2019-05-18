package ar.edu.itba.paw.persistence;


import ar.edu.itba.paw.interfaces.dao.CommentsDao;
import ar.edu.itba.paw.model.Comment;
import ar.edu.itba.paw.model.Enum.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Timestamp;
import java.util.*;

@Repository
public class CommentsDaoImpl implements CommentsDao {

    JdbcTemplate jdbcTemplate;
    SimpleJdbcInsert jdbcInsertComment;

    private final static RowMapper<Comment> COMMENT_ROW_MAPPER = (rs, rowNum) ->
            new Comment(
                    rs.getInt("comment_id"),
                    rs.getInt("user_id"),
                    rs.getInt("recipe_id"),
                    rs.getString("message"),
                    rs.getDate("comment_date"));

    @Autowired
    public CommentsDaoImpl(DataSource ds) {
        jdbcTemplate = new JdbcTemplate(ds);
        jdbcInsertComment = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("recipes_comments")
                .usingGeneratedKeyColumns("comment_id");
    }


    @Override
    public Comment addNewComment(Comment comment) {
        Map<String, Object> map = new HashMap<>();

        map.put("message", comment.getMessage());
        map.put("user_id", comment.getUserId());
        map.put("recipe_id",comment.getRecipeId());

        map.put("comment_status", Status.REGULAR.toString());

        Date date= new Date();
        long time = date. getTime();
        map.put("comment_date", new Timestamp(time));

        comment.setId(jdbcInsertComment.executeAndReturnKey(map).intValue());

        return comment;
    }

    @Override
    public List<Comment> getAllRecipeComments(int recipeId) {
        List<Comment> list = jdbcTemplate.query(
                "SELECT * FROM recipes_comments WHERE recipe_id = ? AND comment_status = 'REGULAR'"
                ,COMMENT_ROW_MAPPER,recipeId);
        if(list.isEmpty())
            return new ArrayList<>();

        return list;

    }

    public List<Comment> getUserCommentsFromRecipe(int recipeId, int userId) {
        List<Comment> list = jdbcTemplate.query(
                "SELECT * FROM recipes_comments WHERE recipe_id = ? AND user_id = ? AND comment_status = 'REGULAR'"
                ,COMMENT_ROW_MAPPER,recipeId,userId);
        if(list.isEmpty())
            return new ArrayList<>();
        return list;
    }


    @Override
    public Optional<Comment> getSpecificComment(int id) {
        List<Comment> list = jdbcTemplate.query(
                "SELECT * FROM recipes_comments WHERE comment_id = ? AND comment_status = 'REGULAR'"
                ,COMMENT_ROW_MAPPER,id);
        if(list.isEmpty())
            return Optional.empty();
        return Optional.of(list.get(0));
    }

    @Override
    public void update(int commentId, Map<String, Object> changes) {
        changes.forEach((k, v) -> update(commentId, k, v));
    }

    private void update(int commentId, String k, Object v) {
        if(k.equals("message")) {
            jdbcTemplate.update("UPDATE recipes_comments SET message = ? WHERE comment_id = ?",v,commentId);
        }
        if(k.equals("comment_status")) {
            jdbcTemplate.update("UPDATE recipes_comments SET comment_status = ? WHERE comment_id = ?",v,commentId);
        }
    }


}
