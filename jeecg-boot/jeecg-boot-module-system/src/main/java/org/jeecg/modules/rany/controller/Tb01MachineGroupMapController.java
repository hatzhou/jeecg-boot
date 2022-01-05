package org.jeecg.modules.rany.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.rany.entity.Tb01MachineGroupMap;
import org.jeecg.modules.rany.service.ITb01MachineGroupMapService;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;

import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.def.NormalExcelConstants;
import org.jeecgframework.poi.excel.entity.ExportParams;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.poi.excel.view.JeecgEntityExcelView;
import org.jeecg.common.system.base.controller.JeecgController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jeecg.common.aspect.annotation.AutoLog;

 /**
 * @Description: tb01_machine_group_map
 * @Author: jeecg-boot
 * @Date:   2021-12-29
 * @Version: V1.0
 */
@Api(tags="tb01_machine_group_map")
@RestController
@RequestMapping("/rany/tb01MachineGroupMap")
@Slf4j
public class Tb01MachineGroupMapController extends JeecgController<Tb01MachineGroupMap, ITb01MachineGroupMapService> {
	@Autowired
	private ITb01MachineGroupMapService tb01MachineGroupMapService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tb01MachineGroupMap
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group_map-分页列表查询")
	@ApiOperation(value="tb01_machine_group_map-分页列表查询", notes="tb01_machine_group_map-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tb01MachineGroupMap tb01MachineGroupMap,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tb01MachineGroupMap> queryWrapper = QueryGenerator.initQueryWrapper(tb01MachineGroupMap, req.getParameterMap());
		Page<Tb01MachineGroupMap> page = new Page<Tb01MachineGroupMap>(pageNo, pageSize);
		IPage<Tb01MachineGroupMap> pageList = tb01MachineGroupMapService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tb01MachineGroupMap
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group_map-添加")
	@ApiOperation(value="tb01_machine_group_map-添加", notes="tb01_machine_group_map-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tb01MachineGroupMap tb01MachineGroupMap) {
		tb01MachineGroupMapService.save(tb01MachineGroupMap);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tb01MachineGroupMap
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group_map-编辑")
	@ApiOperation(value="tb01_machine_group_map-编辑", notes="tb01_machine_group_map-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tb01MachineGroupMap tb01MachineGroupMap) {
		tb01MachineGroupMapService.updateById(tb01MachineGroupMap);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group_map-通过id删除")
	@ApiOperation(value="tb01_machine_group_map-通过id删除", notes="tb01_machine_group_map-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tb01MachineGroupMapService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group_map-批量删除")
	@ApiOperation(value="tb01_machine_group_map-批量删除", notes="tb01_machine_group_map-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tb01MachineGroupMapService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group_map-通过id查询")
	@ApiOperation(value="tb01_machine_group_map-通过id查询", notes="tb01_machine_group_map-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tb01MachineGroupMap tb01MachineGroupMap = tb01MachineGroupMapService.getById(id);
		if(tb01MachineGroupMap==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tb01MachineGroupMap);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tb01MachineGroupMap
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Tb01MachineGroupMap tb01MachineGroupMap) {
        return super.exportXls(request, tb01MachineGroupMap, Tb01MachineGroupMap.class, "tb01_machine_group_map");
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
        return super.importExcel(request, response, Tb01MachineGroupMap.class);
    }

}
