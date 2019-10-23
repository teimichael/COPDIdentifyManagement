<template>
  <div v-loading="loading" class="mine">
    <mu-container class="mine-info" @click="openEditProfileDialog">
      <mu-row justify-content="center">
        <mu-avatar :size="60">
          <img :src="defaultAvatar">
        </mu-avatar>
      </mu-row>
    </mu-container>
    <mu-container>
      <mu-paper :z-depth="3">
        <mu-list>
          <mu-list-item button>
            <mu-list-item-action>
              <mu-icon value="settings"/>
            </mu-list-item-action>
            <mu-list-item-content>
              <mu-list-item-title>Setting</mu-list-item-title>
            </mu-list-item-content>
          </mu-list-item>
          <mu-list-item button>
            <mu-list-item-action>
              <mu-icon value="help_outline"/>
            </mu-list-item-action>
            <mu-list-item-content>
              <mu-list-item-title>Help</mu-list-item-title>
            </mu-list-item-content>
          </mu-list-item>
          <mu-list-item button>
            <mu-list-item-action>
              <mu-icon value="today"/>
            </mu-list-item-action>
            <mu-list-item-content>
              <mu-list-item-title>Feedback</mu-list-item-title>
            </mu-list-item-content>
          </mu-list-item>
          <mu-list-item button>
            <mu-list-item-action>
              <mu-icon value="store"/>
            </mu-list-item-action>
            <mu-list-item-content>
              <mu-list-item-title>About</mu-list-item-title>
            </mu-list-item-content>
          </mu-list-item>
          <mu-divider/>
          <mu-list-item button @click="logout">
            <mu-list-item-action>
              <mu-icon value="power_settings_new"/>
            </mu-list-item-action>
            <mu-list-item-content>
              <mu-list-item-title>Logout</mu-list-item-title>
            </mu-list-item-content>
          </mu-list-item>
        </mu-list>
      </mu-paper>
    </mu-container>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { editProfileRules } from '@/utils/validate'
import Toast from 'muse-ui-toast'

import { avatar } from '@/utils/demoData'

export default {
  name: 'Mine',
  data() {
    return {
      loading: true,
      defaultAvatar: avatar,
      editProfileForm: {
        name: '',
        gender: '',
        birthday: '',
        phone: '',
        email: '',
        address: ''
      },
      editProfileRules: editProfileRules,
      showEditProfileDialog: false
    }
  },
  computed: {
    ...mapGetters([
      'account'
    ])
  },
  mounted() {
    this.loading = false
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw)
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 2

      if (!isJPG) {
        this.$message.error('上传头像图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 2MB!')
      }
      return isJPG && isLt2M
    },
    openEditProfileDialog() {
      this.showEditProfileDialog = true
    },
    closeEditProfileDialog() {
      this.showEditProfileDialog = false
    },
    handleEditProfile() {
      this.$refs.editProfileForm.validate().then((result) => {
        if (result) {
          console.log(this.editProfileForm)
          Toast.success({
            message: '修改成功',
            position: 'top'
          })
          this.showEditProfileDialog = false
          this.resetEditProfileForm()
        } else {
          return false
        }
      })
    },
    resetEditProfileForm() {
      this.editProfileForm = {
        name: '',
        birthday: '',
        phone: '',
        email: '',
        address: ''
      }
    },
    logout() {
      this.$store.dispatch('LogOut').then(() => {
        location.reload()
      })
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .mine {
    margin-top: 3rem;

    &-info {
      margin-bottom: 1.8rem;

      &-name {
        margin-top: 1rem;
        text-align: center;
        font-size: 1rem;
        font-weight: bold;
      }
    }

    &-button-paper {
      padding: 0.5rem;
      margin-bottom: 1.3rem;
    }

    &-button-wrapper {
      text-align: center;
      margin-bottom: 1rem;
    }

    &-edit-profile-form {
      margin: 1.3rem 2rem 1.3rem 2rem;
    }
  }
</style>
