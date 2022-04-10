package team.lte.businessquery.entity.po;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author lte
 * @since 2022-04-07
 */
@Getter
@Setter
@TableName("tbprb")
@Schema(description = "PRB干扰数据")
public class Prb implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "ID")
    private String id;

    @Schema(description = "起始时间")
    private String startTime;

    @Schema(description = "网元/基站名称")
    private String enodebName;

    @Schema(description = "小区描述")
    private String sectorDescription;

    @Schema(description = "小区名")
    private String sectorName;

    @Schema(description = "第0个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise0;

    @Schema(description = "第1个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise1;

    @Schema(description = "第2个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise2;

    @Schema(description = "第3个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise3;

    @Schema(description = "第4个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise4;

    @Schema(description = "第5个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise5;

    @Schema(description = "第6个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise6;

    @Schema(description = "第7个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise7;

    @Schema(description = "第8个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise8;

    @Schema(description = "第9个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise9;

    @Schema(description = "第10个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise10;

    @Schema(description = "第11个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise11;

    @Schema(description = "第12个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise12;

    @Schema(description = "第13个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise13;

    @Schema(description = "第14个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise14;

    @Schema(description = "第15个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise15;

    @Schema(description = "第16个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise16;

    @Schema(description = "第17个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise17;

    @Schema(description = "第18个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise18;

    @Schema(description = "第19个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise19;

    @Schema(description = "第20个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise20;

    @Schema(description = "第21个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise21;

    @Schema(description = "第22个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise22;

    @Schema(description = "第23个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise23;

    @Schema(description = "第24个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise24;

    @Schema(description = "第25个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise25;

    @Schema(description = "第26个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise26;

    @Schema(description = "第27个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise27;

    @Schema(description = "第28个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise28;

    @Schema(description = "第29个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise29;

    @Schema(description = "第30个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise30;

    @Schema(description = "第31个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise31;

    @Schema(description = "第32个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise32;

    @Schema(description = "第33个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise33;

    @Schema(description = "第34个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise34;

    @Schema(description = "第35个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise35;

    @Schema(description = "第36个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise36;

    @Schema(description = "第37个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise37;

    @Schema(description = "第38个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise38;

    @Schema(description = "第39个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise39;

    @Schema(description = "第40个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise40;

    @Schema(description = "第41个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise41;

    @Schema(description = "第42个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise42;

    @Schema(description = "第43个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise43;

    @Schema(description = "第44个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise44;

    @Schema(description = "第45个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise45;

    @Schema(description = "第46个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise46;

    @Schema(description = "第47个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise47;

    @Schema(description = "第48个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise48;

    @Schema(description = "第49个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise49;

    @Schema(description = "第50个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise50;

    @Schema(description = "第51个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise51;

    @Schema(description = "第52个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise52;

    @Schema(description = "第53个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise53;

    @Schema(description = "第54个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise54;

    @Schema(description = "第55个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise55;

    @Schema(description = "第56个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise56;

    @Schema(description = "第57个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise57;

    @Schema(description = "第58个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise58;

    @Schema(description = "第59个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise59;

    @Schema(description = "第60个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise60;

    @Schema(description = "第61个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise61;

    @Schema(description = "第62个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise62;

    @Schema(description = "第63个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise63;

    @Schema(description = "第64个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise64;

    @Schema(description = "第65个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise65;

    @Schema(description = "第66个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise66;

    @Schema(description = "第67个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise67;

    @Schema(description = "第68个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise68;

    @Schema(description = "第69个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise69;

    @Schema(description = "第70个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise70;

    @Schema(description = "第71个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise71;

    @Schema(description = "第72个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise72;

    @Schema(description = "第73个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise73;

    @Schema(description = "第74个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise74;

    @Schema(description = "第75个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise75;

    @Schema(description = "第76个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise76;

    @Schema(description = "第77个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise77;

    @Schema(description = "第78个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise78;

    @Schema(description = "第79个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise79;

    @Schema(description = "第80个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise80;

    @Schema(description = "第81个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise81;

    @Schema(description = "第82个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise82;

    @Schema(description = "第83个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise83;

    @Schema(description = "第84个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise84;

    @Schema(description = "第85个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise85;

    @Schema(description = "第86个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise86;

    @Schema(description = "第87个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise87;

    @Schema(description = "第88个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise88;

    @Schema(description = "第89个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise89;

    @Schema(description = "第90个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise90;

    @Schema(description = "第91个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise91;

    @Schema(description = "第92个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise92;

    @Schema(description = "第93个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise93;

    @Schema(description = "第94个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise94;

    @Schema(description = "第95个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise95;

    @Schema(description = "第96个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise96;

    @Schema(description = "第97个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise97;

    @Schema(description = "第98个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise98;

    @Schema(description = "第99个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)")
    private Integer noise99;

}
