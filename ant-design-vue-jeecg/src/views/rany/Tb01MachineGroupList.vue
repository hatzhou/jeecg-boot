<template>
  <a-row :gutter="10">
    <a-col :md="leftColMd" >
      <a-card>
        <!-- 查询区域 -->

        <!-- 查询区域-END -->

        <!-- 操作按钮区域 -->
        <div class="table-operator">
          <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
          <a-button type="primary" icon="download" @click="handleExportXls('tb01_machine_group')">导出</a-button>
          <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
            <a-button type="primary" icon="import">导入</a-button>
          </a-upload>
          <!-- 高级查询区域 -->
          <j-super-query :fieldList="superFieldList" ref="superQueryModal" @handleSuperQuery="handleSuperQuery"></j-super-query>
          <a-dropdown v-if="selectedRowKeys.length > 0">
            <a-menu slot="overlay">
              <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
            </a-menu>
            <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
          </a-dropdown>
        </div>

        <!-- table区域-begin -->
        <div>
          <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
            <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
            <a style="margin-left: 24px" @click="onClearSelected">清空</a>
          </div>

          <a-table
            ref="table"
            size="middle"
            :scroll="{x:true}"
            bordered
            rowKey="id"
            :columns="columns"
            :dataSource="dataSource"
            :pagination="ipagination"
            :loading="loading"
            :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
            class="j-table-force-nowrap"
            @change="handleTableChange">

            <template slot="htmlSlot" slot-scope="text">
              <div v-html="text"></div>
            </template>
            <template slot="imgSlot" slot-scope="text">
              <span v-if="!text" style="font-size: 12px;font-style: italic;">无图片</span>
              <img v-else :src="getImgView(text)" height="25px" alt="" style="max-width:80px;font-size: 12px;font-style: italic;"/>
            </template>
            <template slot="fileSlot" slot-scope="text">
              <span v-if="!text" style="font-size: 12px;font-style: italic;">无文件</span>
              <a-button
                v-else
                :ghost="true"
                type="primary"
                icon="download"
                size="small"
                @click="downloadFile(text)">
                下载
              </a-button>
            </template>

            <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a @click="deviceOpen(record)">设备</a>
              </a-menu-item>
              <a-menu-item>
                <a @click="handleDetail(record)">详情</a>
              </a-menu-item>
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

          </a-table>
        </div>
        <tb01-machine-group-modal ref="modalForm" @ok="modalFormOk"></tb01-machine-group-modal>
      </a-card>
    </a-col>

    <a-col :md="rightColMd" v-if="this.rightcolval == 1">
      <a-card>
        <div style="text-align: right;">
          <a-icon type="close-circle" @click="hideUserList" />
        </div>
        <div>
          <!-- 操作按钮区域 -->
          <div class="table-operator">
            <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
            <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
            </a-upload>
            <!-- 高级查询区域 -->
            <a-dropdown v-if="selectedRowKeys1.length > 0">
              <a-menu slot="overlay">
                <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
              </a-menu>
              <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
            </a-dropdown>
          </div>
          <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
            <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys1.length }}</a>项
            <a style="margin-left: 24px" @click="onClearSelected">清空</a>
          </div>

          <a-table
            ref="table"
            size="middle"
            :scroll="{x:true}"
            bordered
            rowKey="id"
            :columns="columns1"
            :dataSource="dataSource1"
            :pagination="ipagination1"
            :loading="loading"
            :rowSelection="{selectedRowKeys: selectedRowKeys1, onChange: onSelectChange}"
            class="j-table-force-nowrap"
            @change="handleTableChange">




          </a-table>
        </div>

      </a-card>
    </a-col>
  </a-row>
</template>

<script>

  import '@/assets/less/TableExpand.less'
  import { mixinDevice } from '@/utils/mixin'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'
  import Tb01MachineGroupModal from './modules/Tb01MachineGroupModal'
  import {getAction} from "@api/manage";

  export default {
    name: 'Tb01MachineGroupList',
    mixins:[JeecgListMixin, mixinDevice],
    components: {
      Tb01MachineGroupModal
    },
    data () {
      return {
        description: 'tb01_machine_group',
        rightcolval:1,//右侧
        selectedRowKeys1: [],
        selectedRowKeys: [],
        dataSource1: [],
        selectionRows: [],
        selectionRows1: [],
        // 表头
        ipagination1: {
          current: 1,
          pageSize: 10,
          pageSizeOptions: ['10', '20', '30'],
          showTotal: (total, range) => {
            return range[0] + '-' + range[1] + ' 共' + total + '条'
          },
          showQuickJumper: true,
          showSizeChanger: true,
          total: 0
        },
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
          },
          {
            title:'组名称',
            align:"center",
            dataIndex: 'groupname'
          },
          {
            title:'组机器所在地址',
            align:"center",
            dataIndex: 'groupadd'
          },
          {
            title:'是否允许相同ip',
            align:"center",
            dataIndex: 'allowsameip'
          },
          {
            title:'当前机器数',
            align:"center",
            dataIndex: 'sumcurdev'
          },
          {
            title:'设备上限',
            align:"center",
            dataIndex: 'maxdevice'
          },
          {
            title:'创建人',
            align:"center",
            dataIndex: 'creator'
          },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            fixed:"right",
            width:147,
            scopedSlots: { customRender: 'action' }
          }
        ],
        columns1: [
          {
            title:'制造商',
            align:"center",
            dataIndex: 'mbrand'
          },
          {
            title:'MAC地址',
            align:"center",
            dataIndex: 'mmac'
          },
          {
            title:'当前状态',
            align:"center",
            dataIndex: 'mstatus_dictText'
          }
        ],
        url: {
          list: "/rany/tb01MachineGroup/list",
          list1: "/rany/tb01Machine/list",//机器信息列表
          delete: "/rany/tb01MachineGroup/delete",
          deleteBatch: "/rany/tb01MachineGroup/deleteBatch",
          exportXlsUrl: "/rany/tb01MachineGroup/exportXls",
          importExcelUrl: "rany/tb01MachineGroup/importExcel",

        },
        dictOptions:{},
        superFieldList:[],
      }
    },
    created() {
      this.getSuperFieldList();
      //this.loadData2();
    },
    computed: {
      importExcelUrl: function(){
        return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
      },
      //左右比例
      leftColMd() {
        return this.selectedRowKeys.length === 0 ? 24 : 14
      },
      rightColMd() {
        return this.selectedRowKeys.length === 0 ? 0 : 10
      }
    },
    methods: {
      initDictConfig(){
      },
      getSuperFieldList(){
        let fieldList=[];
        fieldList.push({type:'string',value:'groupname',text:'组名称',dictCode:''})
        fieldList.push({type:'string',value:'groupadd',text:'组机器所在地址',dictCode:''})
        fieldList.push({type:'string',value:'allowsameip',text:'是否允许相同ip',dictCode:''})
        fieldList.push({type:'int',value:'maxdevice',text:'组最大机器数',dictCode:''})
        fieldList.push({type:'string',value:'creator',text:'创建人',dictCode:''})
        this.superFieldList = fieldList
      },
      loadData2(params) {
        console.log(params);
        getAction(this.url.list1,params).then((res)=>{
          this.dataSource1 = res.result.records
          this.ipagination1.total = res.result.total
        })
      },
      /**
       * 打开设备维护面板
       */
      deviceOpen(record) {
        this.selectedRowKeys = [record.id]
        this.rightcolval = '1';//打开右侧面板
        //传参打开右侧面板
        var groupId = record.id;//设备组编号
        var params = {}
        params.groupId = groupId
        this.loadData2(params);
      },
      hideUserList(){
        //this.rightcolval = 0
        this.selectedRowKeys = []
      },
    }
  }
</script>
<style scoped>
</style>