package team.lte.commonutils.result;

import java.util.HashMap;
import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 统一返回结果。
 */
@Data
@Schema(description = "统一返回结果")
public class R {

    @Schema(description = "标识码，true表示成功，false表示失败")
    private Boolean success;

    @Schema(description = "返回码，20000表示成功，20001表示失败")
    private Integer code;

    @Schema(description = "提示信息，供报错时使用")
    private String message;

    @Schema(description = "返回数据，使用Map<String, Object>返回key-value键值对")
    private Map<String, Object> data = new HashMap<>();

    private R() {}

    public static R ok() {
        R r = new R();
        r.setSuccess(true);
        r.setCode(ResultCode.SUCCESS);
        r.setMessage("成功");
        return r;
    }

    public static R error() {
        R r = new R();
        r.setSuccess(false);
        r.setCode(ResultCode.ERROR);
        r.setMessage("失败");
        return r;
    }

    public R success(Boolean success) {
        this.setSuccess(success);
        return this;
    }

    public R message(String message) {
        this.setMessage(message);
        return this;
    }

    public R code(Integer code) {
        this.setCode(code);
        return this;
    }

    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    public R data(Map<String, Object> map) {
        this.setData(map);
        return this;
    }

}
