<template>
    <form
        name="update-stock"
        method="put"
        @submit="updateStock"
    >
        <fieldset class="mb-3">
            <label
                for="stock-name"
                class="form-label"
            >Name</label>
            <input
                id="stock-name"
                v-model="name"
                type="text"
                class="form-control"
            >
        </fieldset>
        <fieldset class="mb-3">
            <label
                for="stock-description"
                class="form-label"
            >Description</label>
            <textarea
                id="stock-description"
                v-model="description"
                class="form-control"
            />
            <button
                type="button"
                @click="updateData"
            >
                Get the current stock
            </button>
        </fieldset>
    </form>
</template>


<script>



export default {
    name: "UpdateStockForm",
    emits: ['action'],
    data() {
        return {
            id: undefined,
            name: '',
            description: '',
            payload: {}
        };
    },
    watch: {
        name() {
            this.payload['name'] = this.name;
        },
        description() {
            this.payload['description'] = this.description;
        }
    },
    activated() {
        console.log("UpdateStockFrom has been activated");
    },
    methods: {
        updateData: function () {
            const storeJSON = JSON.parse(JSON.stringify(this.$store.state.Store));
            const storeMap = new Map(Object.entries(storeJSON));
            console.log("store.js.name", storeMap);
            console.log("store.js.id", storeMap.get('id'));
            console.log("this id", this.id);
            this.id = storeMap.get('id');
            console.log("this id", this.id);
            this.name = storeMap.get('name');
            this.description = storeMap.get('description');
        },
        updateStock: function (event) {
            if (event.type === 'submit') {
                event.preventDefault();
            }
            if (this.name === '' || this.description === '' ) {
                console.log('[ERROR]: Please fill in empty fields');
                return;
            }
            this.$load(async () => {
                await this.$api.stocks.update(this.payload, this.id);
                this.$emit('action');
                console.log('[SUCCESS]: Stock updated');
            });
            this.name = '';
            this.description = '';
        },
        refresh: function () {
            this.name = '';
            this.description = '';
        }
    }
};
</script>

<style>
</style>