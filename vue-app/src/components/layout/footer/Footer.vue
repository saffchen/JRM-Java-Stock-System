<template>
    <footer class="footer bg-secondary p-1">
        <div class="container">
            <div class="pt-4 pb-3 text-decoration-underline">
                Project contributors:
            </div>
            <div class="row mb-3">
                <div
                    v-for="contributor, id in contributors"
                    :key="id"
                    class="col-lg-4 col-md-6"
                >
                    <p class="text-nowrap">
                        {{ contributor.email }} - {{ contributor.nickname }}
                    </p>
                </div>
            </div>
            <div class="row text-center">
                <p>{{ date }}</p>
            </div>
        </div>
    </footer>
</template>

<script>
export default {
    name: 'AppFooter',
    data() {
        return {
            date: getDate(),
            contributors: []
        };
    },

    created() {
        this.getContributors();
    },
    methods: {
        getContributors: function () {
            this.$load(async () => {
                this.contributors = (await this.$api.participants.getAll()).data;
            });
        }
    }

};


function getDate() {
    const current = new Date();
    const day =  format(current.getDate());
    const month = format(current.getMonth() + 1);
    const year = current.getFullYear();
    return day + '.' + month + '.' + year;
}

function format(value) {
    if (value < 10) {
        value = '0' + value;
    }
    return value;
}
</script>

<style scoped>
    
</style>