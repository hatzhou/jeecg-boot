package org.jeecg.modules.rany.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.rany.entity.Tb01MachineGroupMap;

import java.util.List;

/**
 * @Description: tb01_machine_group_map
 * @Author: jeecg-boot
 * @Date:   2021-12-29
 * @Version: V1.0
 */
public interface ITb01MachineGroupMapService extends IService<Tb01MachineGroupMap> {

    /**
     * 根据组编号，获取组内所有的机器编号
     * @param groupId 组编号
     * @return
     */
    public List<String> getMahineIdsByGroupId(String groupId);
}
