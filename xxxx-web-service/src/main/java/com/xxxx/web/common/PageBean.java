package com.xxxx.web.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * 分页参数bean
 *
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageBean<T> {

    @NotNull(message = "页码必须要传")
    @Min(value = 1, message = "最小值为1")
    private long current;

    @NotNull(message = "每页数据量必须要传")
    @Min(value = 1, message = "最小值为1")
    private long pageSize;

    private long total;


    private List<T> records;
}
