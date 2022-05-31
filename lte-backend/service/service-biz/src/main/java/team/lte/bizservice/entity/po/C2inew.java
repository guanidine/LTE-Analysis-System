package team.lte.bizservice.entity.po;

import com.baomidou.mybatisplus.annotation.*;

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
@TableName("tbc2inew")
@Schema(description = "C2inew对象")
@KeySequence(value = "tbc2inew_id_seq", dbType= DbType.POSTGRE_SQL)
public class C2inew implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema( description="主小区")
    private String scell;

    @Schema( description="邻小区")
    private String ncell;

    @Schema( description="C2I均值")
    private Double c2iMean;

    @Schema( description="C2I方差")
    private Double std;

    @Schema( description="C2I差值小于9的概率")
    private Double prbC2i9;

    @Schema( description="C2I绝对值小于6的概率")
    private Double prbAbs6;

    @TableId(value = "id", type = IdType.AUTO)
    @Schema( description="id")
    private Long id;


}
