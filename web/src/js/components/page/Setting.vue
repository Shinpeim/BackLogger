<template>
    <div>
        <form @submit.prevent="save">
            <label for="api-key-input">API KEY</label>
            <input id="api-key-input" type="password" v-model="apiKeyInput">

            <button type="submit">保存</button>
        </form>
    </div>
</template>
<script>
    import {SaveSettingCommand, SettingQuery, SettingEvents} from '../../../../../scala/target/scala-2.12/backlogger-opt'

    import base from './base'

    export default {
        mixins: [base],

        created(){
            this.command = new SaveSettingCommand
        },

        beforeMount(){
            const q = new SettingQuery();
            this.apiKeyInput = q.apiKey();

            this.subscriptions.push(
                SettingEvents.saved.subscribe(() => {
                    this.$router.push("/tasks");
                })
            );
        },

        data(){
            return {
                apiKeyInput: ""
            }
        },

        watch: {
            apiKeyInput(v) {
                this.command.apiKey = v
            }
        },

        methods: {
            save(){
                this.command.execute(this.apiKeyInput)
            }
        }
    }
</script>
