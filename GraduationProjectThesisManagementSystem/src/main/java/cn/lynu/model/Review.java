package cn.lynu.model;

public class Review {
    private  String user_id;
    private String reviewer;
    private String comment;

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getReviewID() {
        return reviewID;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    private int  score;



    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getReview1ID() {
        return reviewID;
    }

    public void setReview1ID(int review1ID) {
        this.reviewID = review1ID;
    }

    private int reviewID;
}
