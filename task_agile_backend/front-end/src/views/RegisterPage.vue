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
          <div v-show="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
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
  .logo-wrapper{
    text-align: center;
    margin-bottom: 40px;
    .tagline {
      line-height: 180%;
      color: #666;
    }
    .logo {
      max-width: 150px;
      margin: 0 auto;
    }
  }
  .footer{width:100%; line-height: 40px; margin-top: 50px;}
</style>

<script>
  import registrationService from "@/services/registration"

  export default {
    name: 'RegisterPage',

    data(){
      return{
       form: {
         username: '',
         emailAddress: '',
         password: ''
       },
        errorMessage: ''
      }
    },
    methods:{
      async submitForm(){
        // TODO: Validate the data
        try {
          await registrationService.register(this.form)
          await this.$router.push({name: 'LoginPage'})
        } catch(error) {
          this.errorMessage = 'Failed to register user. Reason: ' +
              (error.message ? error.message : 'Unknown') + '.'
        }
      }
    }
  }

</script>