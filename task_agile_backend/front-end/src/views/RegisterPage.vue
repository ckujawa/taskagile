import Vue from 'vue'

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img class="logo" :src="`/static/images/logo.png`"/>
          <div class="tagline">Open source task management tool</div>
        </div>
        <form @submit.prevent="submitForm">
          <div class="form-group">
            <label for="userName">Username</label>
            <input type="text" class="form-control" id="userName" v-model="form.username"/>
          </div>
          <div class="form-group">
            <label for="emailAddress">Email Address</label>
            <input type="email" class="form-control" id="emailAddress" v-model="form.emailAddress"/>
          </div>
          <div class="form-group">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password"/>
          </div>
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

<script>
  export default {
    name: 'RegisterPage',

    data(){
      return{
       form: {
         username: '',
         emailAddress: '',
         password: ''
       }
      }
    },
    methods:{
      submitForm(){
      }
    }
  }

</script>