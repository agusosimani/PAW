package ar.edu.itba.paw2019a2.webapp.form;

import javax.validation.constraints.Size;

public class CommentForm {

    @Size(min = 4, max = 500)
    private String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
