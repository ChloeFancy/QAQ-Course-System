package model;

import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.List;

@IdClass(BasicResponse.class)
public class BasicResponse implements Serializable{

    private static final long serialVersionUID = 1L;
    private String resCode;
    private String resMsg;
    private Object data = "";
    private List<?> dataList;


    public String getResCode() {
        return resCode;
    }

    public void setResCode(String resCode) {
        this.resCode = resCode;
    }

    public String getResMsg() {
        return resMsg;
    }

    public void setResMsg(String resMsg) {
        this.resMsg = resMsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

}
