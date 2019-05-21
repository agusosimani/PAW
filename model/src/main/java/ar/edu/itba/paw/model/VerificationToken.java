package ar.edu.itba.paw.model;

import java.sql.Timestamp;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class VerificationToken {
    private static final int EXPIRATION = 60 * 24;
    private int tokenId;
    private String token;
    private int userID;
    private Date expiryDate;

    public VerificationToken(String token, int userID, Date expiryDate) {
        this.token = token;
        this.userID = userID;
        this.expiryDate = expiryDate;
    }

    public static class Builder {
        private String token;
        private int userID;
        private Date expiryDate;

        public Builder(String token, int userId) {
            this.expiryDate = calculateExpiryDate(EXPIRATION);
            this.token = token;
            this.userID = userId;
        }

        private Date calculateExpiryDate(int expiration) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Timestamp(cal.getTime().getTime()));
            cal.add(Calendar.MINUTE, expiration);
            return new Date(cal.getTime().getTime());
        }

        public String getToken() {
            return token;
        }

        public int getUserId() {
            return userID;
        }

        public Date getExpiryDate() {
            return expiryDate;
        }
    }

    public VerificationToken(VerificationToken.Builder tokenBuilder) {
        this.expiryDate = tokenBuilder.getExpiryDate();
        this.token = tokenBuilder.getToken();
        this.userID = tokenBuilder.getUserId();
    }

    public static int getEXPIRATION() {
        return EXPIRATION;
    }

    public int getTokenId() {
        return tokenId;
    }

    public void setTokenId(int id) {
        this.tokenId = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getUserID() {
        return userID;
    }

    public void setUser(int userID) {
        this.userID = userID;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }


}
