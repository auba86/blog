package blog.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long fbId;

    private String fbName;

    private String fbPhoneNumber;

    private String fbEmail;

    private String fbDescription;

    public Long getFbId() {
        return fbId;
    }

    public void setFbId(Long fbId) {
        this.fbId = fbId;
    }

    public String getFbName() {
        return fbName;
    }

    public void setFbName(String fbName) {
        this.fbName = fbName;
    }

    public String getFbPhoneNumber() {
        return fbPhoneNumber;
    }

    public void setFbPhoneNumber(String fbPhoneNumber) {
        this.fbPhoneNumber = fbPhoneNumber;
    }

    public String getFbEmail() {
        return fbEmail;
    }

    public void setFbEmail(String fbEmail) {
        this.fbEmail = fbEmail;
    }

    public String getFbDescription() {
        return fbDescription;
    }

    public void setFbDescription(String fbDescription) {
        this.fbDescription = fbDescription;
    }
}
