package team.lte.commonutils.easyexcel.injector;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.keygen.NoKeyGenerator;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

@Slf4j
public class InsertBatchMethod extends AbstractMethod {

    /**
     * sql注入器代替手动修改xml。
     * <p>
     * insert into user(id, name, age) values (1, "a", 17), (2, "b", 18);
     * 
     * <pre>
     * insert into user(id, name, age) values
     * &lt;foreach collection="list"
     *          item="item"
     *          index="index"
     *          open="("
     *          separator="),("
     *          close=")">
     *     #{item.id}, #{item.name}, #{item.age}
     * &lt;/foreach>
     * </pre>
     */
    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql = "<script>insert into %s %s values %s</script>";
        String fieldSql = prepareFieldSql(tableInfo);
        String valuesSql = prepareValuesSql(tableInfo);
        String sqlResult = String.format(sql, tableInfo.getTableName(), fieldSql, valuesSql);
        log.debug("sqlResult----->{}", sqlResult);
        SqlSource sqlSource = languageDriver.createSqlSource(configuration, sqlResult, modelClass);
        return this.addInsertMappedStatement(mapperClass, modelClass, "insertBatch", sqlSource, new NoKeyGenerator(),
            null, null);
    }

    private String prepareFieldSql(TableInfo tableInfo) {
        StringBuilder fieldSql = new StringBuilder();
        fieldSql.append("(");
        fieldSql.append(tableInfo.getKeyColumn()).append(",");
        tableInfo.getFieldList().forEach(x -> fieldSql.append(x.getColumn()).append(","));
        fieldSql.delete(fieldSql.length() - 1, fieldSql.length());
        fieldSql.append(")");
        return fieldSql.toString();
    }

    private String prepareValuesSql(TableInfo tableInfo) {
        StringBuilder valuesSql = new StringBuilder();
        valuesSql.append(
            "<foreach collection=\"list\" item=\"item\" index=\"index\" open=\"(\" separator=\"),(\" close=\")\">");
        valuesSql.append("#{item.").append(tableInfo.getKeyProperty()).append("},");
        tableInfo.getFieldList().forEach(x -> valuesSql.append("#{item.").append(x.getProperty()).append("},"));
        valuesSql.delete(valuesSql.length() - 1, valuesSql.length());
        valuesSql.append("</foreach>");
        return valuesSql.toString();
    }
}
