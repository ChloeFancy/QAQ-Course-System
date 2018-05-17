package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class MyscoreEntityPK implements Serializable {
    private String sid;
    private String openTerm;
    private String cid;
    private String tid;

    @Column(name = "sid", nullable = false, length = 10)
    @Id
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

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
        MyscoreEntityPK that = (MyscoreEntityPK) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(openTerm, that.openTerm) &&
                Objects.equals(cid, that.cid) &&
                Objects.equals(tid, that.tid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sid, openTerm, cid, tid);
    }
}
