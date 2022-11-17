export default {
    install(Vue) {
        Vue.config.globalProperties.$load = async (action, errHandler) => {
            try {
                await action();
            } catch (error) {
                if (errHandler) {
                    errHandler(error);
                } else {
                    console.log(error);
                }
            }
        };
    }
};