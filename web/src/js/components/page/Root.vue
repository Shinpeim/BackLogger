<style scoped>
    .root {
        height: 100%;
        width: 100%;
    }
</style>
<template>
    <div class="root">
        <div class="loader"></div>
    </div>
</template>
<script>
    import {SettingQuery} from '../../../../../scala/target/scala-2.12/backlogger-opt'

    import base from './base'

    export default {
        mixins: [base],

        mounted(){
            const q = new SettingQuery();
            this.spaceNameInput = q.spaceName();

            if (this.spaceNameInput == "") {
                this.$router.push("/setting");
            } else {
                this.$router.push("/dashboard");
            }
        }
    }
</script>
