<template>
  <div v-loading="loading" class="login">
    <mu-container>
      <mu-button slot="left" icon @click="$router.push('/home')">
        <mu-icon value="arrow_back"/>
      </mu-button>
      <mu-flex class="title" justify-content="center">
        <h1>COPD System</h1>
      </mu-flex>
      <mu-flex class="login-input-box" justify-content="center">
        <mu-form ref="loginForm" :model="loginForm" class="login-form">
          <mu-form-item :rules="loginRules.username" label="Username" label-float icon="account_circle" prop="username">
            <mu-text-field v-model="loginForm.username"/>
          </mu-form-item>
          <mu-form-item :rules="loginRules.password" label="Password" label-float icon="locked" prop="password">
            <mu-text-field
              v-model="loginForm.password"
              :action-icon="visibility ? 'visibility_off' : 'visibility'"
              :action-click="() => (visibility = !visibility)"
              :type="visibility ? 'text' : 'password'"/>
          </mu-form-item>
        </mu-form>
      </mu-flex>
      <mu-flex class="login-button-wrapper" justify-content="center">
        <mu-button round large full-width color="primary" @click="handleLogin">Sign in</mu-button>
      </mu-flex>
      <mu-flex class="login-button-wrapper" justify-content="center">
        <mu-button round large full-width color="secondary" @click="handleRegistration">Sign up</mu-button>
      </mu-flex>
    </mu-container>
  </div>

</template>

<script>
import { loginRules } from '@/utils/validate'

export default {
  name: 'Login',
  data() {
    return {
      loginForm: {
        username: 'user',
        password: 'user'
      },
      loginRules: loginRules,
      visibility: false,
      loading: false,
      redirect: undefined
    }
  },
  watch: {
    $route: {
      handler: function(route) {
        this.redirect = route.query && route.query.redirect
      },
      immediate: true
    }
  },
  methods: {
    handleLogin() {
      this.$refs.loginForm.validate().then((result) => {
        if (result) {
          this.loading = true
          this.$store.dispatch('Login', this.loginForm).then(() => {
            this.loading = false
            this.$router.push({ path: this.redirect || '/' })
          }).catch(() => {
            this.loading = false
          })
        } else {
          return false
        }
      })
    },
    handleRegistration() {
      console.log('register')
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  $bg: #ffd5c2;
  .login {
    position: fixed;
    height: 100%;
    width: 100%;
    background-repeat: no-repeat;
    background-size: 100% 100%;
    -moz-background-size: 100% 100%;
    background-color: $bg;

    .title {
      width: 100%;
      height: 100%;
      margin-top: 3rem;
    }

    .input-box {
      width: 100%;
      height: 100%;
      margin-top: 3rem;
      text-align: center;
    }

    &-button-wrapper {
      margin-bottom: 1.3rem;
    }

  }

</style>
