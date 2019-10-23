<template>
  <div class="app">
    <div class="content">
      <app-main/>
    </div>
    <mu-bottom-nav :value.sync="active" class="bottom-nav" shift>
      <span @click="homeClick">
        <mu-bottom-nav-item value="home" title="Home" icon="home"/>
      </span>
      <span @click="healthClick">
        <mu-bottom-nav-item value="health" title="Health" icon="favorite"/>
      </span>
      <span @click="dataClick">
        <mu-bottom-nav-item value="data" title="Data" icon="assessment"/>
      </span>
      <span @click="mineClick">
        <mu-bottom-nav-item value="mine" title="Mine" icon="tag_faces"/>
      </span>
    </mu-bottom-nav>
  </div>

</template>

<script>
import { AppMain } from './components'
import ResizeMixin from './mixin/ResizeHandler'

export default {
  name: 'Layout',
  components: {
    AppMain
  },
  mixins: [ResizeMixin],
  data() {
    return {
      active: 'home'
    }
  },
  mounted() {
    this.active = this.$route.name
  },
  methods: {
    homeClick() {
      this.$router.replace('/home')
    },
    healthClick() {
      this.$router.replace('/health/index')
    },
    dataClick() {
      this.$router.replace('/data/index')
    },
    mineClick() {
      this.$router.replace('/mine/index')
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
  .app{
    display:-webkit-box;
    -webkit-box-orient:vertical;
    height:100%;

    .content{
      position:relative;
      -webkit-box-flex:1;
      -webkit-flex:1;
      flex:1;
      overflow:auto;
      -webkit-overflow-scrolling:touch;
      margin-bottom: .1rem;
    }

    .bottom-nav{
      span {
        width: 100%;
      }
    }
  }
</style>
