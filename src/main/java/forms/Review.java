package forms;

public class Review {

    private String revId;
    private String revContent;
    private String dateCreated;
    private String userId;

    public Review(String userId, String revId, String revContent, String dateCreated) {
        //TODO Auto-generated constructor stub
        this.revId = revId;
        this.revContent = revContent;
        this.dateCreated = dateCreated;
        this.userId = userId;
    }

    
    
}
