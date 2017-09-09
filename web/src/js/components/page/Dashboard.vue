<template>
    <div>
        <div>
            <router-link to="/">setting</router-link>
        </div>
        <div class="projects">
            <div v-for="p in projects" @click="selectProject(p)">{{p.name}}</div>
        </div>
        <issue-list :selectedProject="selectedProject"></issue-list>
    </div>
</template>
<script>
    import base from './base'
    import IssueList from './Dashboard/partial/IssueList.vue'

    import {
        ProjectQuery,
        InitializeDashboardCommand,
        DashboardEvents
    } from '../../../../../scala/target/scala-2.12/backlogger-opt'

    export default {
        mixins: [base],

        components: {
            IssueList
        },

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
                projects: q.all(),
                selectedProject: null
            }
        },

        methods: {
            selectProject(p){
                this.selectedProject = p;
            }
        }
    }
</script>
