<template>
  <ul>
    <div id="groupBlock">
      <!--里面存放每一个设备组的豆腐块-->
      <div v-for="item in group">
        <span style="height: 70%">
          <span>{{item.groupname}}</span>
          <span>{{item.groupadd}}</span>
          <span>设备：0/0/{{item.maxdevice}}</span>
        </span>
        <span>
          <a-button block size="small" style="font-size: 8px">
            实时截图
          </a-button>
          <a-button block size="small" style="font-size: 8px">
            执行指令
          </a-button>
        </span>
      </div>
    </div>
  </ul>

</template>

<script>
  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import {getAction} from "@api/manage";

  export default {
    name: "Tb01MachineGroupConsole",
    mixins:[JeecgListMixin, mixinDevice],
    data() {
      return {
        group:"",
        url: "/rany/tb01MachineGroup/console"
      }
    },
    methods:{
      loadData() {
        getAction(this.url).then((res)=>{
          this.group = res.result.group;
        })
      }
    },
    created() {
      this.loadData();
    }
  }
</script>

<style scoped>
  #groupBlock{

  }
  #groupBlock div{
    font-size: 12px;
    border:1px solid #c0ccda;
    background: white;
    margin-left: 5px;
    margin-right: 5px;
    width:120px;
    height: 180px;
    float:left;
    margin-bottom: 10px;
  }
  #groupBlock div span{
    width:100%;
    padding: 0 5px 0 5px;
    float: left;
  }
</style>