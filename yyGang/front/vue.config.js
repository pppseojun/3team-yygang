// const { defineConfig } = require('@vue/cli-service')
// module.exports = defineConfig({
//   transpileDependencies: true,
//   lintOnSave: false
// })

module.exports = {
  transpileDependencies: true,
  lintOnSave: false,

  devServer:{
    port: 3030,
    proxy :{
      '/api' : {
        target: 'http://localhost:3000',    // 서버 프로젝트 포트와 동일 할 것
        changeOrigin: true,
      }
    }
  },
  outputDir:"../src/main/resources/static"
}

