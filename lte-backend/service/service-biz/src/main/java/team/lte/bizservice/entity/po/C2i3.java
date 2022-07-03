package team.lte.bizservice.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 *
 * </p>
 *
 * @author lte
 * @since 2022-05-31
 */
@Getter
@Setter
@TableName("tbc2i3")
@Schema( description = "C2i3对象")
public class C2i3 implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema( description = "干扰小区A")
    private String cellA;

    @Schema( description = "干扰小区B")
    private String cellB;

    @Schema( description = "干扰小区C")
    private String cellC;


}
