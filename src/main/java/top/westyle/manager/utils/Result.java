package top.westyle.manager.utils;

/**
 *统一返回数据bean
 */
public class Result {
    /**
     * 返回code默认0为正确，其他请求失败
     */
    private int code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回数据
     */
    private Object data;

    public Result() {
        super();
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public static Result instance(int code, String msg){
        return new Result(code, msg);
    }

    public static Result success(){
        return new Result(ResponseCode.success.getCode(),ResponseCode.success.getMsg());
    }

    public static Result success(Object data){
        return new Result(ResponseCode.success.getCode(), ResponseCode.success.getMsg(), data);
    }

    public static Result error() {
        return new Result(ResponseCode.error.getCode(), ResponseCode.error.getMsg());
    }

    public static Result error(String msg) {
        return new Result(ResponseCode.error.getCode(), msg);
    }
}
