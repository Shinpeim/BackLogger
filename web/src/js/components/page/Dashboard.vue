<style scoped>
    .dashboard {
        background-color: white;
        display: flex;
        height: 100%;
    }

    .sidebar {
        width: 80px;
        display: flex;
        flex-direction: column;
        /*background-color: brown;*/
        background-color: #b3a77b;
        overflow: scroll;
        font-weight: bold;
    }

    .setting-button {
        background-color: gray;
        margin: 10px auto;
        width: 60px;
        height: 60px;
        border-radius: 10px;
        cursor: pointer;
        display: flex;
        justify-content: center;
        align-items: center;
        font-size: 12px;
    }

    .project-select-button {
        background-color: antiquewhite;
        margin: 10px auto;
        width: 60px;
        height: 60px;
        border-radius: 10px;
        cursor: pointer;
        display: flex;
        justify-content: center;
        align-items: center;
    }

    .selected-project-button {
        background-color: #b38324 ;
    }

</style>
<template>
    <div class="dashboard">
        <div class="sidebar">
            <div class="projects">
                <div class="project-select-button"
                     :class='{"selected-project-button": selectedProject && selectedProject.id == p.id}'
                     v-for="p in projects"
                     @click="selectProject(p)">
                    {{p.key}}
                </div>
            </div>

            <div class="setting">
                <div class="setting-button" @click="openSettingPanel">
                    設定
                </div>
            </div>
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
        DashboardEvents,
        NetworkEvents
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
            );
            this.subscriptions.push(
                NetworkEvents.requestFailed.subscribe(() => {
                    alert("backlogとの通信に失敗しました。ネットワーク状況や、スペース名、APIキーを確認してください");
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
            },

            openSettingPanel(){
                this.$router.push("/setting")
            }
        }
    }
</script>
