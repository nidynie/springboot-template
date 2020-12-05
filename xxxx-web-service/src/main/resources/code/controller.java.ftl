package ${package.Controller};


import ${cfg.basePackage}.annotation.RequiredPermission;
import ${cfg.basePackage}.common.PageBean;
import ${cfg.basePackage}.common.Result;
import ${cfg.basePackage}.dto.*;
import ${cfg.basePackage}.vo.*;
import ${cfg.basePackage}.service.${entity}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;


/**
* ${table.comment}处理器
*/
@Api(tags="${table.comment}处理器")
@RestController
@RequestMapping("/api/${entity[0]?lower_case}${entity[1..]}/")
public class ${entity}Controller {


    @Autowired
    private ${entity}Service ${entity[0]?lower_case}${entity[1..]}service;

    /**
     * 创建${table.comment}
     *
     * @param addDto
     * @return
     */
    @PostMapping("/add")
    public Result add(@RequestBody @Validated ${entity}AddDto addDto) {
        ${entity[0]?lower_case}${entity[1..]}service.add(addDto);
        return Result.success();
    }


    /**
     * 更新${table.comment}
     *
     * @param updateDto
     * @return
     */
    @PostMapping("/update")
    public Result update(@RequestBody @Validated ${entity}UpdateDto updateDto) {
        ${entity[0]?lower_case}${entity[1..]}service.update${entity}(updateDto);
        return Result.success();
    }

    /**
     * 删除${table.comment}
     *
     * @param id
     * @return
     */
    @GetMapping("/delete/{id}")
    public Result delete(@PathVariable String id) {

        ${entity[0]?lower_case}${entity[1..]}service.delete(id);

        return Result.success();
    }


    /**
     * 获取${table.comment}
     *
     * @param pageDto
     * @return
     */
    @PostMapping("/getPages")
    public Result${'<'}PageBean${'<'} ${entity}Vo${'>'}${'>'} getPages(@RequestBody @Validated ${entity}PageDto pageDto) {

        return Result.success(${entity[0]?lower_case}${entity[1..]}service.getPages(pageDto));
    }

}