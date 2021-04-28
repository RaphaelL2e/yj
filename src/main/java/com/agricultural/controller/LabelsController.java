package com.agricultural.controller;


import com.agricultural.pojo.Labels;
import com.agricultural.response.ServerResponse;
import com.agricultural.service.ILabelsService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.agricultural.controller.support.BaseController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lee
 * @since 2021-04-09
 */
@RestController
@RequestMapping("/labels")
public class LabelsController extends BaseController {
    private final ILabelsService labelsService;

    public LabelsController(ILabelsService labelsService) {
        this.labelsService = labelsService;
    }

    @ApiOperation("添加标签")
    @PostMapping("/add")
    public ServerResponse AddLabel(@RequestBody Labels labels){
        try {
            labelsService.save(labels);
        }catch (Exception e){
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess(labels);
    }

    @ApiOperation("删除标签ByID")
    @DeleteMapping("/delete")
    public ServerResponse DeleteLabel(@RequestBody Labels labels){
        try {
           labelsService.removeById(labels.getId());
        }catch (Exception e){
            return ServerResponse.createByErrorMessage(e.getMessage());
        }
        return ServerResponse.createBySuccess();
    }
}
