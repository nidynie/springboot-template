package ${package.Service}.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import PageBean;
import ServiceException;
import com.meiliyaya.web.dto.${entity}AddDto;
import com.meiliyaya.web.dto.${entity}PageDto;
import com.meiliyaya.web.dto.${entity}UpdateDto;
import com.meiliyaya.web.entity.${entity}Entity;
import StatusEnum;
import com.meiliyaya.web.mapper.${entity}Mapper;
import com.meiliyaya.web.service.${entity}Service;
import IdUtil;
import ServletRequestUtil;
import com.meiliyaya.web.vo.${entity}Vo;
import org.apache.commons.collections4.CollectionUtils;

import org.springframework.beans.BeanUtils;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ${entity}ServiceImpl extends ServiceImpl${'<'}${entity}Mapper, ${entity}Entity${'>'} implements ${entity}Service {


    @Override
    public void add(${entity}AddDto addDto) {
/*
        ##        long count = this.count(new LambdaQueryWrapper${'<'}${entity}Entity${'>'}()
        ##                .eq(${entity}Entity::getName, addDto.getName()));
        ##        if (count ${'>'} 0) {
        ##            throw new ServiceException("100100", "当前类型名称已经存在");
        ##        }
*/
            ${entity}Entity entity = new ${entity}Entity();
        BeanUtils.copyProperties(addDto, entity);
        entity.setId(IdUtil.getId());
        entity.setCreateUser(ServletRequestUtil.getUserIdFromRequest());
        entity.setUpdateUser(entity.getCreateUser());
        entity.setCreateTime(new Date());
        entity.setUpdateTime(new Date());
        this.save(entity);
    }

    @Override
    public void update${entity}(${entity}UpdateDto updateDto) {

            ${entity}Entity targetEntity = this.getById(updateDto.getId());
        if (targetEntity == null) {
            throw new ServiceException("100100", "无法识别当前信息");
        }
        /*
        if (!updateDto.getName().equals(typesEntity.getName())) {

            long count = this.count(new LambdaQueryWrapper${'<'}${entity}Entity${'>'}()
                    .eq(${entity}Entity::getTitleBarId, updateDto.getTitleBarId()));
            if (count ${'>'} 0) {
                throw new ServiceException("100100", "当前类型名称已经存在");
            }


        }
        */
            ${entity}Entity updateEntity = new ${entity}Entity();
        BeanUtils.copyProperties(updateDto, updateEntity);
        updateEntity.setCreateUser(ServletRequestUtil.getUserIdFromRequest());
        updateEntity.setUpdateTime(new Date());
        this.updateById(updateEntity);
    }

    @Override
    public void delete(String id) {
        this.removeById(id);
    }

    @Override
    public PageBean${'<'}${entity}Vo${'>'} getPages(${entity}PageDto pageDto) {

        LambdaQueryWrapper${'<'}${entity}Entity${'>'} queryWrapper = new LambdaQueryWrapper${'<'}${'>'}();

        queryWrapper.orderByDesc(${entity}Entity::getCreateTime);
        IPage${'<'}${entity}Entity${'>'} page = this.page(new Page${'<'}${'>'}(pageDto.getCurrent(), pageDto.getPageSize()), queryWrapper);
        if (CollectionUtils.isEmpty(page.getRecords())) {
            return new PageBean${'<'}${'>'}(page.getCurrent(), page.getSize(), page.getTotal(), Collections.emptyList());
        }

        return new PageBean${'<'}${'>'}(page.getCurrent(), page.getSize(), page.getTotal(), getVosFromEntities(page.getRecords()));
    }

    @Override
    public Map${'<'}String, ${entity}Vo${'>'} getMapByIds(List${'<'}String${">"} ids) {
        Map${'<'}String, ${entity}Vo${'>'} resultVosMap = new HashMap${'<'}${'>'}();
        List${'<'}${entity}Entity${'>'} resultVos = this.baseMapper.selectBatchIds(ids);
        if (CollectionUtils.isEmpty(resultVos)) {
            return resultVosMap;
        }
        resultVos.stream().map(e->{
            ${entity}Vo vo = new ${entity}Vo();
            BeanUtils.copyProperties(e,vo);
            return vo;
        }).forEach(e -> resultVosMap.put(e.getId(), e));
        return resultVosMap;
    }

    private ${entity}Vo getVoFromEntity(${entity}Entity ${entity[0]?lower_case}${entity[1..]}Entity) {
        ${entity}Vo ${entity[0]?lower_case}${entity[1..]}Vo = new ${entity}Vo();
        BeanUtils.copyProperties(${entity[0]?lower_case}${entity[1..]}Entity, ${entity[0]?lower_case}${entity[1..]}Vo);
        if (Objects.nonNull(${entity[0]?lower_case}${entity[1..]}Vo.getStatus())) {
            ${entity[0]?lower_case}${entity[1..]}Vo.setStatusDesc(${entity[0]?lower_case}${entity[1..]}Vo.getStatus().desc);
        }
        return ${entity[0]?lower_case}${entity[1..]}Vo;
    }

    private List${'<'}${entity}Vo${">"} getVosFromEntities(List${'<'}${entity}Entity${">"} ${entity[0]?lower_case}${entity[1..]}Entities) {
        return ${entity[0]?lower_case}${entity[1..]}Entities.stream().map(e -> getVoFromEntity(e)).collect(Collectors.toList());

    }

}
