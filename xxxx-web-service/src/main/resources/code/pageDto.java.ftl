package com.meiliyaya.web.dto;

import lombok.Data;
import java.util.Date;

/**
 * <p>
 * ${entity}AddDto对象
 * </p>
 *
 * @author ${author}
 * @since ${date}
 */
@Data
public class ${entity}PageDto extends PageDto {

<#--    ## ----------  BEGIN 字段循环遍历  ------------>
<#list  table.fields as field>
    /**
    * ${field.comment!''}
    */
    private ${field.propertyType} ${field.propertyName};
</#list>

}