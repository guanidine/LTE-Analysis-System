package team.lte.businessquery.mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author lte
 * @since 2022-03-16
 */
public interface TableMapper {

    List<Map<String, String>> listTables();

    List<Map<String, String>> listTableColumns(String tableName);

}
