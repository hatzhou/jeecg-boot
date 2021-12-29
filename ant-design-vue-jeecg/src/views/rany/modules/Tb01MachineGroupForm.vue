<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="组名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="groupname">
              <a-input v-model="model.groupname" placeholder="请输入组名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="组机器所在地址" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="groupadd">
              <a-input v-model="model.groupadd" placeholder="请输入组机器所在地址"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="是否允许相同ip" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="allowsameip">
              <a-input v-model="model.allowsameip" placeholder="请输入是否允许相同ip"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="组最大机器数" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="maxdevice">
              <a-input-number v-model="model.maxdevice" placeholder="请输入组最大机器数" style="width: 100%" />
            </a-form-model-item>
          </a-col>
          <!--<a-col :span="24">
            <a-form-model-item label="创建人" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="creator">
              <a-input v-model="model.creator" placeholder="请输入创建人"  ></a-input>
            </a-form-model-item>
          </a-col>-->
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'Tb01MachineGroupForm',
    components: {
    },
    props: {
      //表单禁用
      disabled: {
        type: Boolean,
        default: false,
        required: false
      }
    },
    data () {
      return {
        model:{
         },
        labelCol: {
          xs: { span: 24 },
          sm: { span: 5 },
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 },
        },
        confirmLoading: false,
        validatorRules: {
        },
        url: {
          add: "/rany/tb01MachineGroup/add",
          edit: "/rany/tb01MachineGroup/edit",
          queryById: "/rany/tb01MachineGroup/queryById"
        }
      }
    },
    computed: {
      formDisabled(){
        return this.disabled
      },
    },
    created () {
       //备份model原始值
      this.modelDefault = JSON.parse(JSON.stringify(this.model));
    },
    methods: {
      add () {
        this.edit(this.modelDefault);
      },
      edit (record) {
        this.model = Object.assign({}, record);
        this.visible = true;
      },
      submitForm () {
        const that = this;
        // 触发表单验证
        this.$refs.form.validate(valid => {
          if (valid) {
            that.confirmLoading = true;
            let httpurl = '';
            let method = '';
            if(!this.model.id){
              httpurl+=this.url.add;
              method = 'post';
            }else{
              httpurl+=this.url.edit;
               method = 'put';
            }
            httpAction(httpurl,this.model,method).then((res)=>{
              if(res.success){
                that.$message.success(res.message);
                that.$emit('ok');
              }else{
                that.$message.warning(res.message);
              }
            }).finally(() => {
              that.confirmLoading = false;
            })
          }
         
        })
      },
    }
  }
</script>