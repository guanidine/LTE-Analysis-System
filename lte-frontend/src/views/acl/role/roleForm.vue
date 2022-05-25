<template>
  <div style="margin: 20px 20px">
    <el-input v-model="filterText" placeholder="输入关键字进行过滤"/>
    <div style="margin: 15px 0;"/>
    <el-tree
      ref="tree"
      class="filter-tree"
      :data="data"
      show-checkbox
      default-expand-all
      node-key="id"
      highlight-current
      :props="defaultProps"
      :filter-node-method="filterNode"
    />
    <div style="margin: 15px 0;"/>
    <el-button :disabled="saveBtnDisabled" type="primary" @click="save">保存</el-button>
  </div>
</template>

<script>
import permissionApi from '@/api/acl/permission'
import Vue from 'vue'

export default {
  data() {
    return {
      filterText: '',
      saveBtnDisabled: true,
      data: [],
      defaultProps: {
        children: 'children',
        label: 'name'
      },
      roleId: ''

    }
  },
  watch: {
    $route(to, from) {
      this.init()
    },
    filterText(val) {
      this.$refs.tree.filter(val)
    }
  },

  created() {
    this.init()
  },

  methods: {
    filterNode(value, data) {
      if (!value) return true
      return data.name.indexOf(value) !== -1
    },

    init() {
      if (this.$route.params && this.$route.params.id) {
        this.roleId = this.$route.params.id
        this.fetchDataById(this.roleId)
        this.saveBtnDisabled = !Vue.prototype.hasPerm('role.acl')
      }
    },

    fetchDataById(roleId) {
      permissionApi.listPermissionsById(roleId).then(response => {
        this.data = response.data.list
        const jsonList = JSON.parse(JSON.stringify(this.data))
        const list = []
        this.getJsonToList(list, jsonList[0]['children'])
        this.setCheckedKeys(list)
      })
    },

    // 把json数据转成string再转成对象，根据Key获取value数据
    getJsonToList(list, jsonList) {
      for (let i = 0; i < jsonList.length; i++) {
        if (jsonList[i]['select'] === true && jsonList[i]['level'] === 4) {
          list.push(jsonList[i]['id'])
        }
        if (jsonList[i]['children'] != null) {
          this.getJsonToList(list, jsonList[i]['children'])
        }
      }
    },

    setCheckedKeys(list) {
      this.$refs.tree.setCheckedKeys(list)
    },

    save() {
      this.saveBtnDisabled = true
      // 权限树中，半选的父权限也应包含在角色权限内，否则会出现整个路由无权访问的情况
      const ids = this.$refs.tree.getCheckedKeys().concat(this.$refs.tree.getHalfCheckedKeys())
      permissionApi.assignPermissionsById(this.roleId, ids).then(response => {
        if (response.success) {
          this.$message({
            type: 'success',
            message: '保存成功'
          })
          this.$router.push({ path: '/acl/role/list' })
        }
      }).catch(error => {
        this.saveBtnDisabled = !Vue.prototype.hasPerm('role.acl')
        console.log(error)
      })
    }
  }
}
</script>
