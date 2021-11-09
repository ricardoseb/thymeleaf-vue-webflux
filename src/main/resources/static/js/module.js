const testStore = {
    namespaced:true,
    //para retornar el objeto pasamos los parebtesis sobre el objeto
    state: () =>({
        count:0
    }),
    mutations: {
        increment(state) {
            state.count++
        }
    }
}

export default testStore