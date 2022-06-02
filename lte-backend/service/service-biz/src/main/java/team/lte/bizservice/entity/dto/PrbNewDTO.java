package team.lte.bizservice.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
public class PrbNewDTO {

    @ExcelProperty(value = "起始时间", index = 0)
    @ColumnWidth(20)
    @NotNull
    private String startTime;

    @ExcelProperty(value = "网元/基站名称", index = 1)
    @ColumnWidth(20)
    @NotNull
    private String enodebName;

    @ExcelProperty(value = "第0个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 2)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise0;

    @ExcelProperty(value = "第1个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 3)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise1;

    @ExcelProperty(value = "第2个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 4)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise2;

    @ExcelProperty(value = "第3个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 5)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise3;

    @ExcelProperty(value = "第4个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 6)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise4;

    @ExcelProperty(value = "第5个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 7)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise5;

    @ExcelProperty(value = "第6个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 8)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise6;

    @ExcelProperty(value = "第7个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 9)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise7;

    @ExcelProperty(value = "第8个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 10)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise8;

    @ExcelProperty(value = "第9个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 11)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise9;

    @ExcelProperty(value = "第10个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 12)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise10;

    @ExcelProperty(value = "第11个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 13)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise11;

    @ExcelProperty(value = "第12个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 14)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise12;

    @ExcelProperty(value = "第13个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 15)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise13;

    @ExcelProperty(value = "第14个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 16)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise14;

    @ExcelProperty(value = "第15个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 17)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise15;

    @ExcelProperty(value = "第16个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 18)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise16;

    @ExcelProperty(value = "第17个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 19)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise17;

    @ExcelProperty(value = "第18个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 20)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise18;

    @ExcelProperty(value = "第19个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 21)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise19;

    @ExcelProperty(value = "第20个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 22)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise20;

    @ExcelProperty(value = "第21个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 23)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise21;

    @ExcelProperty(value = "第22个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 24)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise22;

    @ExcelProperty(value = "第23个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 25)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise23;

    @ExcelProperty(value = "第24个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 26)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise24;

    @ExcelProperty(value = "第25个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 27)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise25;

    @ExcelProperty(value = "第26个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 28)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise26;

    @ExcelProperty(value = "第27个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 29)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise27;

    @ExcelProperty(value = "第28个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 30)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise28;

    @ExcelProperty(value = "第29个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 31)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise29;

    @ExcelProperty(value = "第30个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 32)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise30;

    @ExcelProperty(value = "第31个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 33)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise31;

    @ExcelProperty(value = "第32个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 34)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise32;

    @ExcelProperty(value = "第33个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 35)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise33;

    @ExcelProperty(value = "第34个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 36)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise34;

    @ExcelProperty(value = "第35个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 37)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise35;

    @ExcelProperty(value = "第36个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 38)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise36;

    @ExcelProperty(value = "第37个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 39)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise37;

    @ExcelProperty(value = "第38个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 40)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise38;

    @ExcelProperty(value = "第39个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 41)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise39;

    @ExcelProperty(value = "第40个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 42)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise40;

    @ExcelProperty(value = "第41个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 43)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise41;

    @ExcelProperty(value = "第42个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 44)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise42;

    @ExcelProperty(value = "第43个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 45)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise43;

    @ExcelProperty(value = "第44个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 46)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise44;

    @ExcelProperty(value = "第45个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 47)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise45;

    @ExcelProperty(value = "第46个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 48)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise46;

    @ExcelProperty(value = "第47个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 49)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise47;

    @ExcelProperty(value = "第48个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 50)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise48;

    @ExcelProperty(value = "第49个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 51)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise49;

    @ExcelProperty(value = "第50个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 52)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise50;

    @ExcelProperty(value = "第51个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 53)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise51;

    @ExcelProperty(value = "第52个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 54)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise52;

    @ExcelProperty(value = "第53个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 55)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise53;

    @ExcelProperty(value = "第54个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 56)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise54;

    @ExcelProperty(value = "第55个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 57)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise55;

    @ExcelProperty(value = "第56个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 58)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise56;

    @ExcelProperty(value = "第57个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 59)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise57;

    @ExcelProperty(value = "第58个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 60)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise58;

    @ExcelProperty(value = "第59个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 61)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise59;

    @ExcelProperty(value = "第60个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 62)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise60;

    @ExcelProperty(value = "第61个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 63)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise61;

    @ExcelProperty(value = "第62个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 64)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise62;

    @ExcelProperty(value = "第63个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 65)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise63;

    @ExcelProperty(value = "第64个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 66)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise64;

    @ExcelProperty(value = "第65个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 67)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise65;

    @ExcelProperty(value = "第66个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 68)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise66;

    @ExcelProperty(value = "第67个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 69)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise67;

    @ExcelProperty(value = "第68个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 70)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise68;

    @ExcelProperty(value = "第69个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 71)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise69;

    @ExcelProperty(value = "第70个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 72)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise70;

    @ExcelProperty(value = "第71个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 73)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise71;

    @ExcelProperty(value = "第72个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 74)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise72;

    @ExcelProperty(value = "第73个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 75)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise73;

    @ExcelProperty(value = "第74个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 76)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise74;

    @ExcelProperty(value = "第75个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 77)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise75;

    @ExcelProperty(value = "第76个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 78)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise76;

    @ExcelProperty(value = "第77个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 79)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise77;

    @ExcelProperty(value = "第78个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 80)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise78;

    @ExcelProperty(value = "第79个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 81)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise79;

    @ExcelProperty(value = "第80个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 82)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise80;

    @ExcelProperty(value = "第81个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 83)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise81;

    @ExcelProperty(value = "第82个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 84)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise82;

    @ExcelProperty(value = "第83个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 85)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise83;

    @ExcelProperty(value = "第84个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 86)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise84;

    @ExcelProperty(value = "第85个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 87)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise85;

    @ExcelProperty(value = "第86个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 88)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise86;

    @ExcelProperty(value = "第87个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 89)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise87;

    @ExcelProperty(value = "第88个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 90)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise88;

    @ExcelProperty(value = "第89个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 91)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise89;

    @ExcelProperty(value = "第90个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 92)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise90;

    @ExcelProperty(value = "第91个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 93)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise91;

    @ExcelProperty(value = "第92个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 94)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise92;

    @ExcelProperty(value = "第93个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 95)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise93;

    @ExcelProperty(value = "第94个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 96)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise94;

    @ExcelProperty(value = "第95个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 97)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise95;

    @ExcelProperty(value = "第96个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 98)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise96;

    @ExcelProperty(value = "第97个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 99)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise97;

    @ExcelProperty(value = "第98个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 100)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise98;

    @ExcelProperty(value = "第99个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 101)
    @ColumnWidth(10)
    @NotNull
    private BigDecimal noise99;
}
