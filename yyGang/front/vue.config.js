module.exports = {
  transpileDependencies: true,
  lintOnSave: false,

  devServer:{
    port: 8081,
    proxy :{
      '/api' : {
        target: 'http://localhost:8080',    // 서버 프로젝트 포트와 동일 할 것
        changeOrigin: true,
      }
    }
  },
  outputDir:"../src/main/resources/static"
}

