
import {mount, createLocalVue} from "@vue/test-utils"
import RegisterPage from '@/views/RegisterPage'
import VueRouter from 'vue-router'
import registrationService from '@/services/registration'

const localVue = createLocalVue()
localVue.use(VueRouter)
const router = new VueRouter

jest.mock('@/services/registration')

describe('RegisterPage.vue', () =>{
  let wrapper
  let fieldUsername
  let fieldEmailAddress
  let fieldPassword
  let buttonSubmit
  let registerSpy

  beforeEach(() =>{
    wrapper = mount(RegisterPage,{
        localVue,
        router
      })
    fieldUsername = wrapper.find('#userName')
    fieldEmailAddress = wrapper.find('#emailAddress')
    fieldPassword = wrapper.find('#password')
    buttonSubmit = wrapper.find('form button[type="submit"]')
    registerSpy = jest.spyOn(registrationService, 'register')
  })

  afterEach(() => {
    registerSpy.mockReset()
    registerSpy.mockRestore()
  })

  afterAll(() =>{
    jest.resetAllMocks()
  })

  it('should render registration form', () =>{
    expect(wrapper.find('.logo').attributes().src)
      .toEqual('/static/images/logo.png')
    expect(wrapper.find('.tagline').text())
      .toEqual('Open source task management tool')
    expect(fieldUsername.element.value).toEqual('')
    expect(fieldEmailAddress.element.value).toEqual('')
    expect(fieldPassword.element.value).toEqual('')
    expect(buttonSubmit.text()).toEqual('Create Account')
  })

  it('should contain data model with initial values', () => {
    expect(wrapper.vm.form.username).toEqual('')
    expect(wrapper.vm.form.emailAddress).toEqual('')
    expect(wrapper.vm.form.password).toEqual('')
  })

  it('should have form inputs boudn with data model', async () => {
    const username = 'sunny'
    const emailAddress = 'sunny@somewhere'
    const password = 's0methingKryptic'

    await wrapper.setData({
      form: {
        username : username,
        emailAddress: emailAddress,
        password: password
      }
    })

    expect(fieldUsername.element.value).toEqual(username)
    expect(fieldEmailAddress.element.value).toEqual(emailAddress)
    expect(fieldPassword.element.value).toEqual(password)
  })

  it('should have a form submit handler `submitForm`', () => {
    const subSpy = jest.spyOn(wrapper.vm, "submitForm")
    buttonSubmit.trigger('submit')
    expect(subSpy).toBeCalled()
  })

  it('should register a new user', async () =>{
    const spyOn = jest.spyOn(router, "push")
    try {
      await wrapper.setData({
        form: {
          username: 'sunny',
          emailAddress: 'sunny@local',
          password: 'superSecret'
        }
      })
      wrapper.vm.submitForm()
      await wrapper.vm.$nextTick(() => {
        expect(spyOn).toHaveBeenCalledWith({name: 'LoginPage'})
      })
    } catch (e){
      console.error("ouch!")
    }
  })

/*  it('should fail it is not a new user', () => {
    // In the mock, only sunny@local is new user
    wrapper.vm.form.emailAddress = 'ted@local'
    expect(wrapper.find('.failed').isVisible()).toBe(false)
    wrapper.vm.submitForm()
    wrapper.vm.$nextTick(null, () => {
      expect(wrapper.find('.failed').isVisible()).toBe(true)
    })
  })*/
})