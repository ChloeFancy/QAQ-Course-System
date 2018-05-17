package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "myscore", schema = "databaseexp", catalog = "")
@IdClass(MyscoreEntityPK.class)
public class MyscoreEntity {
    private String sid;
    private String openTerm;
    private String cid;
    private String tid;
    private Integer usualResults;
    private Integer examResults;
    private Integer totalResults;

    @Id
    @Column(name = "sid", nullable = false, length = 10)
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Id
    @Column(name = "openTerm", nullable = false, length = 14)
    public String getOpenTerm() {
        return openTerm;
    }

    public void setOpenTerm(String openTerm) {
        this.openTerm = openTerm;
    }

    @Id
    @Column(name = "cid", nullable = false, length = 10)
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Id
    @Column(name = "tid", nullable = false, length = 8)
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MyscoreEntity that = (MyscoreEntity) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(openTerm, that.openTerm) &&
                Objects.equals(cid, that.cid) &&
                Objects.equals(tid, that.tid) &&
                Objects.equals(usualResults, that.usualResults) &&
                Objects.equals(examResults, that.examResults) &&
                Objects.equals(totalResults, that.totalResults);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sid, openTerm, cid, tid, usualResults, examResults, totalResults);
    }
}
