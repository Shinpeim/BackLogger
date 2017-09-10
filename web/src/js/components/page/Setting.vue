<style scoped>
    .container {
        height: 100%;
        width: 100%;
        display: flex;
        justify-content: center;
        align-items: center;
        background-color: #b3a77b;
    }

    .setting-form {
        width: 600px;
        height: 400px;
        background-color: antiquewhite;
        border-radius: 4px;
        font-size:1.3em;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    input {
        padding:10px;
        color:#333333;
        border:solid 1px #ccc;
        margin:0 0 20px;
        width:560px;
        border-radius: 4px;
        font-size:1.3em;
        display: flex;
    }

    form label {
        display: block;
    }

    .submit-button {
        width: 100%;
        display: flex;
        justify-content: flex-end;
        align-items: center;
        border-radius: 4px;
    }

    .submit-button button {
        background-color: #b38324 ;
        font-size:1.3em;
        cursor: pointer;
        border:solid 1px #ccc;
        font-weight: bold;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 4px;
    }


</style>
<template>
    <div class="container">
        <div class="setting-form">
            <form @submit.prevent="save">
                <div>
                    <label for="space-name-input">スペース名</label>
                    <input id="space-name-input" v-model="spaceNameInput">
                </div>
                <div>
                    <label for="api-key-input">APIキー</label>
                    <input id="api-key-input" v-model="apiKeyInput">
                </div>
                <p>※APIキーはbacklogの個人設定→APIから発行できます</p>

                <div class="submit-button">
                    <button type="submit">OK</button>
                </div>
            </form>
        </div>
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
