package model.microModel;

import java.io.Serializable;

public class Score implements Serializable {
    String semester;
    String cid;
    int ExamResults;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getExamResults() {
        return ExamResults;
    }

    public void setExamResults(int examResults) {
        ExamResults = examResults;
    }

    public int getTotalResults() {
        return TotalResults;
    }

    public void setTotalResults(int totalResults) {
        TotalResults = totalResults;
    }

    public int getUsualResults() {
        return UsualResults;
    }

    public void setUsualResults(int usualResults) {
        UsualResults = usualResults;
    }

    int TotalResults;
    int UsualResults;
}