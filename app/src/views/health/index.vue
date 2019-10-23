<template>
  <div v-loading="loading" class="health">
    <mu-appbar style="width: 100%;" title="Health" color="primary"/>
    <div class="dashboard-text">name:{{ account.name }}</div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  name: 'Health',
  data() {
    return {
      loading: true
    }
  },
  computed: {
    ...mapGetters([
      'account'
    ])
  },
  mounted() {
    this.loading = false
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.dashboard {
  &-container {
    margin: 30px;
  }
  &-text {
    font-size: 30px;
    line-height: 46px;
  }
}
</style>
