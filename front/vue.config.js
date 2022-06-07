const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: false,

  pluginOptions: {
    vuetify: {
			// https://github.com/vuetifyjs/vuetify-loader/tree/next/packages/vuetify-loader
		}
  },
    devServer: {
      // 프록시 설정
      proxy: {
          // 프록시 요청을 보낼 api의 시작 부분
          '/cluver': {
              // 프록시 요청을 보낼 서버의 주소
              target: 'http://localhost:9090',
              changeOrigin: true,
              logLevel: 'debug',
          }
      },
  },

  // 리소스의 위치. 아마존 올릴 때 쓰는 것
  publicPath: '/cluver/vue/'
})
