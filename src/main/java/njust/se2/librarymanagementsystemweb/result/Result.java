package njust.se2.librarymanagementsystemweb.result;

import lombok.Data;

@Data

public class Result {
    private Object data;
    private String message;
    //响应码
    private int code;

    public Result(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    Result(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
