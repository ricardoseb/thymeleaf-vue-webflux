const app2 = {
    setup() {
        return {
            talla: talla,
        }
    }
};
const mixinApp2 = {
    data(){
        return {
            color: color,
        }
    }
};
const app2Build= Vue.createApp(app2);
app2Build.mixin(mixinApp2);
app2Build.mount('#app2');