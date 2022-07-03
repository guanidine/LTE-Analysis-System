package team.lte.bizservice.mapper;

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

    List<Map<String, String>> listTables(String schemaName);

    List<Map<String, String>> listTableColumns(String tableName);

    String listTableFile(String tableName);

    String listTableSize(String tableName);
}
