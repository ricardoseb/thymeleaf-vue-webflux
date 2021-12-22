import app4 from './app4.js';
const app6 = Vue.createApp({
    components: {
        'component-a': app4,
    }
}).mount('#app6')