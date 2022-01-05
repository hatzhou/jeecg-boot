package org.jeecg.modules.rany.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.rany.entity.Tb02Instructions;
import org.jeecg.modules.rany.service.ITb02InstructionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

 /**
 * @Description: tb02_instructions
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Api(tags="tb02_instructions")
@RestController
@RequestMapping("/rany/tb02Instructions")
@Slf4j
public class Tb02InstructionsController extends JeecgController<Tb02Instructions, ITb02InstructionsService> {
	@Autowired
	private ITb02InstructionsService tb02InstructionsService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tb02Instructions
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "tb02_instructions-分页列表查询")
	@ApiOperation(value="tb02_instructions-分页列表查询", notes="tb02_instructions-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tb02Instructions tb02Instructions,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tb02Instructions> queryWrapper = QueryGenerator.initQueryWrapper(tb02Instructions, req.getParameterMap());
		Page<Tb02Instructions> page = new Page<Tb02Instructions>(pageNo, pageSize);
		IPage<Tb02Instructions> pageList = tb02InstructionsService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tb02Instructions
	 * @return
	 */
	@AutoLog(value = "tb02_instructions-添加")
	@ApiOperation(value="tb02_instructions-添加", notes="tb02_instructions-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tb02Instructions tb02Instructions) {
		tb02InstructionsService.save(tb02Instructions);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tb02Instructions
	 * @return
	 */
	@AutoLog(value = "tb02_instructions-编辑")
	@ApiOperation(value="tb02_instructions-编辑", notes="tb02_instructions-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tb02Instructions tb02Instructions) {
		tb02InstructionsService.updateById(tb02Instructions);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "tb02_instructions-通过id删除")
	@ApiOperation(value="tb02_instructions-通过id删除", notes="tb02_instructions-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tb02InstructionsService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "tb02_instructions-批量删除")
	@ApiOperation(value="tb02_instructions-批量删除", notes="tb02_instructions-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tb02InstructionsService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "tb02_instructions-通过id查询")
	@ApiOperation(value="tb02_instructions-通过id查询", notes="tb02_instructions-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tb02Instructions tb02Instructions = tb02InstructionsService.getById(id);
		if(tb02Instructions==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tb02Instructions);
	}


    /**
    * 导出excel
    *
    * @param request
    * @param tb02Instructions
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Tb02Instructions tb02Instructions) {
        return super.exportXls(request, tb02Instructions, Tb02Instructions.class, "tb02_instructions");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, Tb02Instructions.class);
    }

}
