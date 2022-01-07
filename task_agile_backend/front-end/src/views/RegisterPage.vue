import Vue from 'vue'

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img class="logo" src="/static/images/logo.png"/>
          <div class="tagline">Open source task management tool</div>
        </div>
        <form>
          <div class="form-group">a</div>
          <div class="form-group">a</div>
          <div class="form-group"></div>
          <button class="btn btn-primary btn-block" type="submit">Create Account</button>
        </form>
      </div>
    </div>
    <footer class="footer">
      <span class="copyright"></span>
      <ul class="footer-links list-inline float-right"></ul>
    </footer>
  </div>
</template>

<style lang="scss" scoped>
  .container{max-width: 900px;}
  .register-form{margin-top: 50px; max-width: 320px;}
  .logo-wrapper{margin-bottom: 40px;}
  .footer{width:100%; line-height: 40px; margin-top: 50px;}
</style>