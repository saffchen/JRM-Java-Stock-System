export default {
    install(Vue) {
        Vue.config.globalProperties.$load = async (action, errHandler) => {
            try {
                await action()
            } catch (error) {
                if (errHandler) {
                    errHandler()
                } else {
                    console.log(error.response.data)
                }
            }
        }
    }
}