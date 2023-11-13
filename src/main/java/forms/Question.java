package forms;

import java.util.ArrayList;
import helper.Misc.prefix;

public class Question extends Survey {
    
    private String qId;
    private boolean isCompulsory;
    private String surveyId;
    private String qText;
    private int qPos;
    private ArrayList<String>qType;

    public Question(
        String surveyId,
        String scId,
        boolean isPublic,
        ArrayList<String>participants,
        boolean isCompulsory,
        String qId,
        String qText,
        int qPos,
        ArrayList<String>qType){
        super(surveyId, scId, isPublic, qType);
        this.qId = qId;
        this.isCompulsory = isCompulsory;
        this.surveyId = surveyId;
        this.qText = qText;
        this.qPos = qPos;
        this.qType = qType;




        }

    public Question(String[] args){

    }    
}   
