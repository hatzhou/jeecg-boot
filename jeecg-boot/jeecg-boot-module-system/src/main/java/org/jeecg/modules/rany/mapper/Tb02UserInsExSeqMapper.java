package org.jeecg.modules.rany.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.rany.entity.Tb02UserInsExSeq;

import java.util.List;

/**
 * @Description: tb02_user_ins_ex_seq
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
public interface Tb02UserInsExSeqMapper extends BaseMapper<Tb02UserInsExSeq> {

    /**
     * 根据token查询执行的脚本
     * @param token
     * @return
     */
    public List<Tb02UserInsExSeq> queryInsByToken(String token);
}
