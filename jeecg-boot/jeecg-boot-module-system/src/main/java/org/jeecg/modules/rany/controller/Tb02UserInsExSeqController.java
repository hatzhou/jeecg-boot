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
import org.jeecg.modules.rany.entity.Tb02UserInsExSeq;
import org.jeecg.modules.rany.service.ITb02UserInsExSeqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: tb02_user_ins_ex_seq
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Api(tags="tb02_user_ins_ex_seq")
@RestController
@RequestMapping("/rany/tb02UserInsExSeq")
@Slf4j
public class Tb02UserInsExSeqController extends JeecgController<Tb02UserInsExSeq, ITb02UserInsExSeqService> {
	@Autowired
	private ITb02UserInsExSeqService tb02UserInsExSeqService;
	
	/**
	 * 分页列表查询
	 *
	 * @param tb02UserInsExSeq
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "tb02_user_ins_ex_seq-分页列表查询")
	@ApiOperation(value="tb02_user_ins_ex_seq-分页列表查询", notes="tb02_user_ins_ex_seq-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(Tb02UserInsExSeq tb02UserInsExSeq,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<Tb02UserInsExSeq> queryWrapper = QueryGenerator.initQueryWrapper(tb02UserInsExSeq, req.getParameterMap());
		Page<Tb02UserInsExSeq> page = new Page<Tb02UserInsExSeq>(pageNo, pageSize);
		IPage<Tb02UserInsExSeq> pageList = tb02UserInsExSeqService.page(page, queryWrapper);
		return Result.OK(pageList);
	}
	
	/**
	 * 添加
	 * @param tb02UserInsExSeq
	 * @return
	 */
	@AutoLog(value = "tb02_user_ins_ex_seq-添加")
	@ApiOperation(value="tb02_user_ins_ex_seq-添加", notes="tb02_user_ins_ex_seq-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody Tb02UserInsExSeq tb02UserInsExSeq) {
		tb02UserInsExSeqService.save(tb02UserInsExSeq);
		return Result.OK("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param tb02UserInsExSeq
	 * @return
	 */
	@AutoLog(value = "tb02_user_ins_ex_seq-编辑")
	@ApiOperation(value="tb02_user_ins_ex_seq-编辑", notes="tb02_user_ins_ex_seq-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody Tb02UserInsExSeq tb02UserInsExSeq) {
		tb02UserInsExSeqService.updateById(tb02UserInsExSeq);
		return Result.OK("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "tb02_user_ins_ex_seq-通过id删除")
	@ApiOperation(value="tb02_user_ins_ex_seq-通过id删除", notes="tb02_user_ins_ex_seq-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		tb02UserInsExSeqService.removeById(id);
		return Result.OK("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "tb02_user_ins_ex_seq-批量删除")
	@ApiOperation(value="tb02_user_ins_ex_seq-批量删除", notes="tb02_user_ins_ex_seq-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.tb02UserInsExSeqService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.OK("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "tb02_user_ins_ex_seq-通过id查询")
	@ApiOperation(value="tb02_user_ins_ex_seq-通过id查询", notes="tb02_user_ins_ex_seq-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		Tb02UserInsExSeq tb02UserInsExSeq = tb02UserInsExSeqService.getById(id);
		if(tb02UserInsExSeq==null) {
			return Result.error("未找到对应数据");
		}
		return Result.OK(tb02UserInsExSeq);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param tb02UserInsExSeq
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, Tb02UserInsExSeq tb02UserInsExSeq) {
        return super.exportXls(request, tb02UserInsExSeq, Tb02UserInsExSeq.class, "tb02_user_ins_ex_seq");
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
        return super.importExcel(request, response, Tb02UserInsExSeq.class);
    }

	 /**
	  *根据token查询当前用户需要执行的指令
	  * @param token
	  * @return
	  */
	 @AutoLog(value = "通过token查询执行指令")
	 @ApiOperation(value="通过token查询执行指令", notes="通过token查询执行指令")
	 @PostMapping(value = "/queryInsByToken")
	 public Result<?> queryInsByToken(@RequestParam(name="token",required=true) String token) {
		 Result  result = new Result();
		 List Tb02UserInsExSeqList = tb02UserInsExSeqService.queryInsByToken(token);
		 //每次只获取一个指令，按照指令序列执行
		 if (Tb02UserInsExSeqList.size() > 0){
			 result.setResult(Tb02UserInsExSeqList.get(0));
		 }
		 return result;
	 }
}
