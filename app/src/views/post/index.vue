<template>
  <div v-loading="loading" class="post">
    <mu-appbar color="lightBlue400" class="post-appbar">
      <mu-button slot="left" icon @click="$router.go(-1)">
        <mu-icon value="arrow_back"/>
      </mu-button>
      {{ post.title }}
      <mu-button slot="right" icon>
        <mu-icon value="share"/>
      </mu-button>
    </mu-appbar>
    <mu-container v-model="post" class="post-info">
      <mu-card-title :title="post.title" :sub-title="post.createDate"/>
      <mu-card class="post-info-user">
        <mu-ripple>
          <mu-card-header title="User" sub-title="Info">
            <mu-avatar slot="avatar">
              <img :src="defaultAvatar">
            </mu-avatar>
          </mu-card-header>
        </mu-ripple>
      </mu-card>
      <mu-card class="post-info-content">
        <mu-ripple>
          <mu-card-text>
            {{ post.content }}
          </mu-card-text>
        </mu-ripple>
        <mu-card-actions>
          <mu-flex align-items="center">
            <mu-flex justify-content="center" fill>
              <mu-button>
                <mu-icon left value="reply"/>
              </mu-button>
            </mu-flex>
            <mu-flex justify-content="center" fill>
              <mu-button>
                <mu-icon left value="favorite" color="orange"/>
              </mu-button>
            </mu-flex>
            <mu-flex justify-content="center" fill>
              <mu-button>
                <mu-icon left value="thumb_up" color="red"/>
              </mu-button>
            </mu-flex>
          </mu-flex>
        </mu-card-actions>
      </mu-card>
    </mu-container>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import { post, replyList, avatar } from '@/utils/demoData'

export default {
  name: 'Post',
  data() {
    return {
      loading: true,
      defaultAvatar: avatar,
      post: post,
      replyList: replyList
    }
  },
  computed: {
    ...mapGetters([
      'account'
    ])
  },
  mounted() {
    this.loading = false
    const postId = this.$router.currentRoute.params.id
    if (!postId) {
      this.$router.replace('/error/404')
    }
  }
}
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.post {

  &-appbar {
    position: fixed;
    width: 100%;
    top: 0;
    margin: auto;
    z-index: 999;
  }

  &-info{
    padding-top: 5rem;
    margin-bottom: 1.3rem;

    &-user {
      margin-bottom: 0.6rem;
    }

    &-content{
      margin-bottom: 1.3rem;
    }
  }

  &-reply {
    &-card {
      margin-bottom: 0.6rem;
    }
  }
}
</style>
