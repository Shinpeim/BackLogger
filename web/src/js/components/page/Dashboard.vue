<template>
    <div>
        <div>
            <router-link to="/">setting</router-link>
        </div>
        <div class="projects">
            ここにプロジェクト一覧
        </div>
        <div class="issues">
            ここに課題一覧
        </div>
    </div>
</template>
<script>
    import base from './base'

    import {LoadDashboardCommand} from '../../../../../scala/target/scala-2.12/backlogger-opt'

    export default {
        mixins: [base],

        created(){
            (new LoadDashboardCommand()).execute()
        }
    }
</script>
