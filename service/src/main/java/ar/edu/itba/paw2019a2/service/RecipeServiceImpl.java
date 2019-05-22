package ar.edu.itba.paw2019a2.service;

import ar.edu.itba.paw2019a2.interfaces.dao.*;
import ar.edu.itba.paw2019a2.interfaces.service.IngredientService;
import ar.edu.itba.paw2019a2.interfaces.service.RecipeService;
import ar.edu.itba.paw2019a2.model.*;
import ar.edu.itba.paw2019a2.model.Enum.Order;
import ar.edu.itba.paw2019a2.model.Enum.Status;
import ar.edu.itba.paw2019a2.model.Enum.Tag;
import ar.edu.itba.paw2019a2.model.Enum.Warnings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class RecipeServiceImpl implements RecipeService {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    UserDao userDao;

    @Autowired
    IngredientsDao ingredientsDao;

    @Autowired
    RatingsDao ratingsDao;

    @Autowired
    CommentsDao commentsDao;

    @Autowired
    IngredientService ingredientService;

    @Override
    public Optional<Recipe> getById(int id) {

        Optional<Recipe> maybe = recipeDao.getById(id);
        if (maybe.isPresent()) {
            Recipe recipe = maybe.get();
            List<RecipeTag> tags = recipeDao.getAllRecipeTags(recipe);
            List<String> tagsString = new ArrayList<>();

            for (RecipeTag tag : tags) {
                tagsString.add(tag.getTag());
            }
            recipe.setTags(tagsString);

            List<RecipeIngredient> ingredientsList = ingredientsDao.getByRecipeId(recipe.getId());
            recipe.setIngredients(ingredientsList);

            recipe.setComments(this.getRecipeComments(recipe.getId()));

            return Optional.of(recipe);
        }
        return maybe;
    }

    @Override
    public Optional<Recipe> getByIdWithIngredients(int id) {
        Optional<Recipe> maybeRecipe = recipeDao.getById(id);

        if (maybeRecipe.isPresent()) {
            Recipe recipe = maybeRecipe.get();

            List<RecipeIngredient> ingredientsList = ingredientsDao.getByRecipeId(recipe.getId());
            recipe.setIngredients(ingredientsList);
            return Optional.of(recipe);
        }
        return maybeRecipe;
    }

    @Override
    public List<Recipe> findByUser(int userId) {
        return recipeDao.getByUserId(userId);
    }

    @Override
    public int userRecipesNumber(int userId) {
        return findByUser(userId).size();
    }

    @Override
    public Either<Recipe, Warnings> findUserRecipeByName(int userId, String name) {
        List<Recipe> list = this.findByUser(userId);
        for (Recipe r : list) {
            if (r.getName().equals(name)) {
                return Either.value(r);
            }
        }
        return Either.alternative(Warnings.valueOf("NoSuchRecipeUser"));
    }

    @Transactional
    @Override
    public Either<Recipe, Warnings> addNewRecipe(Recipe recipe) {

        if (recipe.getName().isEmpty() || recipe.getInstructions().isEmpty()) {
            return Either.alternative(Warnings.valueOf("AddRecipeValuesWrong"));
        }

        Recipe rec = recipeDao.addNewRecipe(recipe);

        for (String rt : rec.getTags()) {
            RecipeTag recipeTag = new RecipeTag(rt, recipe.getId());
            recipeDao.addNewRecipeTag(recipeTag);
        }

        for (RecipeIngredient rt : rec.getIngredients()) {
            if (rt.getObservation() == null)
                rt.setObservation("");
            ingredientsDao.addNewRecipeIngredient(rec.getId(), rt);
        }

        for (Comment comment : recipe.getComments()) {
            commentsDao.addNewComment(comment);
        }
        return Either.value(rec);
    }

    @Override
    @Transactional
    public void update(Recipe recipe) {
        Optional<Recipe> maybeOldRecipe = this.getById(recipe.getId());
        if (maybeOldRecipe.isPresent()) {
            Recipe oldRecipe = maybeOldRecipe.get();
            Map<String, Object> map = new HashMap<>();

            if (!oldRecipe.getDescription().equals(recipe.getDescription())) {
                map.put("description", recipe.getDescription());
            }
            if (!oldRecipe.getName().equals(recipe.getName())) {
                map.put("recipe_name", recipe.getName());
            }
            if (!oldRecipe.getInstructions().equals(recipe.getInstructions())) {
                map.put("instructions", recipe.getInstructions());
            }
            if (oldRecipe.getImage() != null && recipe.getImage() != null && Arrays.equals(recipe.getImage(), oldRecipe.getImage()))
                map.put("image", recipe.getImage());


            recipeDao.update(recipe.getId(), map);

            List<RecipeIngredient> oldIngList = oldRecipe.getIngredients();

            int deleteIngredients = oldIngList.size();



            for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {
                boolean newIng = true;


                for (RecipeIngredient oldIng : oldIngList) {

                    if (oldIng.getIngredient().getId() == recipeIngredient.getIngredient().getId()) {
                        Map<String, Object> UPmap = new HashMap<>();
                        UPmap.put("serving_amount", recipeIngredient.getAmount());
                        ingredientsDao.updateRecipeIngredient(oldIng.getIngredient().getId(), UPmap, recipe.getId());
                        newIng = false;
                    }
                }

                if (newIng) {
                    if (ingredientsDao.isRecipeIngredientDeleted(recipeIngredient.getIngredient().getId())) {
                        Map<String, Object> RIDmap = new HashMap<>();
                        RIDmap.put("ri_status", Status.REGULAR.toString());
                        RIDmap.put("serving_amount", recipeIngredient.getAmount());
                        ingredientsDao.updateRecipeIngredient(recipeIngredient.getIngredient().getId(), RIDmap, recipe.getId());
                    } else {
                        ingredientsDao.addNewRecipeIngredient(recipe.getId(), recipeIngredient);
                    }
                } else {
                    deleteIngredients--;
                }
            }

            while (deleteIngredients > 0) {
                for (RecipeIngredient oldIng : oldIngList) {
                    boolean oldIngDelete = true;
                    for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {
                        if (oldIng.getIngredient().getId() == recipeIngredient.getIngredient().getId())
                            oldIngDelete = false;
                    }
                    if(oldIngDelete) {
                        Map<String, Object> RDmap = new HashMap<>();
                        RDmap.put("ri_status", Status.DELETED.toString());
                        ingredientsDao.updateRecipeIngredient(oldIng.getIngredient().getId(), RDmap, oldRecipe.getId());
                        deleteIngredients--;
                    }

                }
            }


            List<RecipeTag> listTagsOld = recipeDao.getAllRecipeTags(oldRecipe);

            int deleteTags = listTagsOld.size();


            for (String tag : recipe.getTags()) {

                boolean newTag = true;

                for (RecipeTag oldTag : listTagsOld) {
                    if (oldTag.getTag().equals(tag)) {
                        newTag = false;
                    }
                }

                if (newTag) {
                    if (recipeDao.isTagDeleted(tag, recipe.getId())) {
                        recipeDao.changeTagStatus(tag, recipe.getId(), Status.REGULAR);

                    } else {
                        recipeDao.addNewRecipeTag(new RecipeTag(tag, recipe.getId()));
                    }
                } else {
                    deleteTags--;
                }
            }
            while (deleteTags > 0) {

                for (RecipeTag oldTag : listTagsOld) {
                    boolean oldTagDelete = true;
                    for (String tag : recipe.getTags()) {
                        if (oldTag.getTag().equals(tag)) {
                            oldTagDelete = false;
                        }
                    }
                    if (oldTagDelete) {
                        recipeDao.changeTagStatus(oldTag.getTag(), oldTag.getRecipeId(), Status.DELETED);
                        deleteTags--;
                    }
                }
            }


        }

    }

    @Transactional
    @Override
    public void deleteRecipe(int recipe) {
        Map<String, Object> map = new HashMap<>();
        map.put("recipe_status", Status.DELETED.toString());
        recipeDao.update(recipe, map);
    }

    @Override
    @Transactional
    public void addNewRating(int user, int recipe, float rating) {
        Optional<Rating> maybeRating = ratingsDao.getSpecificRating(user, recipe);
        if (maybeRating.isPresent()) {
            ratingsDao.update(user, recipe, "rating", rating);
            ratingsDao.update(user, recipe, "status", Status.REGULAR.toString());
            updateRatingRecipe(recipe, rating);

        } else {
            ratingsDao.addNewRating(user, recipe, rating);
            updateRatingRecipe(recipe, rating);

        }
    }

    private void updateRatingRecipe(int recipe, float rating) {
        Optional<Float> maybeTotalRating = ratingsDao.getRecipeRating(recipe);
        Map<String, Object> map = new HashMap<>();
        if (maybeTotalRating.isPresent()) {
            float totalRating = maybeTotalRating.get();


            map.put("rating", totalRating);
            recipeDao.update(recipe, map);
        } else {
            map.put("rating", rating);
            recipeDao.update(recipe, map);
        }
    }

    @Override
    public List<Recipe> getAllRecipesByUserId(int userId) {
        return recipeDao.getAllRecipesByUserId(userId);
    }

    @Override
    public List<Recipe> getFavouriteRecipes(int userId) {
        List<Rating> ratingsList = ratingsDao.getRatingsBiggerThanFour(userId);

        List<Recipe> retList = new ArrayList<>();

        for (Rating rating : ratingsList) {
            Optional<Recipe> maybeRec = recipeDao.getById(rating.getRecipeId());
            maybeRec.ifPresent(retList::add);
        }
        return retList;
    }

    @Override
    public Optional<Float> getUserRating(int userId, int recipeId) {
        Optional<Rating> maybeRating = ratingsDao.getSpecificRating(userId, recipeId);
        return maybeRating.map(Rating::getRating);
    }

    @Transactional
    @Override
    public Either<Comment, Warnings> addComment(Comment comment) {
        if (comment == null)
            return Either.alternative(Warnings.valueOf("CouldNotAddComment"));
        if (comment.getMessage().isEmpty() || comment.getMessage().equals(""))
            return Either.alternative(Warnings.valueOf("CouldNotAddComment"));
        return Either.value(commentsDao.addNewComment(comment));
    }


    @Override
    public List<Comment> getRecipeComments(int recipeId) {

        List<Comment> commentsList = commentsDao.getAllRecipeComments(recipeId);
        for (Comment comment : commentsList) {
            Optional<User> maybeUser = userDao.getById(comment.getUserId());
            maybeUser.ifPresent(user -> comment.setUsername(user.getUsername()));
        }
        return commentsList;
    }

    @Transactional
    @Override
    public RecipeList addNewCookListWithoutIngredients(int userId, String name) {
        return recipeDao.addNewUserList(name, userId);

    }


    @Transactional
    @Override
    public void addRecipeToCookList(int listId, int recipeId) {//TODO cambiar bien el add
        recipeDao.addRecipeToUserList(listId, recipeId);
    }

    @Override
    public List<RecipeList> getUserCookLists(int userId) {
        List<RecipeList> list = recipeDao.getUserCookLists(userId);
        for (RecipeList recipeList : list) {
            recipeList.setList(recipeDao.getRecipesfromCookList(recipeList.getId()));
        }
        return list;
    }

    @Override
    public Either<RecipeList, Warnings> getCookList(int cookListId) {

        Optional<RecipeList> maybeRecipeList = recipeDao.getCookList(cookListId);
        if (!maybeRecipeList.isPresent())
            return Either.alternative(Warnings.valueOf("NoCookLists"));

        RecipeList recipeList = maybeRecipeList.get();
        recipeList.setList(recipeDao.getRecipesfromCookList(cookListId));

        return Either.value(recipeList);
    }

    @Override
    @Transactional
    public Warnings deleteRecipeFromCookList(int listId, int recipeId, int userId) {
        if (recipeDao.checkCookListUser(listId, userId)) {
            recipeDao.updateRList(listId, recipeId, Status.DELETED.toString());
            return Warnings.valueOf("Success");
        }
        return Warnings.valueOf("AuthorizationDenied");
    }

    @Override
    @Transactional
    public Warnings deleteCookList(int listId, int userId) {//TODO: chequear si soy el dueno del cookList
        if (recipeDao.checkCookListUser(listId, userId)) {
            Map<String, Object> map = new HashMap<>();
            map.put("ur_status", Status.DELETED.toString());
            recipeDao.updateURList(listId, map);
            return Warnings.valueOf("Success");
        }
        return Warnings.valueOf("AuthorizationDenied");

    }

    @Override
    public Set<Recipe> getRecipesBasedOnOrderTagsCookable(List<String> tags, Order order, int userId, String search, int limit) {
        if (tags == null)
            tags = new ArrayList<>();
        List<Recipe> recipeList = recipeDao.getRecipesWithTagAndOrder(order, tags, search, limit);

        List<Recipe> returnList = new ArrayList<>();

        List<RecipeIngredient> userIngredients = ingredientsDao.getByUserId(userId);

        for (Recipe recipe : recipeList) {

            boolean flag = true;


            recipe.setIngredients(ingredientsDao.getByRecipeId(recipe.getId()));


            for (RecipeIngredient recipeIngredient : recipe.getIngredients()) {

                boolean canUseIng = false;

                for (RecipeIngredient userIngredient : userIngredients) {
                    if (recipeIngredient.getIngredient().equals(userIngredient.getIngredient())) {
                        if (recipeIngredient.getAmount() <= userIngredient.getAmount()) {
                            canUseIng = true;
                        }
                    }

                }
                if (!canUseIng) {
                    flag = false;
                }
            }

            if (flag) {

                recipe.setComments(this.getRecipeComments(recipe.getId()));

                List<RecipeTag> recipeTags = recipeDao.getAllRecipeTags(recipe);
                List<String> tagsString = new ArrayList<>();

                for (RecipeTag tag : recipeTags) {
                    tagsString.add(tag.getTag());
                }
                recipe.setTags(tagsString);

                returnList.add(recipe);
            }
        }

        return new LinkedHashSet<>(returnList);
    }

    @Override
    public Set<Recipe> getRecipesBasedOnOrderTags(List<String> tags, Order order, String search, int limit) {
        if (tags == null)
            tags = new ArrayList<>();


        List<Recipe> list = recipeDao.getRecipesWithTagAndOrder(order, tags, search, limit);


        for (Recipe recipe : list) {
            List<String> tagsString = new ArrayList<>();
            List<RecipeTag> recipeTags = recipeDao.getAllRecipeTags(recipe);
            for (RecipeTag rt : recipeTags) {
                tagsString.add(rt.getTag());
            }
            recipe.setTags(tagsString);


            recipe.setComments(this.getRecipeComments(recipe.getId()));
        }


        return new LinkedHashSet<>(list);
    }

    @Override
    public List<RecipeIngredient> getIngredientsCookedRangeTime(int userId, Date from, Date to) {
        List<RecipeIngredient> retList = new ArrayList<>();

        List<Recipe> recipeList = recipeDao.getRecipesCookedInBetweenDates(userId, from, to);

        for (Recipe rec : recipeList) {
            retList.addAll(ingredientsDao.getByRecipeId(rec.getId()));
        }


        return retList;
    }


    @Override
    public Set<Recipe> getRecipesOrderCooked(int userId) {
        List<Recipe> list = recipeDao.getRecipesInCookOrder(userId);
        putIngredientsAndTagsToRecipe(list);

        return new LinkedHashSet<>(list);
    }

    private void putIngredientsAndTagsToRecipe(List<Recipe> list) {
        for (Recipe rec : list) {
            rec.setIngredients(ingredientsDao.getByRecipeId(rec.getId()));
            List<RecipeTag> tags = recipeDao.getAllRecipeTags(rec);
            List<String> tagString = new ArrayList<>();

            for (RecipeTag rt : tags) {
                tagString.add(rt.getTag());
            }

            rec.setTags(tagString);
        }
    }

    private List<Recipe> getRecipesCookedRangeTime(int userId, Date from, Date to) {

        List<Recipe> recipeList = recipeDao.getRecipesCookedInBetweenDates(userId, from, to);

        for (Recipe rec : recipeList) {

            rec.setComments(this.getRecipeComments(rec.getId()));

            rec.setIngredients(ingredientsDao.getByRecipeId(rec.getId()));

            List<String> tagsString = new ArrayList<>();
            List<RecipeTag> recipeTags = recipeDao.getAllRecipeTags(rec);
            for (RecipeTag rt : recipeTags) {
                tagsString.add(rt.getTag());
            }
            rec.setTags(tagsString);
        }

        return recipeList;
    }

    @Override
    public Map<Tag,Integer> tagStatistics(int userId, Date from, Date to) {

        List<Recipe> recipeList = getRecipesCookedRangeTime(userId,from,to);

        if(recipeList.isEmpty()) {
            return new HashMap<>();
        }

        Map<Tag,Integer> retMap = new HashMap<>();

        for(Tag tag:Tag.values()) {
            retMap.put(tag,0);
        }

        for (Recipe recipe: recipeList) {
            for (String tag:recipe.getTags()) {
                int aux = retMap.get(Tag.valueOf("tag"));
                retMap.put(Tag.valueOf("tag"),aux + 1);
            }
        }
        return retMap;
    }

}
