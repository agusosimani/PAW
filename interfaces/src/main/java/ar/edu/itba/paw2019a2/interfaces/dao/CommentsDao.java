package ar.edu.itba.paw2019a2.interfaces.dao;

import ar.edu.itba.paw2019a2.model.Comment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommentsDao {


    Comment addNewComment(Comment comment);

    List<Comment> getAllRecipeComments(int recipeId);

    void update(int commentId, Map<String, Object> changes);
}
