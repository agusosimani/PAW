package ar.edu.itba.paw.model.Enum;

public enum Warnings {

    CouldNotAddComment("Could not add the comment"),
    NoRecipesInCookList("Could not find recipes in this CookList"),
    NoCookLists("Could not find CookLists"),
    Success("Success"),
    AuthorizationDenied("User does not have the permission for action"),
    CantCookRecipe("User does not have the ingredients to cook the recipe"),
    CouldNotFindIngredient("Could not find the ingredient"),
    UserAlreadyExists("This user already exists"),
    NoSuchUser("This user does not exist"),
    AddRecipeValuesWrong("The values put cannot be null"),
    NoSuchRecipeUser("The recipe with this name was not found under this user's belongings");

    private String warning;

    Warnings(String warning) {
        this.warning = warning;
    }

    public String getWarning() {
        return warning;
    }
}
