<!--https://stackoverflow.com/questions/53406350/vue-import-vue-components-dynamically
https://medium.com/@pratikpatel_60309/dynamic-importing-component-templates-with-vue-js-78d2167db1e7-->


<template>
    <main class="main pb-5">
        <component :is="comp" />
    </main>
</template>

<script>
import {
    defineAsyncComponent 
} from 'vue';

export default {
    name: "DynamicComponent",
    props: {
        componentName: {
            type: String,
            required: true,
            default: "ProductTable"
        }
    },
    computed: {
        comp() {
            let name = this.componentName;
            console.log(name);
            return defineAsyncComponent(() => import(`./content/${name}.vue`));
        }
    }
};
</script>

<style>
</style>