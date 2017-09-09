<style scoped>
    .issue-list {
        width: 100%;
        height: 100%;
        overflow: scroll;
    }

    .issue-list-container {
        width: 90%;
        margin: 0 auto;
    }

    table {
        width: 100%;
    }

    td {
        padding: 20px 4px;
    }

    .status-cell {
        width: 80px;
        font-size: small;
        text-align: center;
        vertical-align: middle;
    }

    .close-cell {
        width: 80px;
        font-size: small;
        text-align: center;
        vertical-align: middle;
    }

    .status-button-group > div:first-child{
        border-top: solid 1px;
        border-radius: 10px 10px 0 0;
    }
    .status-button-group > div{
        cursor: pointer;
        border-bottom: solid 1px;
        border-left: solid 1px;
        border-right: solid 1px;
        background-color: antiquewhite;
        height: 32px;
        font-weight: bold;
        display: flex;
        justify-content: center;
        align-items: center;
    }
    .status-button-group > div:last-child{
        border-radius: 0 0 10px 10px;
    }

    .status-button-group > div.current-status {
        background-color: #b38324
    }

    .close-button {
        cursor: pointer;
        border: solid 1px;
        background-color: antiquewhite;
        height: 32px;
        font-weight: bold;
        display: flex;
        justify-content: center;
        align-items: center;
        border-radius: 10px;
    }

    h1 {
        width: 100%;
        border-bottom: dashed 2px;
    }

    tr td {
        border-bottom: solid 1px gray;
    }

    .list-complete-item {
        transition: all 1s;
    }
    .list-complete-leave-to, .list-complete-leave-active {
        opacity: 0;
    }
</style>
<template>
    <div class="issue-list">
        <div class="issue-list-container">
            <div v-if="selectedProject == null">

            </div>
            <div v-else>
                <h1>自分が担当している {{selectedProject.name}} の未完了課題</h1>
                <table>
                    <transition-group name="list-complete" tag="tbody">
                        <tr v-for="i in issues" :key="i.id" class="list-complete-item">
                            <td class="summary-cell">{{i.summary}}</td>
                            <td class="status-cell">
                                <div class="status-button-group">
                                    <div :class="{'current-status': i.status == 'untreated'}"
                                         @click="makeIssueStatusAsUntreated(i.id)">
                                        未対応
                                    </div>
                                    <div :class="{'current-status': i.status == 'processing'}"
                                         @click="makeIssueStatusAsProcessing(i.id)">
                                        処理中
                                    </div>
                                    <div :class="{'current-status': i.status == 'processed'}"
                                         @click="makeIssueStatusAsProcessed(i.id)">
                                        処理済み
                                    </div>
                                </div>
                            </td>
                            <td class="close-cell">
                                <div class="close-button" @click="closeIssue(i.id)">
                                    完了
                                </div>
                            </td>
                        </tr>
                    </transition-group>
                </table>
            </div>
        </div>
    </div>
</template>
<script>
    import {
        LoadIssuesCommand,
        MakeIssueStatusAsUntreatedCommand,
        MakeIssueStatusAsProcessingCommand,
        MakeIssueStatusAsProcessedCommand,
        CloseIssueCommand,
        IssueEvents,
        IssueQuery
    } from '../../../../../../../scala/target/scala-2.12/backlogger-opt'

    import base from '../../base'

    export default {
        mixins: [base],

        props: ['selectedProject'],

        beforeMount() {
            const query = new IssueQuery;
            this.subscriptions.push(
                IssueEvents.loaded.subscribe(() => {
                    this.issues = query.allOf(this.selectedProject.id)
                })
            );
            this.subscriptions.push(
                IssueEvents.repositoryChanged.subscribe(() => {
                    this.issues = query.allOf(this.selectedProject.id)
                })
            );
        },

        watch: {
            selectedProject(v){
                const query = new IssueQuery;
                this.issues = query.allOf(this.selectedProject.id);
                const command = new LoadIssuesCommand();
                command.projectId = v.id;
                command.execute();
            }
        },

        data(){
            return {
                issues: []
            }
        },

        methods: {
            makeIssueStatusAsUntreated(issueId){
                const command = new MakeIssueStatusAsUntreatedCommand;
                command.projectId = this.selectedProject.id;
                command.issueId = issueId;
                command.execute();
            },

            makeIssueStatusAsProcessing(issueId){
                const command = new MakeIssueStatusAsProcessingCommand;
                command.projectId = this.selectedProject.id;
                command.issueId = issueId;
                command.execute();
            },

            makeIssueStatusAsProcessed(issueId){
                const command = new MakeIssueStatusAsProcessedCommand;
                command.projectId = this.selectedProject.id;
                command.issueId = issueId;
                command.execute();
            },

            closeIssue(issueId){
                const command = new CloseIssueCommand;
                command.projectId = this.selectedProject.id;
                command.issueId = issueId;
                command.execute();
            },
        }
    }
</script>
