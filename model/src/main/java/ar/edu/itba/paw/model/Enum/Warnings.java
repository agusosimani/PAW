package ar.edu.itba.paw.model.Enum;

public enum Warnings {

    CouldNotAddComment("Could not add the comment");

    private String warning;

    Warnings(String warning) {
        this.warning = warning;
    }

    public String getWarning() {
        return warning;
    }
}
