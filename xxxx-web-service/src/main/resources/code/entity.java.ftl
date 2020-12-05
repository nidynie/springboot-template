package ${package.Entity};

import lombok.Data;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
/**
* <p>
    * ${entity}对象
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Data
@TableName("${table.name}")
public class ${entity}Entity{

<#--## ----------  BEGIN 字段循环遍历  ------------>
<#list  table.fields as field>
    /**
    * ${field.comment!''}
    */
    @TableField("${field.name}")
    private ${field.propertyType} ${field.propertyName};
</#list>

}