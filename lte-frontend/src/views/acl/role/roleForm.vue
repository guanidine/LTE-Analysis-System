<template>
  <div style="margin: 20px 20px">
    <el-tree
      ref="tree"
      :data="data"
      show-checkbox
      default-expand-all
      node-key="id"
      highlight-current
      :props="defaultProps"
    />
    <el-button :disabled="saveBtnDisabled" type="primary" @click="save">保存</el-button>
  </div>
</template>

<script>
import permissionApi from '@/api/acl/permission'
import Vue from 'vue'

export default {
  data() {
    return {
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
    }
  },

  created() {
    this.init()
  },

  methods: {
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
      const ids = this.$refs.tree.getCheckedKeys()
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
