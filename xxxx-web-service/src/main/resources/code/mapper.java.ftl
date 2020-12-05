package ${cfg.basePackage}.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import ${cfg.basePackage}.entity.${entity}Entity;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface ${entity}Mapper extends BaseMapper<${entity}Entity> {

}
