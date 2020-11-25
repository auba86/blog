package blog.model;

import javax.persistence.*;

@Entity
public class Rating {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ratingId;

    private Integer ratingValue;

    private Integer likeValue;

    private Integer dislikeValue;

    @OneToOne(mappedBy = "rating", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Record record;

    public Long getRatingId() {
        return ratingId;
    }

    public void setRatingId(Long ratingId) {
        this.ratingId = ratingId;
    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setRatingValue(Integer ratingValue) {
        this.ratingValue = ratingValue;
    }

    public Record getRecord() {
        return record;
    }

    public void setRecord(Record record) {
        this.record = record;
    }

    public Integer getLikeValue() {
        return likeValue;
    }

    public void setLikeValue(Integer likeValue) {
        this.likeValue = likeValue;
    }

    public Integer getDislikeValue() {
        return dislikeValue;
    }

    public void setDislikeValue(Integer dislikeValue) {
        this.dislikeValue = dislikeValue;
    }
}