<template>
  <div class="http404">
    <h1>{{ message }}</h1>
  </div>
</template>

<script>

export default {
  name: 'Page404',
  computed: {
    message() {
      return 'The page has gone.'
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>

</style>
