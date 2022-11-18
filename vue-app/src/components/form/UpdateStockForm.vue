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
                :value="name"
                type="text"
                class="form-control"
                @input="changeName"
            >
        </fieldset>
        <fieldset class="mb-3">
            <label
                for="stock-description"
                class="form-label"
            >Description</label>
            <textarea
                id="stock-description"
                :value="description"
                class="form-control"
                @input="changeDescription"
            />
        </fieldset>
    </form>
    <div
        v-if="showMessage"
        id="status"
        class="mt-3"
    >
        <span
            class="message"
            :class="messageClass"
            v-text="messageText"
        />
    </div>
</template>


<script>



export default {
    name: 'UpdateStockForm',
    data() {
        return {
            name: '',
            description: '',
            payload: {},
            showMessage: false,
            messageClass: '',
            messageText: ''
        };
    },
    activated() {
        const stock = this.$store.getters['stock/editObject'];
        this.name = stock.name;
        this.description = stock.description;
        this.payload = {
            id: stock.id,
            name: stock.name,
            description: stock.description
        };
    },
    methods: {
        changeName: function(event) {
            this.name = event.target.value;
            this.payload.name = event.target.value;
            this.showMessage = false;
        },
        changeDescription: function(event) {
            this.description = event.target.value;
            this.payload.description = event.target.value;
            this.showMessage = false;
        },
        updateStock: function (event) {
            if (event.type === 'submit') {
                event.preventDefault();
            }
            if (this.name === '' || this.description === '' ) {
                this.messageClass = 'error';
                this.messageText = 'Fields cannot be empty!';
                this.showMessage = true;
                return;
            }
            this.$load(async () => {
                await this.$store.dispatch('stock/update', this.payload);
                this.messageClass = 'success';
                this.messageText = 'The stock was successfully updated';
                this.showMessage = true;
            }, this.handleError);
            this.refresh();
        },
        handleError: function (error) {
            this.messageClass = 'error';
            this.messageText = error.response.data.message;
            this.showMessage = true;
        },
        refresh: function() {
            this.name = '';
            this.description = '';
            this.messageClass = '';
            this.messageText = '';
            this.showMessage = false;
            document.getElementById('stock-description').removeAttribute('style');
        }
    }
};
</script>

<style scoped>

#status {
    text-align: right;
}

.message {
    padding: 5px;
    border-radius: 10px;
}

.success {
    background: #095d09;
    color: white;
}

.error {
    background: #b21111;
    color: white;
}

</style>