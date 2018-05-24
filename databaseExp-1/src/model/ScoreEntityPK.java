package model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class ScoreEntityPK implements Serializable {
    private String sid;
    private String cid;

    @Column(name = "sid", nullable = false, length = 10)
    @Id
    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    @Column(name = "cid", nullable = false, length = 10)
    @Id
    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreEntityPK that = (ScoreEntityPK) o;
        return Objects.equals(sid, that.sid) &&
                Objects.equals(cid, that.cid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(sid, cid);
    }
}
