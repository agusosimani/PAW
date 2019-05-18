package ar.edu.itba.paw.model.Enum;

public enum Warnings {

    CouldNotAddComment("Could not add the comment"),
    NoRecipesInCookList("Could not find recipes in this CookList"),
    NoCookLists("Could not find CookLists"),
    Success("Success"),
    AuthorizationDenied("User does not have the permission for action");

    private String warning;

    Warnings(String warning) {
        this.warning = warning;
    }

    public String getWarning() {
        return warning;
    }
}
