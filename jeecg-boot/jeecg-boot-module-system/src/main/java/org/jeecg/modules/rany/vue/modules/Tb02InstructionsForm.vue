<template>
  <a-spin :spinning="confirmLoading">
    <j-form-container :disabled="formDisabled">
      <a-form-model ref="form" :model="model" :rules="validatorRules" slot="detail">
        <a-row>
          <a-col :span="24">
            <a-form-model-item label="指令编码" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="inskey">
              <a-input v-model="model.inskey" placeholder="请输入指令编码"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="指令中文名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="inscnname">
              <a-input v-model="model.inscnname" placeholder="请输入指令中文名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="指令文件名称" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insfilename">
              <a-input v-model="model.insfilename" placeholder="请输入指令文件名称"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="指令说明" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insdesc">
              <a-input v-model="model.insdesc" placeholder="请输入指令说明"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="指令状态" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="insstatus">
              <a-input v-model="model.insstatus" placeholder="请输入指令状态"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="指令下载路径" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="inspath">
              <a-input v-model="model.inspath" placeholder="请输入指令下载路径"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="创建认" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="creator">
              <a-input v-model="model.creator" placeholder="请输入创建认"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="创建时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="creatdt">
              <a-input v-model="model.creatdt" placeholder="请输入创建时间"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="最后修改认" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="lastmodby">
              <a-input v-model="model.lastmodby" placeholder="请输入最后修改认"  ></a-input>
            </a-form-model-item>
          </a-col>
          <a-col :span="24">
            <a-form-model-item label="最后修改时间" :labelCol="labelCol" :wrapperCol="wrapperCol" prop="lastmoddt">
              <a-input v-model="model.lastmoddt" placeholder="请输入最后修改时间"  ></a-input>
            </a-form-model-item>
          </a-col>
        </a-row>
      </a-form-model>
    </j-form-container>
  </a-spin>
</template>

<script>

  import { httpAction, getAction } from '@/api/manage'
  import { validateDuplicateValue } from '@/utils/util'

  export default {
    name: 'Tb02InstructionsForm',
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
           inskey: [
              { required: true, message: '请输入指令编码!'},
           ],
        },
        url: {
          add: "/rany/tb02Instructions/add",
          edit: "/rany/tb02Instructions/edit",
          queryById: "/rany/tb02Instructions/queryById"
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