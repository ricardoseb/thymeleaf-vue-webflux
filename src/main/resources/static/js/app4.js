import {App5} from './app5.js'

export default {
    components: {
        App5
    },
    setup() {
        return {
            message: Vue.ref('Hi from app4')
        }
    },
    template: `
    <h1>
    {{message}}
    </h1>
<App5 post-title="hello props!"></App5>
`

}