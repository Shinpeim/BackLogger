<template>
    <div>
        <form @submit.prevent="save">
            <label for="api-key-input">API KEY</label>
            <input id="api-key-input" type="password" v-model="apiKey">

            <button type="submit">保存</button>
        </form>
    </div>
</template>
<script>
    export default {
        data(){
            return {
                apiKey: ""
            }
        },

        methods: {
            save(){
                console.log(this.apiKey)
            }
        }
    }
</script>
