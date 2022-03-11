package team.lte.businessquery.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lte
 * @since 2022-03-10
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tbcell")
@ApiModel(value = "Cell对象", description = "小区配置信息")
public class Cell implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("城市/地区名称")
    private String city;

    @ApiModelProperty("小区ID")
    @TableId(type = IdType.INPUT)
    private String sectorId;

    @ApiModelProperty("小区名称")
    private String sectorName;

    @ApiModelProperty("小区所属基站标识")
    private Integer enodebid;

    @ApiModelProperty("基站名称")
    private String enodebName;

    @ApiModelProperty("小区配置的频点编号")
    private Integer earfcn;

    @ApiModelProperty("物理小区标识")
    private Integer pci;

    @ApiModelProperty("主同步信号标识")
    private Integer pss;

    @ApiModelProperty("辅同步信号表示")
    private Integer sss;

    @ApiModelProperty("跟踪区编码")
    private Integer tac;

    private String vendor;

    private Float longitude;

    private Float latitude;

    private String style;

    @ApiModelProperty("小区天线方位角")
    private Float azimuth;

    @ApiModelProperty("小区天线高度")
    private Float height;

    @ApiModelProperty("小区天线电下倾角")
    private Float electtilt;

    @ApiModelProperty("小区天线机械下倾角")
    private Float mechtilt;

    @ApiModelProperty("总下倾角")
    private Float totletilt;


}
