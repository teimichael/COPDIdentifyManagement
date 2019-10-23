<template>
  <div v-loading="loading" class="check">
    <div class="check-container">
      <h1>Please run this app on a smartphone.</h1>
    </div>
  </div>
</template>

<script>

export default {
  name: 'Check',
  data() {
    return {
      loading: true
    }
  },
  mounted() {
    this.loading = false
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.check {
  &-container {
    text-align: center;
    margin-top: 5rem;
  }
}
</style>
