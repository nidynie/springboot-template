package ${package.Service};

import com.baomidou.mybatisplus.extension.service.IService;
import PageBean;
import com.meiliyaya.web.dto.${entity}AddDto;
import com.meiliyaya.web.dto.${entity}PageDto;
import com.meiliyaya.web.dto.${entity}UpdateDto;
import com.meiliyaya.web.entity.${entity}Entity;
import com.meiliyaya.web.vo.${entity}Vo;
import java.util.List;
import java.util.Map;

public interface ${entity}Service extends IService${'<'}${entity}Entity${'>'} {

    /**
     * 创建${table.comment}
     *
     * @param addDto
     */
    void add(${entity}AddDto addDto);


    /**
     * 更新${table.comment}
     *
     * @param updateDto
     */
    void update${entity}(${entity}UpdateDto updateDto);


    /**
     * 删除${table.comment}
     *
     * @param id
     */
    void delete(String id);


    /**
     * ${table.comment}分页
     *
     * @param pageDto
     * @return
     */
    PageBean${'<'}${entity}Vo${'>'} getPages(${entity}PageDto pageDto);


    /**
     * 通过id列表获取对应Map集合
     *
     * @return
     */
    Map${'<'}String, ${entity}Vo${'>'} getMapByIds(List${'<'}String${'>'} ids);


}
