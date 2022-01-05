package org.jeecg.modules.rany.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.rany.entity.Tb01MachineGroupMap;

import java.util.List;

/**
 * @Description: tb01_machine_group_map
 * @Author: jeecg-boot
 * @Date:   2021-12-29
 * @Version: V1.0
 */
public interface Tb01MachineGroupMapMapper extends BaseMapper<Tb01MachineGroupMap> {

    public List<String> getMachineByGroup(String groupId);
}
