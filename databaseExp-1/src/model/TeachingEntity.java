package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "teaching", schema = "databaseexp", catalog = "")
@IdClass(TeachingEntityPK.class)
public class TeachingEntity implements Serializable {
    private String openTerm;
    private String cid;
    private String tid;
    private String ttime;
    private Double scoreRate;

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
    @Column(name = "ttime", nullable = true, length = 12)
    public String getTtime() {
        return ttime;
    }

    public void setTtime(String ttime) {
        this.ttime = ttime;
    }

    @Basic
    @Column(name = "scoreRate", nullable = true, precision = 0)
    public Double getScoreRate() {
        return scoreRate;
    }

    public void setScoreRate(Double scoreRate) {
        this.scoreRate = scoreRate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TeachingEntity that = (TeachingEntity) o;
        return Objects.equals(openTerm, that.openTerm) &&
                Objects.equals(cid, that.cid) &&
                Objects.equals(tid, that.tid) &&
                Objects.equals(ttime, that.ttime) &&
                Objects.equals(scoreRate, that.scoreRate);
    }

    @Override
    public int hashCode() {

        return Objects.hash(openTerm, cid, tid, ttime, scoreRate);
    }
}
