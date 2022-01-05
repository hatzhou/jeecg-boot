package org.jeecg.modules.rany.entity;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecg.common.aspect.annotation.Dict;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @Description: tb01_machine_group_map
 * @Author: jeecg-boot
 * @Date:   2021-12-29
 * @Version: V1.0
 */
@Data
@TableName("tb01_machine_group_map")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="tb01_machine_group_map对象", description="tb01_machine_group_map")
public class Tb01MachineGroupMap implements Serializable {
    private static final long serialVersionUID = 1L;

	/**组id    对应组表主键*/
	@Excel(name = "组id    对应组表主键", width = 15)
    @ApiModelProperty(value = "组id    对应组表主键")
    private String gid;
	/**设备id 对应设备表主键*/
	@Excel(name = "设备id 对应设备表主键", width = 15)
    @ApiModelProperty(value = "设备id 对应设备表主键")
    private String mid;
	/**创建时间*/
    @ApiModelProperty(value = "创建时间")
    private String createTime;
	/**创建人*/
	@Excel(name = "创建人", width = 15)
    @ApiModelProperty(value = "创建人")
    private String creator;
}
