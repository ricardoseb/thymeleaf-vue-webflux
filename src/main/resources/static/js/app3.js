const store = Vuex.createStore({
    state() {
        return {
            count: 0
        }
    },
    mutations: {
        increment(state) {
            state.count++
        }
    }
})
const Counter = Vue.createApp({

    setup() {
        const s = Vuex.useStore();

        return {
            count: Vue.computed(() => s.state.count),
            increment: () => s.commit('increment'),
        }
    },
    // view
  //   template: `
  //   <span>{{count}}</span>
  //   <br>
  //   <button @click="increment">+</button>
  // `,


});
Counter.use(store).mount('#app3')
