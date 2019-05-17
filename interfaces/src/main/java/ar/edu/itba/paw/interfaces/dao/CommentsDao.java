package ar.edu.itba.paw.interfaces.dao;

import ar.edu.itba.paw.model.Comment;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface CommentsDao {


    Comment addNewComment(Comment comment);

    List<Comment> getAllRecipeComments(int recipeId);

    Optional<Comment> getSpecificComment(int id);

    void update(int commentId, Map<String, Object> changes);
}
