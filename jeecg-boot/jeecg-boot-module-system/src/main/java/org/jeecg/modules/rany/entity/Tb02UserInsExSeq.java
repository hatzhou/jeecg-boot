package org.jeecg.modules.rany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;

/**
 * @Description: tb02_user_ins_ex_seq
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Data
@TableName("tb02_user_ins_ex_seq")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tb02_user_ins_ex_seq对象", description="tb02_user_ins_ex_seq")
public class Tb02UserInsExSeq implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**关联用户名*/
	@Excel(name = "关联用户名", width = 15)
    @ApiModelProperty(value = "关联用户名")
    private String username;

	@Excel(name = "机器token", width = 256)
    @ApiModelProperty(value = "机器token")
    private String token;

	/**指令编号*/
	@Excel(name = "指令编号", width = 15)
    @ApiModelProperty(value = "指令编号")
    private String inskey;
	/**指令编号*/
	@Excel(name = "指令参数", width = 512)
    @ApiModelProperty(value = "指令参数")
    private String execparam;
	/**执行顺序*/
	@Excel(name = "执行顺序", width = 15)
    @ApiModelProperty(value = "执行顺序")
    private String execorder;
	/**执行状态(0未执行1已执行2执行失败)*/
	@Excel(name = "执行状态(0未执行1已执行2执行失败)", width = 15)
    @ApiModelProperty(value = "执行状态(0未执行1已执行2执行失败)")
    private String execstatus;
	/**执行时间*/
	@Excel(name = "执行时间", width = 15)
    @ApiModelProperty(value = "执行时间")
    private String execdt;
}
