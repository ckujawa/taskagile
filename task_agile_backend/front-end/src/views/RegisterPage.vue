import Vue from 'vue'

<template>
  <div class="container">
    <div class="row justify-content-center">
      <div class="register-form">
        <div class="logo-wrapper">
          <img class="logo" :src="`/static/images/logo.png`"/>
          <div class="tagline">Open source task management tool</div>
        </div>
        <form class="border border-dark shadow shadow-lg p-4" @submit.prevent="submitForm">
          <div v-if="errorMessage" class="alert alert-danger failed">{{ errorMessage }}</div>
          <div class="form-group mt-2">
            <label for="userName">Username</label>
            <input type="text" class="form-control" id="userName" v-model="form.username"/>
            <div class="field-error" v-if="$v.form.username.$dirty">
              <div class="error" v-if="!$v.form.username.required">Username is required</div>
              <div class="error" v-if="!$v.form.username.alphaNum">Username can only contain letters and numbers</div>
              <div class="error" v-if="!$v.form.username.minLength">Username must have at least {{$v.form.username.$params.minLength.min}} letters.</div>
              <div class="error" v-if="!$v.form.username.maxLength">Username is too long. It can contains maximium {{$v.form.username.$params.maxLength.max}} letters.</div>
            </div>
          </div>
          <div class="form-group mt-2">
            <label for="emailAddress">Email Address</label>
            <input type="email" class="form-control" id="emailAddress" v-model="form.emailAddress"/>
            <div class="field-error" v-if="$v.form.emailAddress.$dirty">
              <div class="error" v-if="!$v.form.emailAddress.required">Email address is required</div>
              <div class="error" v-if="!$v.form.emailAddress.email">This is not a valid email address</div>
              <div class="error" v-if="!$v.form.emailAddress.maxLength">Email address is too long. It can contains maximium {{$v.form.emailAddress.$params.maxLength.max}} letters.</div>
            </div>
          </div>
          <div class="form-group mt-2">
            <label for="password">Password</label>
            <input type="password" class="form-control" id="password" v-model="form.password"/>
            <div class="field-error" v-if="$v.form.password.$dirty">
              <div class="error" v-if="!$v.form.password.required">Password is required</div>
              <div class="error" v-if="!$v.form.password.minLength">Password is too short. It can contains at least {{$v.form.password.$params.minLength.min}} letters.</div>
              <div class="error" v-if="!$v.form.password.maxLength">Password is too long. It can contains maximium {{$v.form.password.$params.maxLength.max}} letters.</div>
            </div>
          </div>
          <button class="btn btn-primary btn-block w-100 mt-3" type="submit">Create Account</button>
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
  .error {color: red;}
</style>

<script>
  import registrationService from "@/services/registration"
  import {required, email, minLength, maxLength, alphaNum}from 'vuelidate/lib/validators'

  export default {
    name: 'RegisterPage',

    validations: {
      form: {
        username: {
          required,
          minLength: minLength(2),
          maxLength: maxLength(50),
          alphaNum
          },
        emailAddress: {
          required,
          email,
          maxLength: maxLength(100)
        },
        password: {
          required,
          minLength: minLength(8),
          maxLength: maxLength(30)
        }
      }
    },

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
        this.$v.$touch()
        if (this.$v.$invalid){
          return
        }
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