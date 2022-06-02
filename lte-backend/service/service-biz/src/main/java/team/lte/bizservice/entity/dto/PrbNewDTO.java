package team.lte.bizservice.entity.dto;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PrbDTO {

    @ExcelProperty(value = "起始时间", index = 0)
    @ColumnWidth(20)
    @NotNull
    private String startTime;

    @ExcelProperty(value = "网元/基站名称", index = 1)
    @ColumnWidth(20)
    @NotNull
    private String enodebName;

    @ExcelProperty(value = "小区描述", index = 2)
    @ColumnWidth(120)
    @NotNull
    private String sectorDescription;

    @ExcelProperty(value = "小区名", index = 3)
    @ColumnWidth(20)
    @NotNull
    private String sectorName;

    @ExcelProperty(value = "第0个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 4)
    @ColumnWidth(10)
    @NotNull
    private Integer noise0;

    @ExcelProperty(value = "第1个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 5)
    @ColumnWidth(10)
    @NotNull
    private Integer noise1;

    @ExcelProperty(value = "第2个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 6)
    @ColumnWidth(10)
    @NotNull
    private Integer noise2;

    @ExcelProperty(value = "第3个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 7)
    @ColumnWidth(10)
    @NotNull
    private Integer noise3;

    @ExcelProperty(value = "第4个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 8)
    @ColumnWidth(10)
    @NotNull
    private Integer noise4;

    @ExcelProperty(value = "第5个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 9)
    @ColumnWidth(10)
    @NotNull
    private Integer noise5;

    @ExcelProperty(value = "第6个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 10)
    @ColumnWidth(10)
    @NotNull
    private Integer noise6;

    @ExcelProperty(value = "第7个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 11)
    @ColumnWidth(10)
    @NotNull
    private Integer noise7;

    @ExcelProperty(value = "第8个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 12)
    @ColumnWidth(10)
    @NotNull
    private Integer noise8;

    @ExcelProperty(value = "第9个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 13)
    @ColumnWidth(10)
    @NotNull
    private Integer noise9;

    @ExcelProperty(value = "第10个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 14)
    @ColumnWidth(10)
    @NotNull
    private Integer noise10;

    @ExcelProperty(value = "第11个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 15)
    @ColumnWidth(10)
    @NotNull
    private Integer noise11;

    @ExcelProperty(value = "第12个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 16)
    @ColumnWidth(10)
    @NotNull
    private Integer noise12;

    @ExcelProperty(value = "第13个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 17)
    @ColumnWidth(10)
    @NotNull
    private Integer noise13;

    @ExcelProperty(value = "第14个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 18)
    @ColumnWidth(10)
    @NotNull
    private Integer noise14;

    @ExcelProperty(value = "第15个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 19)
    @ColumnWidth(10)
    @NotNull
    private Integer noise15;

    @ExcelProperty(value = "第16个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 20)
    @ColumnWidth(10)
    @NotNull
    private Integer noise16;

    @ExcelProperty(value = "第17个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 21)
    @ColumnWidth(10)
    @NotNull
    private Integer noise17;

    @ExcelProperty(value = "第18个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 22)
    @ColumnWidth(10)
    @NotNull
    private Integer noise18;

    @ExcelProperty(value = "第19个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 23)
    @ColumnWidth(10)
    @NotNull
    private Integer noise19;

    @ExcelProperty(value = "第20个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 24)
    @ColumnWidth(10)
    @NotNull
    private Integer noise20;

    @ExcelProperty(value = "第21个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 25)
    @ColumnWidth(10)
    @NotNull
    private Integer noise21;

    @ExcelProperty(value = "第22个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 26)
    @ColumnWidth(10)
    @NotNull
    private Integer noise22;

    @ExcelProperty(value = "第23个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 27)
    @ColumnWidth(10)
    @NotNull
    private Integer noise23;

    @ExcelProperty(value = "第24个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 28)
    @ColumnWidth(10)
    @NotNull
    private Integer noise24;

    @ExcelProperty(value = "第25个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 29)
    @ColumnWidth(10)
    @NotNull
    private Integer noise25;

    @ExcelProperty(value = "第26个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 30)
    @ColumnWidth(10)
    @NotNull
    private Integer noise26;

    @ExcelProperty(value = "第27个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 31)
    @ColumnWidth(10)
    @NotNull
    private Integer noise27;

    @ExcelProperty(value = "第28个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 32)
    @ColumnWidth(10)
    @NotNull
    private Integer noise28;

    @ExcelProperty(value = "第29个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 33)
    @ColumnWidth(10)
    @NotNull
    private Integer noise29;

    @ExcelProperty(value = "第30个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 34)
    @ColumnWidth(10)
    @NotNull
    private Integer noise30;

    @ExcelProperty(value = "第31个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 35)
    @ColumnWidth(10)
    @NotNull
    private Integer noise31;

    @ExcelProperty(value = "第32个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 36)
    @ColumnWidth(10)
    @NotNull
    private Integer noise32;

    @ExcelProperty(value = "第33个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 37)
    @ColumnWidth(10)
    @NotNull
    private Integer noise33;

    @ExcelProperty(value = "第34个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 38)
    @ColumnWidth(10)
    @NotNull
    private Integer noise34;

    @ExcelProperty(value = "第35个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 39)
    @ColumnWidth(10)
    @NotNull
    private Integer noise35;

    @ExcelProperty(value = "第36个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 40)
    @ColumnWidth(10)
    @NotNull
    private Integer noise36;

    @ExcelProperty(value = "第37个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 41)
    @ColumnWidth(10)
    @NotNull
    private Integer noise37;

    @ExcelProperty(value = "第38个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 42)
    @ColumnWidth(10)
    @NotNull
    private Integer noise38;

    @ExcelProperty(value = "第39个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 43)
    @ColumnWidth(10)
    @NotNull
    private Integer noise39;

    @ExcelProperty(value = "第40个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 44)
    @ColumnWidth(10)
    @NotNull
    private Integer noise40;

    @ExcelProperty(value = "第41个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 45)
    @ColumnWidth(10)
    @NotNull
    private Integer noise41;

    @ExcelProperty(value = "第42个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 46)
    @ColumnWidth(10)
    @NotNull
    private Integer noise42;

    @ExcelProperty(value = "第43个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 47)
    @ColumnWidth(10)
    @NotNull
    private Integer noise43;

    @ExcelProperty(value = "第44个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 48)
    @ColumnWidth(10)
    @NotNull
    private Integer noise44;

    @ExcelProperty(value = "第45个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 49)
    @ColumnWidth(10)
    @NotNull
    private Integer noise45;

    @ExcelProperty(value = "第46个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 50)
    @ColumnWidth(10)
    @NotNull
    private Integer noise46;

    @ExcelProperty(value = "第47个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 51)
    @ColumnWidth(10)
    @NotNull
    private Integer noise47;

    @ExcelProperty(value = "第48个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 52)
    @ColumnWidth(10)
    @NotNull
    private Integer noise48;

    @ExcelProperty(value = "第49个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 53)
    @ColumnWidth(10)
    @NotNull
    private Integer noise49;

    @ExcelProperty(value = "第50个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 54)
    @ColumnWidth(10)
    @NotNull
    private Integer noise50;

    @ExcelProperty(value = "第51个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 55)
    @ColumnWidth(10)
    @NotNull
    private Integer noise51;

    @ExcelProperty(value = "第52个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 56)
    @ColumnWidth(10)
    @NotNull
    private Integer noise52;

    @ExcelProperty(value = "第53个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 57)
    @ColumnWidth(10)
    @NotNull
    private Integer noise53;

    @ExcelProperty(value = "第54个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 58)
    @ColumnWidth(10)
    @NotNull
    private Integer noise54;

    @ExcelProperty(value = "第55个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 59)
    @ColumnWidth(10)
    @NotNull
    private Integer noise55;

    @ExcelProperty(value = "第56个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 60)
    @ColumnWidth(10)
    @NotNull
    private Integer noise56;

    @ExcelProperty(value = "第57个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 61)
    @ColumnWidth(10)
    @NotNull
    private Integer noise57;

    @ExcelProperty(value = "第58个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 62)
    @ColumnWidth(10)
    @NotNull
    private Integer noise58;

    @ExcelProperty(value = "第59个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 63)
    @ColumnWidth(10)
    @NotNull
    private Integer noise59;

    @ExcelProperty(value = "第60个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 64)
    @ColumnWidth(10)
    @NotNull
    private Integer noise60;

    @ExcelProperty(value = "第61个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 65)
    @ColumnWidth(10)
    @NotNull
    private Integer noise61;

    @ExcelProperty(value = "第62个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 66)
    @ColumnWidth(10)
    @NotNull
    private Integer noise62;

    @ExcelProperty(value = "第63个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 67)
    @ColumnWidth(10)
    @NotNull
    private Integer noise63;

    @ExcelProperty(value = "第64个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 68)
    @ColumnWidth(10)
    @NotNull
    private Integer noise64;

    @ExcelProperty(value = "第65个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 69)
    @ColumnWidth(10)
    @NotNull
    private Integer noise65;

    @ExcelProperty(value = "第66个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 70)
    @ColumnWidth(10)
    @NotNull
    private Integer noise66;

    @ExcelProperty(value = "第67个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 71)
    @ColumnWidth(10)
    @NotNull
    private Integer noise67;

    @ExcelProperty(value = "第68个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 72)
    @ColumnWidth(10)
    @NotNull
    private Integer noise68;

    @ExcelProperty(value = "第69个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 73)
    @ColumnWidth(10)
    @NotNull
    private Integer noise69;

    @ExcelProperty(value = "第70个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 74)
    @ColumnWidth(10)
    @NotNull
    private Integer noise70;

    @ExcelProperty(value = "第71个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 75)
    @ColumnWidth(10)
    @NotNull
    private Integer noise71;

    @ExcelProperty(value = "第72个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 76)
    @ColumnWidth(10)
    @NotNull
    private Integer noise72;

    @ExcelProperty(value = "第73个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 77)
    @ColumnWidth(10)
    @NotNull
    private Integer noise73;

    @ExcelProperty(value = "第74个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 78)
    @ColumnWidth(10)
    @NotNull
    private Integer noise74;

    @ExcelProperty(value = "第75个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 79)
    @ColumnWidth(10)
    @NotNull
    private Integer noise75;

    @ExcelProperty(value = "第76个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 80)
    @ColumnWidth(10)
    @NotNull
    private Integer noise76;

    @ExcelProperty(value = "第77个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 81)
    @ColumnWidth(10)
    @NotNull
    private Integer noise77;

    @ExcelProperty(value = "第78个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 82)
    @ColumnWidth(10)
    @NotNull
    private Integer noise78;

    @ExcelProperty(value = "第79个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 83)
    @ColumnWidth(10)
    @NotNull
    private Integer noise79;

    @ExcelProperty(value = "第80个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 84)
    @ColumnWidth(10)
    @NotNull
    private Integer noise80;

    @ExcelProperty(value = "第81个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 85)
    @ColumnWidth(10)
    @NotNull
    private Integer noise81;

    @ExcelProperty(value = "第82个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 86)
    @ColumnWidth(10)
    @NotNull
    private Integer noise82;

    @ExcelProperty(value = "第83个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 87)
    @ColumnWidth(10)
    @NotNull
    private Integer noise83;

    @ExcelProperty(value = "第84个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 88)
    @ColumnWidth(10)
    @NotNull
    private Integer noise84;

    @ExcelProperty(value = "第85个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 89)
    @ColumnWidth(10)
    @NotNull
    private Integer noise85;

    @ExcelProperty(value = "第86个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 90)
    @ColumnWidth(10)
    @NotNull
    private Integer noise86;

    @ExcelProperty(value = "第87个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 91)
    @ColumnWidth(10)
    @NotNull
    private Integer noise87;

    @ExcelProperty(value = "第88个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 92)
    @ColumnWidth(10)
    @NotNull
    private Integer noise88;

    @ExcelProperty(value = "第89个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 93)
    @ColumnWidth(10)
    @NotNull
    private Integer noise89;

    @ExcelProperty(value = "第90个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 94)
    @ColumnWidth(10)
    @NotNull
    private Integer noise90;

    @ExcelProperty(value = "第91个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 95)
    @ColumnWidth(10)
    @NotNull
    private Integer noise91;

    @ExcelProperty(value = "第92个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 96)
    @ColumnWidth(10)
    @NotNull
    private Integer noise92;

    @ExcelProperty(value = "第93个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 97)
    @ColumnWidth(10)
    @NotNull
    private Integer noise93;

    @ExcelProperty(value = "第94个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 98)
    @ColumnWidth(10)
    @NotNull
    private Integer noise94;

    @ExcelProperty(value = "第95个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 99)
    @ColumnWidth(10)
    @NotNull
    private Integer noise95;

    @ExcelProperty(value = "第96个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 100)
    @ColumnWidth(10)
    @NotNull
    private Integer noise96;

    @ExcelProperty(value = "第97个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 101)
    @ColumnWidth(10)
    @NotNull
    private Integer noise97;

    @ExcelProperty(value = "第98个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 102)
    @ColumnWidth(10)
    @NotNull
    private Integer noise98;

    @ExcelProperty(value = "第99个PRB上检测到的干扰噪声的平均值 (毫瓦分贝)", index = 103)
    @ColumnWidth(10)
    @NotNull
    private Integer noise99;

}
