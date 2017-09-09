<template>
    <div>
        <div>
            <router-link to="/">setting</router-link>
        </div>
        <div class="projects">
            <div v-for="p in projects">{{p.name}}</div>
        </div>
        <div class="issues">
            ここに課題一覧
        </div>
    </div>
</template>
<script>
    import base from './base'

    import {
        ProjectQuery,
        InitializeDashboardCommand,
        DashboardEvents
    } from '../../../../../scala/target/scala-2.12/backlogger-opt'

    export default {
        mixins: [base],

        created(){
            (new InitializeDashboardCommand()).execute()
        },

        beforeMount(){
            const q = new ProjectQuery();
            this.subscriptions.push(
                DashboardEvents.initialized.subscribe(() => {
                    this.projects = q.all();
                })
            )
        },

        data(){
            const q = new ProjectQuery();
            return {
                projects: q.all()
            }
        }
    }
</script>
