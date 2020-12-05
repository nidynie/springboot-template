package com.meiliyaya.web.vo;

import lombok.Data;
import java.util.Date;


/**
* <p>
    * ${entity}VO对象
    * </p>
*
* @author ${author}
* @since ${date}
*/
@Data
public class ${entity}Vo {

<#--## ----------  BEGIN 字段循环遍历  ------------>
<#list  table.fields as field>
    /**
    * ${field.comment!''}
    */
    private ${field.propertyType} ${field.propertyName};

    <#if field.propertyName=="status">

     /**
      * 状态描述
      */
     private String statusDesc;
    </#if>



</#list>

}