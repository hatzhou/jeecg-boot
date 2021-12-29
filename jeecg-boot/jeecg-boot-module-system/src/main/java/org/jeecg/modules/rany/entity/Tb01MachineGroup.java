package org.jeecg.modules.rany.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * @Description: tb01_machine_group
 * @Author: jeecg-boot
 * @Date:   2021-12-09
 * @Version: V1.0
 */
@Data
@TableName("tb01_machine_group")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tb01_machine_group对象", description="tb01_machine_group")
public class Tb01MachineGroup implements Serializable {
    private static final long serialVersionUID = 1L;

	/**编号*/
	@TableId(type = IdType.ASSIGN_ID)
    @ApiModelProperty(value = "编号")
    private String id;
	/**组名称*/
	@Excel(name = "组名称", width = 15)
    @ApiModelProperty(value = "组名称")
    private String groupname;
	/**组机器所在地址*/
	@Excel(name = "组机器所在地址", width = 15)
    @ApiModelProperty(value = "组机器所在地址")
    private String groupadd;
	/**是否允许相同ip*/
	@Excel(name = "是否允许相同ip", width = 15)
    @ApiModelProperty(value = "是否允许相同ip")
    private String allowsameip;
	/**组最大机器数*/
	@Excel(name = "组最大机器数", width = 15)
    @ApiModelProperty(value = "组最大机器数")
    private Integer maxdevice;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private String creator;
	/**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private String createTime;
	/**组内当前机器数*/
    @TableField(exist = false)
    @ApiModelProperty(value = "组内当前机器数")
    private String sumcurdev;
}
