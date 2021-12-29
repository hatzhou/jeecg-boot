package org.jeecg.modules.rany.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.rany.entity.Tb02UserInsExSeq;

import java.util.List;

/**
 * @Description: tb02_user_ins_ex_seq
 * @Author: jeecg-boot
 * @Date:   2021-11-23
 * @Version: V1.0
 */
public interface ITb02UserInsExSeqService extends IService<Tb02UserInsExSeq> {

    public List<Tb02UserInsExSeq> queryInsByToken(String token);
}
