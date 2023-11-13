package forms;

public class Review extends User{

    private String revId;
    private String revContent;
    private String dateCreated;
    private String userId;

    public Review(String nid, String username, String fullname, String password, int age, String gender, String email,
            int phoneNo, String userId, String revId, String revContent, String dateCreated) {
        super(nid, username, fullname, password, age, gender, email, phoneNo, userId);
        //TODO Auto-generated constructor stub
        this.revId = revId;
        this.revContent = revContent;
        this.dateCreated = dateCreated;
        this.userId = userId;
    }

    
    
}
