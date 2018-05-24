package model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "banji", schema = "databaseexp", catalog = "")
public class BanjiEntity {
    private String cName;
    private Integer cNumber;
    private String pid;

    @Id
    @Column(name = "cName", nullable = false, length = 30)
    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    @Basic
    @Column(name = "cNumber", nullable = true)
    public Integer getcNumber() {
        return cNumber;
    }

    public void setcNumber(Integer cNumber) {
        this.cNumber = cNumber;
    }

    @Basic
    @Column(name = "pid", nullable = true, length = 7)
    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BanjiEntity that = (BanjiEntity) o;
        return Objects.equals(cName, that.cName) &&
                Objects.equals(cNumber, that.cNumber) &&
                Objects.equals(pid, that.pid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(cName, cNumber, pid);
    }
}
