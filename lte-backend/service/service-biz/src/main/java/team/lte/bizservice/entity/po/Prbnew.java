package team.lte.bizservice.entity.po;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * <p>
 * PRB干扰数据（小时级）
 * </p>
 *
 * @author lte
 * @since 2022-06-02
 */
@Getter
@Setter
@TableName("tbprbnew")
@Schema( description = "PRB干扰数据（小时级）")
public class Prbnew implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema( description = "起始时间")
    private String hourTime;

    @Schema( description = "网元/基站名称")
    private String enodebName;

    @Schema( description = "第0个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise0;

    @Schema( description = "第1个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise1;

    @Schema( description = "第2个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise2;

    @Schema( description = "第3个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise3;

    @Schema( description = "第4个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise4;

    @Schema( description = "第5个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise5;

    @Schema( description = "第6个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise6;

    @Schema( description = "第7个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise7;

    @Schema( description = "第8个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise8;

    @Schema( description = "第9个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise9;

    @Schema( description = "第10个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise10;

    @Schema( description = "第11个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise11;

    @Schema( description = "第12个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise12;

    @Schema( description = "第13个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise13;

    @Schema( description = "第14个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise14;

    @Schema( description = "第15个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise15;

    @Schema( description = "第16个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise16;

    @Schema( description = "第17个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise17;

    @Schema( description = "第18个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise18;

    @Schema( description = "第19个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise19;

    @Schema( description = "第20个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise20;

    @Schema( description = "第21个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise21;

    @Schema( description = "第22个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise22;

    @Schema( description = "第23个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise23;

    @Schema( description = "第24个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise24;

    @Schema( description = "第25个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise25;

    @Schema( description = "第26个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise26;

    @Schema( description = "第27个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise27;

    @Schema( description = "第28个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise28;

    @Schema( description = "第29个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise29;

    @Schema( description = "第30个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise30;

    @Schema( description = "第31个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise31;

    @Schema( description = "第32个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise32;

    @Schema( description = "第33个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise33;

    @Schema( description = "第34个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise34;

    @Schema( description = "第35个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise35;

    @Schema( description = "第36个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise36;

    @Schema( description = "第37个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise37;

    @Schema( description = "第38个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise38;

    @Schema( description = "第39个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise39;

    @Schema( description = "第40个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise40;

    @Schema( description = "第41个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise41;

    @Schema( description = "第42个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise42;

    @Schema( description = "第43个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise43;

    @Schema( description = "第44个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise44;

    @Schema( description = "第45个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise45;

    @Schema( description = "第46个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise46;

    @Schema( description = "第47个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise47;

    @Schema( description = "第48个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise48;

    @Schema( description = "第49个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise49;

    @Schema( description = "第50个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise50;

    @Schema( description = "第51个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise51;

    @Schema( description = "第52个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise52;

    @Schema( description = "第53个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise53;

    @Schema( description = "第54个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise54;

    @Schema( description = "第55个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise55;

    @Schema( description = "第56个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise56;

    @Schema( description = "第57个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise57;

    @Schema( description = "第58个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise58;

    @Schema( description = "第59个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise59;

    @Schema( description = "第60个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise60;

    @Schema( description = "第61个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise61;

    @Schema( description = "第62个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise62;

    @Schema( description = "第63个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise63;

    @Schema( description = "第64个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise64;

    @Schema( description = "第65个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise65;

    @Schema( description = "第66个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise66;

    @Schema( description = "第67个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise67;

    @Schema( description = "第68个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise68;

    @Schema( description = "第69个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise69;

    @Schema( description = "第70个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise70;

    @Schema( description = "第71个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise71;

    @Schema( description = "第72个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise72;

    @Schema( description = "第73个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise73;

    @Schema( description = "第74个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise74;

    @Schema( description = "第75个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise75;

    @Schema( description = "第76个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise76;

    @Schema( description = "第77个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise77;

    @Schema( description = "第78个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise78;

    @Schema( description = "第79个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise79;

    @Schema( description = "第80个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise80;

    @Schema( description = "第81个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise81;

    @Schema( description = "第82个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise82;

    @Schema( description = "第83个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise83;

    @Schema( description = "第84个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise84;

    @Schema( description = "第85个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise85;

    @Schema( description = "第86个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise86;

    @Schema( description = "第87个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise87;

    @Schema( description = "第88个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise88;

    @Schema( description = "第89个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise89;

    @Schema( description = "第90个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise90;

    @Schema( description = "第91个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise91;

    @Schema( description = "第92个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise92;

    @Schema( description = "第93个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise93;

    @Schema( description = "第94个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise94;

    @Schema( description = "第95个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise95;

    @Schema( description = "第96个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise96;

    @Schema( description = "第97个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise97;

    @Schema( description = "第98个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise98;

    @Schema( description = "第99个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private BigDecimal noise99;


}
