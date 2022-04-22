package team.lte.businessquery.util;

import com.google.common.collect.ImmutableMap;
import io.swagger.v3.oas.annotations.media.Schema;
import team.lte.businessquery.entity.vo.TimeObject;
import team.lte.commonutils.easyexcel.annotation.DbType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 查询工具类。
 *
 * @author lte
 */
public class QueryUtils {

    /**
     * 查询属性名转数据表字段名。反射实体类变量上的 {@code @Schema} 注解，注解 {@code description} 匹配的即为待查变量。
     *
     * @param field 查询属性名
     * @param clazz 实体类class
     * @param <T> 查询实体类
     * @return 符合蛇形命名法的数据表字段名
     * @throws NoSuchFieldException 反射出错，实体类内可能无 {@code description} 为 {@code field} 的成员变量
     */
    public static <T> String getFieldName(String field, Class<T> clazz) throws NoSuchFieldException {
        Field[] fields = clazz.getDeclaredFields();
        for (Field item : fields) {
            Field declaredField = clazz.getDeclaredField(item.getName());
            declaredField.setAccessible(true);
            Schema annotation = declaredField.getAnnotation(Schema.class);
            if (annotation != null && Objects.equals(annotation.description(), field)) {
                field = item.getName();
                break;
            }
        }

        return toSnake(field);
    }

    /**
     * 提取“时间-数值”类型的数据列表。
     *
     * @param data 数据
     * @return 时间、数值分开存放的，符合接口返回值规范的 Map
     */
    public static Map<String, Object> extractData(List<TimeObject> data) {
        List<Object> list = new ArrayList<>();
        List<String> date = new ArrayList<>();
        for (TimeObject datum : data) {
            date.add(datum.getStartTime());
            list.add(datum.getResult());
        }

        return ImmutableMap.<String, Object>builder().put("list", list).put("date", date).build();
    }

    /**
     * 获取当前环境下使用的数据库方言。
     * 
     * @param env 环境profile
     * @return 数据库方言
     * @see DbType
     */
    public static DbType getDbType(String env) {
        return switch (env) {
            case "demo" -> DbType.GAUSS;
            case "dev", "prod" -> DbType.POSTGRE_SQL;
            default -> DbType.OTHER;
        };
    }

    private static String toSnake(String key) {
        final String delimiter = "_";
        if (key.contains(delimiter)) {
            return key;
        }
        StringBuilder sb = new StringBuilder();
        char[] chars = key.toCharArray();
        for (char c : chars) {
            if (Character.isUpperCase(c)) {
                sb.append(delimiter).append(c);
            } else {
                sb.append(c);
            }
        }
        return sb.toString().toLowerCase();
    }

}
