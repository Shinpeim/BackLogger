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
    import {SaveSettingCommand} from '../../../../../scala/target/scala-2.12/backlogger-opt'

    export default {
        data(){
            return {
                apiKey: ""
            }
        },

        methods: {
            save(){
                (new SaveSettingCommand).save(this.apiKey)
            }
        }
    }
</script>
