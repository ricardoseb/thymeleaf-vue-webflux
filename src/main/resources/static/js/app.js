import testStore from "./module.js";
const store2 = Vuex.createStore({
    modules: {
        counter: testStore
        //Podemos agregar todos los modulos que requeramos por ejemplo:
        //auth: authStore
    }
})
const app = Vue.createApp({

    setup() {
        return {
            count: Vue.computed(() => store2.state.counter.count),
            increment: () => store2.commit('counter/increment'),
            message: message,
            message2: data2
        }
    }
}).use(store2)
    .mount('#app')