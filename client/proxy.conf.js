 const PROXY_CONFIG = [
   {
      context : [ '/api' ] ,
      target : 'http://10.27.104.82:8080',
      source: false,
      loglevel: 'debug'
    }
  ] ;  

  module.exports =  PROXY_CONFIG;