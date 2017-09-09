<template>
    <div>
        <form @submit.prevent="save">
            <div>
                <label for="space-name-input">スペース名</label>
                <input id="space-name-input" v-model="spaceNameInput">
            </div>
            <div>
                <label for="api-key-input">API KEY</label>
                <input id="api-key-input" v-model="apiKeyInput">
            </div>

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
            this.command = new SaveSettingCommand;
        },

        beforeMount(){
            this.updateData();

            this.subscriptions.push(
                SettingEvents.saved.subscribe(() => {
                    this.$router.push("/dashboard");
                })
            );

            this.subscriptions.push(
                SettingEvents.apiKeyVerificationFailed.subscribe(() => {
                    alert("認証に失敗しました")
                })
            );
        },

        data(){
            return {
                apiKeyInput: "",
                spaceNameInput: ""
            }
        },

        watch: {
            apiKeyInput(v) {
                this.command.apiKey = v
            },
            spaceNameInput(v) {
                this.command.spaceName = v
            }
        },

        methods: {
            save(){
                this.command.execute()
            },
            updateData(){
                const q = new SettingQuery();
                console.log(q.apiKey());
                this.apiKeyInput = q.apiKey();
                this.spaceNameInput = q.spaceName();
            }
        }
    }
</script>
