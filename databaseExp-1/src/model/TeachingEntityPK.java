package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class TeachingEntityPK implements Serializable {
    private String openTerm;
    private String cid;
    private String tid;

    @Column(name = "openTerm", nullable = false, length = 14)
    @Id
    public String getOpenTerm() {
        return openTerm;
    }

    public void setOpenTerm(String openTerm) {
        this.openTerm = openTerm;
    }

    @Column(name = "cid", nullable = false, length = 10)
    @Id
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Column(name = "tid", nullable = false, length = 8)
    @Id
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
        TeachingEntityPK that = (TeachingEntityPK) o;
        return Objects.equals(openTerm, that.openTerm) &&
                Objects.equals(cid, that.cid) &&
                Objects.equals(tid, that.tid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(openTerm, cid, tid);
    }
}
