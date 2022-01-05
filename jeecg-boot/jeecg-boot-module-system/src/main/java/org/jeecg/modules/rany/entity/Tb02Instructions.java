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
 * @Description: tb02_instructions
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
@Data
@TableName("tb02_instructions")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tb02_instructions对象", description="tb02_instructions")
public class Tb02Instructions implements Serializable {
    private static final long serialVersionUID = 1L;

	/**主键*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "主键")
    private String id;
	/**指令编码*/
	@Excel(name = "指令编码", width = 15)
    @ApiModelProperty(value = "指令编码")
    private String inskey;
	/**指令中文名称*/
	@Excel(name = "指令中文名称", width = 15)
    @ApiModelProperty(value = "指令中文名称")
    private String inscnname;
	/**指令文件名称*/
	@Excel(name = "指令文件名称", width = 15)
    @ApiModelProperty(value = "指令文件名称")
    private String insfilename;
	/**指令说明*/
	@Excel(name = "指令说明", width = 15)
    @ApiModelProperty(value = "指令说明")
    private String insdesc;
	/**指令状态*/
	@Excel(name = "指令状态", width = 15)
    @ApiModelProperty(value = "指令状态")
    private String insstatus;
	/**指令下载路径*/
	@Excel(name = "指令下载路径", width = 15)
    @ApiModelProperty(value = "指令下载路径")
    private String inspath;
	/**创建认*/
	@Excel(name = "创建认", width = 15)
    @ApiModelProperty(value = "创建认")
    private String creator;
	/**创建时间*/
	@Excel(name = "创建时间", width = 15)
    @ApiModelProperty(value = "创建时间")
    private String creatdt;
	/**最后修改认*/
	@Excel(name = "最后修改认", width = 15)
    @ApiModelProperty(value = "最后修改认")
    private String lastmodby;
	/**最后修改时间*/
	@Excel(name = "最后修改时间", width = 15)
    @ApiModelProperty(value = "最后修改时间")
    private String lastmoddt;
}
