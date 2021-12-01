// const store = Vuex.createStore({
//     state() {
//         return {
//             count: 0
//         }
//     },
//     // Remember that mutations have to be synchronous.
//     mutations: {
//         increment(state) {
//             state.count++
//         }
//     }
// })
import testStore from "./module.js";
console.log(testStore)
const store = Vuex.createStore({
    modules: {
        counter: testStore
        //Podemos agregar todos los modulos que requeramos por ejemplo:
        //auth: authStore
    }
})
const Counter = Vue.createApp({

    setup() {
        // const s = Vuex.useStore();

        //con modulos
        return{
            count: Vue.computed(() => store.state.counter.count),
            message: message,
            //pasamos en nombre del modulo en el commit
            increment: () => store.commit('counter/increment'),
        }

        //sin modulos
        // return {
        //     count: Vue.computed(() => $store.state.count),
        //     increment: () => $store.commit('increment'),
        // }
    },
    // view
    //   template: `
    //   <span>{{count}}</span>
    //   <br>
    //   <button @click="increment">+</button>
    // `,


});
Counter.use(store).mount('#app3')

