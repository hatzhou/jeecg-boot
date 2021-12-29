package org.jeecg.modules.rany.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.rany.entity.Tb01MachineGroup;
import org.jeecg.modules.rany.service.ITb01MachineGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description: tb01_machine_group
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Api(tags="tb01_machine_group")
@RestController
@RequestMapping("/rany/tb01MachineGroup")
@Slf4j
public class Tb01MachineGroupController extends JeecgController<Tb01MachineGroup, ITb01MachineGroupService> {
	@Autowired
	private ITb01MachineGroupService tb01MachineGroupService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tb01MachineGroup
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group-分页列表查询")
	@ApiOperation(value="tb01_machine_group-分页列表查询", notes="tb01_machine_group-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tb01MachineGroup tb01MachineGroup,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tb01MachineGroup> queryWrapper = QueryGenerator.initQueryWrapper(tb01MachineGroup, req.getParameterMap());
		Page<Tb01MachineGroup> page = new Page<Tb01MachineGroup>(pageNo, pageSize);
		IPage<Tb01MachineGroup> pageList = tb01MachineGroupService.queryGroupByPage(pageSize, pageNo);
		//IPage<Tb01MachineGroup> pageList = tb01MachineGroupService.getListByPage(page);
		return Result.OK(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param tb01MachineGroup
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group-添加")
	@ApiOperation(value="tb01_machine_group-添加", notes="tb01_machine_group-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tb01MachineGroup tb01MachineGroup) {
		LoginUser sysUser = (LoginUser) SecurityUtils.getSubject().getPrincipal();
		tb01MachineGroup.setCreator(sysUser.getUsername());
		tb01MachineGroupService.save(tb01MachineGroup);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tb01MachineGroup
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group-编辑")
	@ApiOperation(value="tb01_machine_group-编辑", notes="tb01_machine_group-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tb01MachineGroup tb01MachineGroup) {
		tb01MachineGroupService.updateById(tb01MachineGroup);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group-通过id删除")
	@ApiOperation(value="tb01_machine_group-通过id删除", notes="tb01_machine_group-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tb01MachineGroupService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group-批量删除")
	@ApiOperation(value="tb01_machine_group-批量删除", notes="tb01_machine_group-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tb01MachineGroupService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "tb01_machine_group-通过id查询")
	@ApiOperation(value="tb01_machine_group-通过id查询", notes="tb01_machine_group-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tb01MachineGroup tb01MachineGroup = tb01MachineGroupService.getById(id);
		if(tb01MachineGroup==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tb01MachineGroup);
	}


	@AutoLog(value = "tb01_machine_group-组控制台")
	@ApiOperation(value="tb01_machine_group-组控制台", notes="tb01_machine_group-组控制台")
	@GetMapping(value = "/console")
	public Result<?> console() {
		Result  result = new Result();
		//获取所有分组机器，以豆腐块的形式展示，并在此基础上做操作
		Map retmap = new HashMap();
		List<Tb01MachineGroup> tb01MachineGroupList = tb01MachineGroupService.list();
		retmap.put("group",tb01MachineGroupList);
		result.setResult(retmap);
		return result;
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tb01MachineGroup
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Tb01MachineGroup tb01MachineGroup) {
        return super.exportXls(request, tb01MachineGroup, Tb01MachineGroup.class, "tb01_machine_group");
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
        return super.importExcel(request, response, Tb01MachineGroup.class);
    }

}
