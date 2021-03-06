package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "score", schema = "databaseexp", catalog = "")
@IdClass(ScoreEntityPK.class)
public class ScoreEntity implements Serializable {
    private String academicYear;
    private String semester;
    private String sid;
    private String cid;
    private Integer usualResults;
    private Integer examResults;
    private Integer totalResults;
    private String tid;

    @Basic
    @Column(name = "academic_year", nullable = true, length = 20)
    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    @Basic
    @Column(name = "semester", nullable = true, length = 10)
    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Id
    @Column(name = "sid", nullable = false, length = 10)
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Id
    @Column(name = "cid", nullable = false, length = 10)
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "usualResults", nullable = true)
    public Integer getUsualResults() {
        return usualResults;
    }

    public void setUsualResults(Integer usualResults) {
        this.usualResults = usualResults;
    }

    @Basic
    @Column(name = "examResults", nullable = true)
    public Integer getExamResults() {
        return examResults;
    }

    public void setExamResults(Integer examResults) {
        this.examResults = examResults;
    }

    @Basic
    @Column(name = "totalResults", nullable = true)
    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    @Basic
    @Column(name = "tid", nullable = false, length = 8)
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreEntity that = (ScoreEntity) o;
        return Objects.equals(academicYear, that.academicYear) &&
                Objects.equals(semester, that.semester) &&
                Objects.equals(sid, that.sid) &&
                Objects.equals(cid, that.cid) &&
                Objects.equals(usualResults, that.usualResults) &&
                Objects.equals(examResults, that.examResults) &&
                Objects.equals(totalResults, that.totalResults) &&
                Objects.equals(tid, that.tid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(academicYear, semester, sid, cid, usualResults, examResults, totalResults, tid);
    }
}
